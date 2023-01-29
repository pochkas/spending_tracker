package org.example.model;


import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ExpenseCategory categories;

    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public Long getId(){
        return id;
    }

    public ExpenseCategory getCategories(){
        return categories;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setCategories(ExpenseCategory categories) {
        this.categories = categories;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(LocalDateTime date){
        this.date=date;
    }


}
