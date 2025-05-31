package com.example.MedicineRemember.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MedicineRemember.Dto.ReminderDto;
import com.example.MedicineRemember.Dto.ResponseDto;
import com.example.MedicineRemember.Model.History;
import com.example.MedicineRemember.Model.Medicine;
import com.example.MedicineRemember.Model.Patient;
import com.example.MedicineRemember.Model.Reminder;
import com.example.MedicineRemember.Repository.IMedicine;
import com.example.MedicineRemember.Repository.IPatient;
import com.example.MedicineRemember.Repository.IReminder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReminderService {

    private final IReminder reminderRepository;
    private final IPatient patientRepository;
    private final IMedicine medicineRepository;
    private final HistoryService historyService;

    public ReminderService(IReminder reminderRepository, IPatient patientRepository,
            IMedicine medicineRepository, HistoryService historyService) {
        this.reminderRepository = reminderRepository;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.historyService = historyService;
    }

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public ResponseDto addReminder(ReminderDto reminderDTO) {
        if (!validation(reminderDTO)) {
            return ResponseDto.error("All fields are required");
        }

        // Cargar entidades completas
        Optional<Patient> patientOpt = patientRepository.findById(reminderDTO.getPatientId());
        Optional<Medicine> medicineOpt = medicineRepository.findById(reminderDTO.getMedicineId());

        if (patientOpt.isEmpty()) {
            return ResponseDto.error("Patient not found");
        }
        if (medicineOpt.isEmpty()) {
            return ResponseDto.error("Medicine not found");
        }

        Reminder reminder = dtoToModel(reminderDTO);
        reminder.setPatient(patientOpt.get());
        reminder.setMedicine(medicineOpt.get());
        reminder.setStatus("PENDING");

        reminderRepository.save(reminder);
        return ResponseDto.ok("Reminder added successfully");
    }

    public ResponseDto confirmReminder(int id) {
        Optional<Reminder> reminderOpt = reminderRepository.findById(id);
        if (reminderOpt.isEmpty()) {
            return ResponseDto.error("Reminder not found");
        }

        Reminder reminder = reminderOpt.get();
        reminder.setStatus("CONFIRMED");
        reminder.setConfirmationTime(LocalTime.now());
        reminderRepository.save(reminder);

        // Registrar en historial
        historyService.logHistory(createHistory(reminder, "CONFIRMED"));

        return ResponseDto.ok("Medicamento confirmado! Gracias por confirmar tu dosis.");
    }

    public ResponseDto suspendReminder(int id, boolean suspend) {
        Optional<Reminder> reminderOpt = reminderRepository.findById(id);
        if (reminderOpt.isEmpty()) {
            return ResponseDto.error("Reminder not found");
        }

        Reminder reminder = reminderOpt.get();
        reminder.setSuspended(suspend);
        reminderRepository.save(reminder);

        historyService.logHistory(createHistory(reminder, suspend ? "SUSPENDED" : "REACTIVATED"));

        String message = suspend ? "Recordatorios suspendidos temporalmente" : "Recordatorios reactivados";
        return ResponseDto.ok(message);
    }

    public List<Reminder> getRemindersToSend() {
        LocalTime now = LocalTime.now();
        LocalTime startWindow = now.minusMinutes(5);
        LocalTime endWindow = now.plusMinutes(1);

        return reminderRepository.findAll().stream()
                .filter(reminder -> reminder.getPatient().isReminderActive())
                .filter(reminder -> "PENDING".equals(reminder.getStatus()))
                .filter(reminder -> !reminder.isSuspended())
                .filter(reminder -> isInTimeWindow(reminder.getScheduledTime(), startWindow, endWindow))
                .collect(Collectors.toList());
    }

    public List<Reminder> getMissedReminders() {
        LocalTime oneHourAgo = LocalTime.now().minusHours(1);

        return reminderRepository.findAll().stream()
                .filter(reminder -> "SENT".equals(reminder.getStatus()))
                .filter(reminder -> reminder.getConfirmationTime() == null)
                .filter(reminder -> reminder.getScheduledTime().isBefore(oneHourAgo))
                .collect(Collectors.toList());
    }

    private boolean validation(ReminderDto reminderDTO) {
        return Objects.nonNull(reminderDTO.getPatientId())
                && Objects.nonNull(reminderDTO.getMedicineId())
                && Objects.nonNull(reminderDTO.getScheduledTime());
    }

    private Reminder dtoToModel(ReminderDto reminderDTO) {
        Reminder reminder = new Reminder();
        reminder.setScheduledTime(reminderDTO.getScheduledTime());
        return reminder;
    }

    private History createHistory(Reminder reminder, String result) {
        History history = new History();
        history.setReminder(reminder);
        history.setSentTime(LocalDateTime.now());
        history.setResult(result);
        return history;
    }

    private boolean isInTimeWindow(LocalTime scheduledTime, LocalTime start, LocalTime end) {
        return !scheduledTime.isBefore(start) && !scheduledTime.isAfter(end);
    }
}
