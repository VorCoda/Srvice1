package com.example.Service1.Brockers_Serv1;

import java.time.LocalDateTime;

public class MsgDTO {

    private String text;
    private LocalDateTime timestamp;


    public MsgDTO(String text) {
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }


    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
