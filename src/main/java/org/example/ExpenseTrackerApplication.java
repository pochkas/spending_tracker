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
public class ExpenseTrackerApplication implements CommandLineRunner {

    @Autowired
    ExpenseRepository expenseRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        System.out.println(expenseRepository.findAll());

        LocalDateTime date1 = LocalDateTime.now();
        expenseRepository.save(new Expense(FOOD, 100, date1));
        expenseRepository.save(new Expense(CAFE, 5000, date1));
        expenseRepository.save(new Expense(CLOTHES, 100, date1));
        expenseRepository.save(new Expense(FOOD, 500, date1));
        expenseRepository.save(new Expense(FOOD, 100, date1));
        expenseRepository.save(new Expense(FOOD, 100, date1));
        expenseRepository.save(new Expense(APARTMENT, 11000, date1));
        expenseRepository.save(new Expense(FOOD, 100, date1));
        expenseRepository.save(new Expense(FOOD, 100, date1));

        List<Expense> expenses=new ArrayList<>();
        expenses=expenseRepository.findAll();

        System.out.println(expenseRepository.findById(4L));

        System.out.println(expenses);
        System.out.println(expenseRepository.findAllByCategories(FOOD.toString()));

        List<CategoriesAndPrice> list = expenseRepository.groupByCategories();


        System.out.println("Group by category. "+list.get(0).getCategory()+": "+ list.get(0).getPrice());



    }


}
