package com.example.MedicineRemember.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.MedicineRemember.Model.Medicine;
import com.example.MedicineRemember.Model.Patient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public boolean sendReminderEmail(Patient patient, Medicine medicine, LocalTime scheduledTime, int reminderId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(patient.getEmail());
            message.setSubject("Recordatorio de Medicamento - " + medicine.getName());
            message.setText(buildEmailMessage(patient, medicine, scheduledTime, reminderId));

            mailSender.send(message);
            log.info("Email enviado a: {} para medicamento: {}", patient.getEmail(), medicine.getName());
            return true;
        } catch (Exception e) {
            log.error("Error enviando email: {}", e.getMessage());
            return false;
        }
    }

    private String buildEmailMessage(Patient patient, Medicine medicine, LocalTime scheduledTime, int reminderId) {
        String baseUrl = "http://localhost:8080/api";
        String confirmUrl = baseUrl + "/reminders/" + reminderId + "/confirm";
        String suspendUrl = baseUrl + "/reminders/" + reminderId + "/suspend?suspend=true";

        return String.format("""
                             Hola %s,
                             
                             \ud83d\udd14 Es hora de tomar tu medicamento:
                             
                             \ud83d\udc8a Medicamento: %s
                             \ud83d\udccf Dosis: %s
                             \u23f0 Hora programada: %s
                             
                             \u2705 Confirmar: %s
                             \u23f8\ufe0f Suspender: %s
                             
                             Cu\u00eddate,
                             Sistema de Recordatorios M\u00e9dicos""",
                patient.getName(),
                medicine.getName(),
                medicine.getDose(),
                scheduledTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                confirmUrl,
                suspendUrl
        );
    }
}

