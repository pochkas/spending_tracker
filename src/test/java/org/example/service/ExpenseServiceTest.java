package org.example.service;


import org.example.converters.CategoriesAndPrice;
import org.example.converters.CategoryPriceMonth;
import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.example.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ExpenseServiceTest {

    @Autowired
    ExpenseRepository expenseRepository;

    @Test
    public void getAllSuccess() {
        expenseRepository.save(new Expense(ExpenseCategory.FOOD, 500.50, LocalDateTime.now()));
        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(1, foundEntity.size());
    }

    @Test
    public void insertSuccess() {

        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(0, foundEntity.size());

        expenseRepository.insert("FOOD", 500.50, LocalDateTime.now());
        List<Expense> foundEntity2 = expenseRepository.findAll();

        assertEquals(1, foundEntity2.size());

        assertEquals(foundEntity2.get(0).getCategory().toString(), "FOOD");
        assertEquals(foundEntity2.get(0).getPrice(), 500.50);

    }

    @Test
    public void findByIdSuccess() {
        LocalDateTime date = LocalDateTime.now();
        Expense expense = expenseRepository.save(new Expense(ExpenseCategory.FOOD, 500.50, date));
        expense.setId(1L);
        Expense expense2 = expenseRepository.save(new Expense(ExpenseCategory.FOOD, 500.50, date));
        expense2.setId(2L);


        Optional<Expense> expenseId2 = expenseRepository.findById(2L);


        assertEquals(2L, expense2.getId());
        assertEquals(expenseId2.get().getId(), expense2.getId());
        assertEquals(expenseId2.get().getCategory().toString(), expense2.getCategory().toString());
        assertEquals(expenseId2.get().getPrice(), expense2.getPrice());
        assertEquals(expenseId2.get().getDate(), expense2.getDate());



    }

    @Test
    public void updateSuccess() {


        LocalDateTime date = LocalDateTime.now();
        expenseRepository.insert("FOOD", 500.50, date);
        expenseRepository.update(1L, "OTHER", 600.60, date);
        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(1, foundEntity.size());
        assertEquals(foundEntity.get(0).getCategory().toString(), "OTHER");
        assertEquals(foundEntity.get(0).getPrice(), 600.60);
        assertEquals(foundEntity.get(0).getDate(), date);


    }

    @Test
    public void deleteSuccess() {

        LocalDateTime date = LocalDateTime.now();
        expenseRepository.insert("FOOD", 500.50, date);
        expenseRepository.deleteExp(1L);
        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(0, foundEntity.size());

    }

    @Test
    public void groupByCategorySuccess() {

        LocalDateTime date = LocalDateTime.now();
        expenseRepository.insert("FOOD", 500.0, date);
        expenseRepository.insert("FOOD", 500.0, date);
        expenseRepository.insert("FOOD", 500.0, date);


        expenseRepository.insert("OTHER", 500.0, date);
        expenseRepository.insert("OTHER", 500.0, date);

        expenseRepository.insert("FOOD", 500.0, date);


        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(6, foundEntity.size());

        List<CategoriesAndPrice> expensesGroupBy = expenseRepository.groupByCategory();
        assertEquals(2, expensesGroupBy.size());
        assertEquals(expensesGroupBy.get(0).getCategory(), "FOOD");
        assertEquals(expensesGroupBy.get(0).getPrice(), 2000.0);

        assertEquals(expensesGroupBy.get(1).getCategory(), "OTHER");
        assertEquals(expensesGroupBy.get(1).getPrice(), 1000.0);


    }

    @Test
    public void groupByCategoryMonthSuccess() {


        expenseRepository.insert("FOOD", 500.0, LocalDateTime.of(2023, 11, 11, 8, 45, 44));
        expenseRepository.insert("FOOD", 500.0, LocalDateTime.of(2023, 11, 11, 8, 45, 44));
        expenseRepository.insert("FOOD", 500.0, LocalDateTime.of(2023, 12, 11, 8, 45, 44));


        expenseRepository.insert("OTHER", 500.0, LocalDateTime.of(2023, 9, 11, 8, 45, 44));
        expenseRepository.insert("OTHER", 500.0, LocalDateTime.of(2023, 9, 11, 8, 45, 44));


        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(5, foundEntity.size());

        List<CategoryPriceMonth> expensesGroupBy = expenseRepository.groupByCategoryAndMonth();
        assertEquals(3, expensesGroupBy.size());
        assertEquals(expensesGroupBy.get(0).getCategory(), "FOOD");
        assertEquals(expensesGroupBy.get(0).getPrice(), 1000.0);
        assertEquals(expensesGroupBy.get(0).getYearDate(), 2023);
        assertEquals(expensesGroupBy.get(0).getMonthDate(), 11);


        assertEquals(expensesGroupBy.get(1).getCategory(), "FOOD");
        assertEquals(expensesGroupBy.get(1).getPrice(), 500.0);
        assertEquals(expensesGroupBy.get(1).getYearDate(), 2023);
        assertEquals(expensesGroupBy.get(1).getMonthDate(), 12);

        assertEquals(expensesGroupBy.get(2).getCategory(), "OTHER");
        assertEquals(expensesGroupBy.get(2).getPrice(), 1000.0);
        assertEquals(expensesGroupBy.get(2).getYearDate(), 2023);
        assertEquals(expensesGroupBy.get(2).getMonthDate(), 9);

    }


}
