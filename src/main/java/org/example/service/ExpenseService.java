package org.example.service;

import org.example.converters.CategoriesAndPrice;
import org.example.converters.CategoryPriceMonth;
import org.example.model.Expense;
import org.example.model.ExpenseCategory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseService {

    public List<Expense> getAll(UUID userid);

    public Expense addExpense(Expense expense);

    public int update(UUID userid, Long id, ExpenseCategory category, Double price, LocalDateTime dateTime);

    public Long delete(UUID userid, Long id);

    public Expense getExpense(UUID userid, Long id);

    public List<Expense> findAllByCategory(UUID userid, ExpenseCategory expenseCategory);

    public List<CategoriesAndPrice> groupByCategory(UUID userid);

    public List<CategoryPriceMonth> groupByCategoryAndMonth(UUID userid);




}
