package org.example.service;

import org.example.model.Expense;
import org.example.model.ExpenseCategory;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    public List<Expense> getAll();
    public List<Expense> findAll(int page, int size, String[] sort);

    public Expense addExpense(Expense expense);

    public Expense update(Expense expense);

    public Long delete(Long id);

    public Expense getExpense(Long id);

    public List<Expense> groupByCategory(ExpenseCategory expenseCategory);


}
