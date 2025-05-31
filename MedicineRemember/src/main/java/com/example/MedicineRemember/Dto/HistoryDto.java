package com.example.MedicineRemember.Dto;

import java.time.LocalDateTime;

public class HistoryDto {

    private int historyId;
    private int reminderId;
    private LocalDateTime sentTime;
    private String result;

    public HistoryDto() {}

    public HistoryDto(int historyId, int reminderId, LocalDateTime sentTime, String result) {
        this.historyId = historyId;
        this.reminderId = reminderId;
        this.sentTime = sentTime;
        this.result = result;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
