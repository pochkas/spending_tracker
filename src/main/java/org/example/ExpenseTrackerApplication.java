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
        expenseRepository.insert(FOOD.toString(), 100.0, date1);
        expenseRepository.insert(CAFE.toString(), 5000.0, date1);
        expenseRepository.insert(CLOTHES.toString(), 100.0, date1);
        expenseRepository.insert(FOOD.toString(), 500.0, date1);
        expenseRepository.insert(FOOD.toString(), 100.0, date1);
        expenseRepository.insert(FOOD.toString(), 100.0, date1);
        expenseRepository.insert(APARTMENT.toString(), 11000.0, date1);
        expenseRepository.insert(FOOD.toString(), 100.0, date1);
        expenseRepository.insert(FOOD.toString(), 100.0, date1);

        List<Expense> expenses=new ArrayList<>();
        expenses=expenseRepository.findAll();

        System.out.println(expenseRepository.findById(4L));

        System.out.println(expenses);
        System.out.println(expenseRepository.findAllByCategory(FOOD.toString()));

        List<CategoriesAndPrice> list = expenseRepository.groupByCategory();


        System.out.println("Group by category. "+list.get(0).getCategory()+": "+ list.get(0).getPrice());

        expenseRepository.deleteExp(1L);

        System.out.println(expenseRepository.findAll());

        expenseRepository.update(2L, OTHER.toString(), 700.0, date1);
        System.out.println(expenseRepository.findAll());
        
        


    }


}
