package org.example.service;

import org.example.converters.CategoriesAndPrice;
import org.example.model.Expense;
import org.example.model.ExpenseCategory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    public List<Expense> getAll();

    public Expense addExpense(Expense expense);

    public void update(Long id, ExpenseCategory category, Double price, LocalDateTime dateTime);

    public Long delete(Long id);

    public Expense getExpense(Long id);

    public List<Expense> findAllByCategory(ExpenseCategory expenseCategory);

    public List<CategoriesAndPrice> groupByCategory();




}
