package org.example.dto;

import org.example.model.Expense;
import org.example.model.ExpenseCategory;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExpenseCreationDTO {

    private ExpenseCategory category;

    private double price;

    private LocalDateTime date;

    public ExpenseCreationDTO(ExpenseCategory category, double price, LocalDateTime date) {
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public ExpenseCreationDTO(){

    }

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

    public Expense toExpense(UUID userId) {
        return new Expense(userId, getCategory(), getPrice(), getDate());
    }


}
