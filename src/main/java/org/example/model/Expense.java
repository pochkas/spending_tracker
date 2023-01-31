package org.example.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "expenses")
public class Expense {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public Expense(ExpenseCategory category, double price, LocalDateTime date) {

        this.category = category;
        this.price = price;
        this.date = date;
    }

    public Expense() {
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


    public String toString() {
        return getClass().getSimpleName() + ": " +getId()+", "+ getCategory() + ", " + getPrice() + ", " + getDate();
    }
}
