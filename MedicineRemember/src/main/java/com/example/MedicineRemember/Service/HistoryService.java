package com.example.MedicineRemember.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MedicineRemember.Dto.ResponseDto;
import com.example.MedicineRemember.Model.History;
import com.example.MedicineRemember.Repository.IHistory;

@Service
public class HistoryService {

    private final IHistory historyRepository;

    public HistoryService(IHistory historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

    public ResponseDto logHistory(History history) {
        try {
            history.setSentTime(LocalDateTime.now());
            historyRepository.save(history);
            return ResponseDto.ok("History logged successfully");
        } catch (Exception e) {
            return ResponseDto.error("Error logging history");
        }
    }

    public List<History> getHistoryByReminder(int reminderId) {
        return historyRepository.findAll().stream()
                .filter(history -> history.getReminder().getReminderId() == reminderId)
                .sorted((h1, h2) -> h2.getSentTime().compareTo(h1.getSentTime()))
                .collect(Collectors.toList());
    }
}
