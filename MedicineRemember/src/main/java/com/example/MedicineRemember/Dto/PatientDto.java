package com.example.MedicineRemember.Dto;

public class PatientDto {

    private int patientId;
    private String name;
    private String email;
    private boolean reminderActive;

    public PatientDto(){}

    public PatientDto(int patientId, String name, String email, boolean reminderActive) {
        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.reminderActive = reminderActive;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isReminderActive() {
        return reminderActive;
    }

    public void setReminderActive(boolean reminderActive) {
        this.reminderActive = reminderActive;
    }
}
