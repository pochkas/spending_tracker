package org.example.service;

import org.example.model.Expense;
import org.example.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ExpenseService {

    public List<Expense> getAll();
    public List<Expense> findAll(int page, int size, String[] sort);

    public Expense addExpense(Expense expense);

    public Expense update(Expense expense);

    public Long delete(Long id);

    public Expense getExpense(Long id);


}
