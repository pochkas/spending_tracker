package org.example.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.example.model.ExpenseCategory;

import java.time.LocalDateTime;

public class ExpenseCreationDTO {

    private ExpenseCategory categories;

    private double price;

    private LocalDateTime date;

    public ExpenseCategory getCategories() {
        return categories;
    }

    public void setCategories(ExpenseCategory categories) {
        this.categories = categories;
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
