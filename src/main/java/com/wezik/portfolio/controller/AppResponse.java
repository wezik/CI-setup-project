package com.wezik.portfolio.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder
@JsonPropertyOrder({"status", "value", "message", "result"})
public class AppResponse {

    @JsonProperty("status")
    private final HttpStatus status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private Object result;

    @JsonProperty("value")
    private int value() {
        return status.value();
    }

    public static AppResponse statusOK(Object o) {
        return AppResponse.builder()
                .status(HttpStatus.OK)
                .message("Success")
                .result(o)
                .build();
    }

    public static AppResponse statusOK() {
        return AppResponse.builder()
                .status(HttpStatus.OK)
                .message("Success")
                .build();
    }

    public static AppResponse statusNotFound(String message) {
        return AppResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(message)
                .build();
    }

    public static AppResponse statusNotFound() {
        return AppResponse.statusNotFound("Not found");
    }

    public static AppResponse statusBadRequest(String message) {
        return AppResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(message)
                .build();
    }

    public static AppResponse statusBadRequest() {
        return AppResponse.statusBadRequest("Bad Request");
    }

}
