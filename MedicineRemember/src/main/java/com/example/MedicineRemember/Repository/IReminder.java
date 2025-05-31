package com.example.MedicineRemember.Repository;

import com.example.MedicineRemember.Model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;

public interface IReminder extends JpaRepository<Reminder, Integer> {
    @Query("SELECT r FROM Reminder r WHERE r.scheduledTime BETWEEN :startTime AND :endTime " +
            "AND r.status = 'PENDING' AND r.isSuspended = false AND r.patient.reminderActive = true")
    List<Reminder> findPendingRemindersInTimeRange(LocalTime startTime, LocalTime endTime);

    @Query("SELECT r FROM Reminder r WHERE r.status = 'SENT' AND r.confirmationTime IS NULL")
    List<Reminder> findUnconfirmedReminders();
}
