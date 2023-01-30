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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory categories;

    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public Expense(ExpenseCategory categories, double price, LocalDateTime date) {

        this.categories = categories;
        this.price = price;
        this.date = date;
    }

    public Expense() {
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


    public String toString() {
        return getClass().getSimpleName() + ": " +getId()+", "+ getCategories() + ", " + getPrice() + ", " + getDate();
    }
}
