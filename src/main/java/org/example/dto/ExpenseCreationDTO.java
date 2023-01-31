package org.example.dto;

import org.example.model.ExpenseCategory;

import java.time.LocalDateTime;

public class ExpenseCreationDTO {

    private ExpenseCategory category;

    private double price;

    private LocalDateTime date;

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
