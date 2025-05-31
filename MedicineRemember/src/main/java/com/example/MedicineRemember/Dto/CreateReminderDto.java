package com.example.MedicineRemember.Dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReminderDto {
    private int patientId;
    private int medicineId;
    private LocalTime scheduledTime;
}