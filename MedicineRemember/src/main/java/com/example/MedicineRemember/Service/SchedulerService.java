package com.example.MedicineRemember.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.MedicineRemember.Model.History;
import com.example.MedicineRemember.Model.Reminder;
import com.example.MedicineRemember.Repository.IReminder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchedulerService {

    private final ReminderService reminderService;
    private final EmailService emailService;
    private final HistoryService historyService;
    private final IReminder reminderRepository;

    public SchedulerService(ReminderService reminderService, EmailService emailService, 
                           HistoryService historyService, IReminder reminderRepository) {
        this.reminderService = reminderService;
        this.emailService = emailService;
        this.historyService = historyService;
        this.reminderRepository = reminderRepository;
    }

    @Scheduled(fixedRate = 300000) // Cada 5 minutos
    public void checkMedicationReminders() {
        log.info("Verificando recordatorios...");
        
        List<Reminder> remindersToSend = reminderService.getRemindersToSend();
        log.info("Encontrados {} recordatorios para enviar", remindersToSend.size());

        for (Reminder reminder : remindersToSend) {
            sendReminderNotification(reminder);
        }
    }

    @Scheduled(fixedRate = 60000) // Cada 1 minuto
    public void checkMissedMedications() {
        log.info("Verificando medicamentos no confirmados...");
        
        List<Reminder> missedReminders = reminderService.getMissedReminders();
        
        for (Reminder reminder : missedReminders) {
            handleMissedMedication(reminder);
        }
    }

    private void sendReminderNotification(Reminder reminder) {
        try {
            boolean emailSent = emailService.sendReminderEmail(
                    reminder.getPatient(),
                    reminder.getMedicine(),
                    reminder.getScheduledTime(),
                    reminder.getReminderId()
            );

            reminder.setStatus("SENT");
            reminderRepository.save(reminder);

            // Registrar en historial
            History history = new History();
            history.setReminder(reminder);
            history.setSentTime(LocalDateTime.now());
            history.setResult(emailSent ? "EMAIL_SENT" : "EMAIL_FAILED");
            historyService.logHistory(history);

        } catch (Exception e) {
            log.error("Error enviando recordatorio: {}", e.getMessage());
        }
    }

    private void handleMissedMedication(Reminder reminder) {
        try {
            reminder.setStatus("MISSED");
            reminderRepository.save(reminder);

            History history = new History();
            history.setReminder(reminder);
            history.setSentTime(LocalDateTime.now());
            history.setResult("MISSED_MEDICATION");
            historyService.logHistory(history);

            log.warn("Medicamento no confirmado - Paciente: {}", reminder.getPatient().getName());
        } catch (Exception e) {
            log.error("Error procesando medicamento perdido: {}", e.getMessage());
        }
    }

}
