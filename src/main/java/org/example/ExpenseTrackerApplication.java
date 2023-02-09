package org.example;

import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.example.converters.CategoriesAndPrice;
import org.example.repository.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.example.model.ExpenseCategory.*;

@SpringBootApplication
public class ExpenseTrackerApplication {



    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }


}
