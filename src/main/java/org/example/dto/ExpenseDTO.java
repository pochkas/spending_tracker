package org.example.dto;

import jakarta.persistence.*;
import org.example.model.ExpenseCategory;

import java.time.LocalDateTime;


public class ExpenseDTO {

    private ExpenseCategory categories;

    private double price;

    private LocalDateTime date;



    @Override
    public String toString() {
        return "ExpenseDTO{" +
                ", categories=" + categories +
                ", price=" + price +
                ", date=" + date +
                '}';
    }




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

    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (!(o instanceof ExpenseDTO)) {
            return false;
        }
        ExpenseDTO ex = (ExpenseDTO) o;
        return categories.equals(ex.categories) && (Double.compare(getPrice(), ex.getPrice())==0) && getDate().equals(ex.getDate());
    }
}
