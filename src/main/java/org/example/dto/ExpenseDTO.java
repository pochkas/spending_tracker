package org.example.dto;

import jakarta.persistence.*;

import org.example.model.ExpenseCategory;

import java.time.LocalDateTime;


public class ExpenseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ExpenseCategory categories;

    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;


}
