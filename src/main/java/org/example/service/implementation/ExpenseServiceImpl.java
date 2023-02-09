package org.example.service.implementation;

import org.example.converters.CategoriesAndPrice;
import org.example.converters.CategoryPriceMonth;
import org.example.exception.ExpenseException;
import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.example.repository.ExpenseRepository;
import org.example.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAll(UUID userId) {
        return expenseRepository.findAll(userId);
    }
    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    @Override
    public int update(UUID userId, Long id, ExpenseCategory category, Double price, LocalDateTime dateTime) {
        int row=expenseRepository.update(userId, id, category.toString(), price, dateTime);
        if(row==0){
            throw new ExpenseException("Error, expense was not found.", id);
        }
        return row;
    }
    @Override
    public Long delete(UUID userId, Long id) {
        Optional<Expense> expense = expenseRepository.findById(userId, id);
        if (!expense.isPresent()) {
            throw new ExpenseException("Could not find this Expense.", id);
        }
        expenseRepository.delete(expense.get());
        return id;
    }

    @Override
    public Expense getExpense(UUID userId, Long id) {
        Optional<Expense> expense = expenseRepository.findById(userId, id);
        if (!expense.isPresent()) {
            throw new ExpenseException("Could not find this Expense.", id);
        }
        return expense.get();
    }

    @Override
    public List<Expense> findAllByCategory(UUID userId ,ExpenseCategory expenseCategory) {
        return expenseRepository.findAllByCategory(userId, expenseCategory.toString());

    }

    @Override
    public List<CategoriesAndPrice> groupByCategory(UUID userId) {
        return expenseRepository.groupByCategory(userId);
    }

    @Override
    public List<CategoryPriceMonth> groupByCategoryAndMonth(UUID userId){ return expenseRepository.groupByCategoryAndMonth(userId);}



}
