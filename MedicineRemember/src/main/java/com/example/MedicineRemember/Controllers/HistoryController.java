package com.example.MedicineRemember.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicineRemember.Dto.ResponseDto;
import com.example.MedicineRemember.Model.History;
import com.example.MedicineRemember.Service.HistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<History>> getAllHistory() {
        return ResponseEntity.ok(historyService.getAllHistory());
    }

    @GetMapping("/reminder/{reminderId}")
    public ResponseEntity<List<History>> getHistoryByReminder(@PathVariable int reminderId) {
        return ResponseEntity.ok(historyService.getHistoryByReminder(reminderId));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> logHistory(@RequestBody History history) {
        ResponseDto response = historyService.logHistory(history);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
