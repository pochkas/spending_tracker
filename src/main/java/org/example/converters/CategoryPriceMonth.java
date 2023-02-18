package org.example.converters;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public interface CategoryPriceMonth {

    String getCategory();

    Double getPrice();

    Integer getMonthDate();

    Integer getYearDate();
}
