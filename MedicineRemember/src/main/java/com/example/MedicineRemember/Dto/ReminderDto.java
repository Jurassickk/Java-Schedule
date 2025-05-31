package com.example.MedicineRemember.Dto;

import java.time.LocalTime;

public class ReminderDto {

    private int reminderId;
    private int patientId;
    private int medicineId;
    private LocalTime scheduledTime;
    private String status;
    private LocalTime confirmationTime;
    private boolean isSuspended;

    public ReminderDto() {}

    public ReminderDto(int reminderId, int patientId, int medicineId, LocalTime scheduledTime, String status, LocalTime confirmationTime, boolean isSuspended) {
        this.reminderId = reminderId;
        this.patientId = patientId;
        this.medicineId = medicineId;
        this.scheduledTime = scheduledTime;
        this.status = status;
        this.confirmationTime = confirmationTime;
        this.isSuspended = isSuspended;
    }

    public int getReminderId() {
        return reminderId;
    }
    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public int getMedicineId() {
        return medicineId;
    }
    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }
    public LocalTime getScheduledTime() {
        return scheduledTime;
    }
    public void setScheduledTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalTime getConfirmationTime() {
        return confirmationTime;
    }
    public void setConfirmationTime(LocalTime confirmationTime) {
        this.confirmationTime = confirmationTime;
    }
}
