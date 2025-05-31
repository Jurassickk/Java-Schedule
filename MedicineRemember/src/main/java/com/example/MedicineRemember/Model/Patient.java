package com.example.MedicineRemember.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name = "Patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientId")
    private int patientId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
    
    @Column(name = "email", length = 250, nullable = false)
    private String email;

    @Column(name = "reminder_active")
    private boolean reminderActive = true;



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

    public boolean isReminderActive() {return reminderActive;}

    public void setReminderActive(boolean reminderActive) {this.reminderActive = reminderActive;}
}
