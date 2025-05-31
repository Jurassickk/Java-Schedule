package com.example.MedicineRemember.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "History")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;

    @ManyToOne
    @JoinColumn(name = "reminder_id", nullable = false)
    private Reminder reminder;

    @Column(name = "sentTime", nullable = false)
    private LocalDateTime sentTime;

    @Column(name = "result", length = 100)
    private String result;

    public History() {}

    public History(Reminder reminder, LocalDateTime sentTime, String result) {
        this.reminder = reminder;
        this.sentTime = sentTime;
        this.result = result;
    }

    public int getHistoryId() {return historyId;}

    public void setHistoryId(int historyId) {this.historyId = historyId;}

    public Reminder getReminder() {return reminder;}

    public void setReminder(Reminder reminder) {this.reminder = reminder;}

    public LocalDateTime getSentTime() {return sentTime;}

    public void setSentTime(LocalDateTime sentTime) {this.sentTime = sentTime;}

    public String getResult() {return result;}

    public void setResult(String result) {this.result = result;}
}
