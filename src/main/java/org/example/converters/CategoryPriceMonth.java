package org.example.converters;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface CategoryPriceMonth {

    String getCategory();

    Double getPrice();

    int getMonthDate();

    int getYearDate();
}
