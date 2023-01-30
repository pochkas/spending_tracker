package org.example;

import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.example.convertertoDB.CategoriesAndPriceInterface;
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
        expenseRepository.save(new Expense(ExpenseCategory.FOOD, 100, date1));
        expenseRepository.save(new Expense(ExpenseCategory.CAFE, 5000, date1));
        expenseRepository.save(new Expense(ExpenseCategory.CLOTHES, 100, date1));
        expenseRepository.save(new Expense(ExpenseCategory.FOOD, 100, date1));
        expenseRepository.save(new Expense(ExpenseCategory.FOOD, 100, date1));
        expenseRepository.save(new Expense(ExpenseCategory.FOOD, 100, date1));
        expenseRepository.save(new Expense(ExpenseCategory.APARTMENT, 11000, date1));
        expenseRepository.save(new Expense(ExpenseCategory.FOOD, 100, date1));
        expenseRepository.save(new Expense(ExpenseCategory.FOOD, 100, date1));

        List<Expense> expenses=new ArrayList<>();
        expenses=expenseRepository.findAll();


        System.out.println(expenses);
        System.out.println(expenseRepository.findAllByCategories());

        List<CategoriesAndPriceInterface> list = expenseRepository.groupByCategories();


        System.out.println("group by cat:"+ list.get(0).getPrice());

        //System.out.println(expenseRepository.findCategoriesWithMaxPrice());

    }


}
