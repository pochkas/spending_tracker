package org.example.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.example.dto.ExpenseDTO;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return category == expense.category && Double.compare(expense.price, price) == 0  &&  Objects.equals(date, expense.date);
    }

    public ExpenseDTO toDTO(Expense expense) {

        ExpenseDTO dto = new ExpenseDTO();

        dto.setCategory(expense.getCategory());
        dto.setPrice(expense.getPrice());
        dto.setDate(expense.getDate());

        return dto;
    }


}
