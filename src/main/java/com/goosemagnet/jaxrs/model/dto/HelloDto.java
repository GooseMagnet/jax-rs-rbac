package com.goosemagnet.jaxrs.model.dto;


import lombok.*;

import java.time.Instant;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class HelloDto {

    String message;
    Instant timestamp;
    Integer visit;

    public HelloDto(String message, Instant timestamp) {
        this(message, timestamp, null);
    }
}
