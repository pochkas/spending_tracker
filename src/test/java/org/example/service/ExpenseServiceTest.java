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
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ExpenseServiceTest {

    static LocalDateTime date = LocalDateTime.of(2023, 11, 11, 8, 45, 44);

    @Autowired
    ExpenseRepository expenseRepository;

    UUID userid = UUID.randomUUID();

    @Test
    public void findByIdSuccess() {

        Expense expense2 = expenseRepository.save(new Expense(userid, ExpenseCategory.OTHER, 500.50, date));

        Expense expenseId2 = expenseRepository.findById(expense2.getId()).get();
        assertEquals(expenseId2, expense2);
    }

    @Test
    public void getAllSuccess() {

        expenseRepository.save( new Expense(userid, ExpenseCategory.FOOD, 500.50, date));
        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(1, foundEntity.size());
    }

    @Test
    public void insertSuccess() {

        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(0, foundEntity.size());

        expenseRepository.insert(userid,"FOOD", 500.50, LocalDateTime.now());

        List<Expense> foundEntity2 = expenseRepository.findAll();

        assertEquals(1, foundEntity2.size());

        assertEquals(foundEntity2.get(0).getCategory().toString(), "FOOD");
        assertEquals(foundEntity2.get(0).getPrice(), 500.50);

    }



    @Test
    public void deleteSuccess() {

        expenseRepository.save( new Expense(userid, 1L, ExpenseCategory.FOOD, 500.50, date));
        expenseRepository.deleteExp(userid,1L);
        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(0, foundEntity.size());

    }

    @Test
    public void groupByCategorySuccess() {

        expenseRepository.insert(userid,"FOOD", 500.0, date);
        expenseRepository.insert(userid,"FOOD", 500.0, date);
        expenseRepository.insert(userid,"FOOD", 500.0, date);


        expenseRepository.insert(userid,"OTHER", 500.0, date);
        expenseRepository.insert(userid,"OTHER", 500.0, date);

        expenseRepository.insert(userid,"FOOD", 500.0, date);


        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(6, foundEntity.size());

        List<CategoriesAndPrice> expensesGroupBy = expenseRepository.groupByCategory(userid);
        assertEquals(2, expensesGroupBy.size());
        assertEquals(expensesGroupBy.get(0).getCategory(), "FOOD");
        assertEquals(expensesGroupBy.get(0).getPrice(), 2000.0);

        assertEquals(expensesGroupBy.get(1).getCategory(), "OTHER");
        assertEquals(expensesGroupBy.get(1).getPrice(), 1000.0);


    }

    @Test
    public void getByCategory() {

        expenseRepository.insert(userid,"FOOD", 500.0, date);
        expenseRepository.insert(userid,"FOOD", 600.0, date);
        expenseRepository.insert(userid,"FOOD", 500.0, date);


        expenseRepository.insert(userid,"OTHER", 500.0, date);
        expenseRepository.insert(userid,"OTHER", 500.0, date);

        expenseRepository.insert(userid,"FOOD", 500.0, date);


        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(6, foundEntity.size());

        List<Expense> expensesGetByCategory = expenseRepository.findAllByCategory(userid, "FOOD");
        assertEquals(4, expensesGetByCategory.size());
        assertEquals(expensesGetByCategory.get(0).getCategory().toString(), "FOOD");
        assertEquals(expensesGetByCategory.get(0).getPrice(), 500.0);




    }

    @Test
    public void groupByCategoryMonthSuccess() {

        expenseRepository.insert(userid,"FOOD", 500.0, LocalDateTime.of(2023, 11, 11, 8, 45, 44));
        expenseRepository.insert(userid,"FOOD", 500.0, LocalDateTime.of(2023, 11, 11, 8, 45, 44));
        expenseRepository.insert(userid,"FOOD", 500.0, LocalDateTime.of(2023, 12, 11, 8, 45, 44));


        expenseRepository.insert(userid,"OTHER", 500.0, LocalDateTime.of(2023, 9, 11, 8, 45, 44));
        expenseRepository.insert(userid,"OTHER", 500.0, LocalDateTime.of(2023, 9, 11, 8, 45, 44));


        List<Expense> foundEntity = expenseRepository.findAll();
        assertEquals(5, foundEntity.size());

        List<CategoryPriceMonth> expensesGroupBy = expenseRepository.groupByCategoryAndMonth(userid);
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
