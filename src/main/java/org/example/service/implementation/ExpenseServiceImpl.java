package org.example.service.implementation;

import org.example.converters.CategoriesAndPrice;
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


@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
    @Override
    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }
    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    @Override
    public void update(Long id, ExpenseCategory category, Double price, LocalDateTime dateTime) {
        expenseRepository.update(id, category.toString(), price, dateTime);

    }
    @Override
    public Long delete(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (!expense.isPresent()) {
            throw new RuntimeException("Could not find this Expense");
        }
        expenseRepository.delete(expense.get());
        return id;
    }

    @Override
    public Expense getExpense(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (!expense.isPresent()) {
            throw new RuntimeException("Could not find this Expense");
        }
        return expense.get();
    }

    @Override
    public List<Expense> findAllByCategory(ExpenseCategory expenseCategory) {
        return expenseRepository.findAllByCategory(expenseCategory.toString());

    }

    @Override
    public List<CategoriesAndPrice> groupByCategory() {
        return expenseRepository.groupByCategory();
    }



}
