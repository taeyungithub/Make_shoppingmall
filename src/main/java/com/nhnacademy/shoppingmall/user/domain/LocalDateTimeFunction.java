package com.nhnacademy.shoppingmall.user.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// jsp에서 localdatetime 출력하려고 만듬
public class LocalDateTimeFunction {
    public LocalDateTimeFunction() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatDate(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}