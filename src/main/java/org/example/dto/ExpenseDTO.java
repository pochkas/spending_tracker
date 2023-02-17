package org.example.dto;

import org.example.model.ExpenseCategory;

import java.time.LocalDateTime;
import java.util.UUID;


public class ExpenseDTO {

    private ExpenseCategory category;

    private double price;

    private LocalDateTime date;

    public ExpenseDTO(UUID userid, ExpenseCategory category, double price, LocalDateTime date) {
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public ExpenseDTO(){

    }

    @Override
    public String toString() {
        return "ExpenseDTO{" +
                ", categories=" + category +
                ", price=" + price +
                ", date=" + date +
                '}';
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

    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (!(o instanceof ExpenseDTO)) {
            return false;
        }
        ExpenseDTO ex = (ExpenseDTO) o;
        return category.equals(ex.category) && (Double.compare(getPrice(), ex.getPrice())==0) && getDate().equals(ex.getDate());
    }
}
