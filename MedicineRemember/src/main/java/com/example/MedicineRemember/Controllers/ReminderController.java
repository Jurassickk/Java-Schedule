package com.example.MedicineRemember.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicineRemember.Dto.ReminderDto;
import com.example.MedicineRemember.Dto.ResponseDto;
import com.example.MedicineRemember.Model.Reminder;
import com.example.MedicineRemember.Service.ReminderService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/reminders")
@Slf4j
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @GetMapping
    public ResponseEntity<List<Reminder>> getAllReminders() {
        return ResponseEntity.ok(reminderService.getAllReminders());
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addReminder(@RequestBody ReminderDto reminderDTO) {
        ResponseDto response = reminderService.addReminder(reminderDTO);
        return ResponseEntity.ok(response); 
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<ResponseDto> confirmReminder(@PathVariable int id) {
        ResponseDto response = reminderService.confirmReminder(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/suspend")
    public ResponseEntity<ResponseDto> suspendReminder(@PathVariable int id, @RequestParam boolean suspend) {
        ResponseDto response = reminderService.suspendReminder(id, suspend);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/to-send")
    public ResponseEntity<List<Reminder>> getRemindersToSend() {
        return ResponseEntity.ok(reminderService.getRemindersToSend());
    }

    @GetMapping("/missed")
    public ResponseEntity<List<Reminder>> getMissedReminders() {
        return ResponseEntity.ok(reminderService.getMissedReminders());
    }
}
