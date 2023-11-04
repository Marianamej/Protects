package com.gamertx.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ErrorDetails {
    private LocalDate timeStamp;
    private String message;
    private String detail;

    public ErrorDetails(LocalDate timeStamp, String message, String detail) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.detail = detail;
    }
}
