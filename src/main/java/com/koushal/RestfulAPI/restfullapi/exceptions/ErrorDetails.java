package com.koushal.RestfulAPI.restfullapi.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime time;
    private String message;
    private String description;

    public ErrorDetails(LocalDateTime time, String message, String description) {
        this.time = time;
        this.message = message;
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "time=" + time +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
