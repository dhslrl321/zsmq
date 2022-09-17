package com.example.example.model;

import java.time.ZonedDateTime;

public class IdGenerator {
    public static Long gen() {
        return ZonedDateTime.now().toEpochSecond();
    }
}
