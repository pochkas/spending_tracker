package org.example.service.implementation;

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

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    public List<Expense> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2147483647") int size, @RequestParam(defaultValue = "id,asc") String[] sort) {

        PageRequest pr = PageRequest.of(page, size);
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        return expenseRepository.findAll(pr.of(page, size, (Sort.by(orders)))).toList();

    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense update(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    public Long delete(@RequestBody Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (!expense.isPresent()) {
            throw new RuntimeException("Could not find this Expense");
        }
        expenseRepository.delete(expense.get());
        return id;
    }

    public Expense getExpense(@PathVariable Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (!expense.isPresent()) {
            throw new RuntimeException("Could not find this Expense");
        }
        return expense.get();
    }

    public List<Expense> groupByCategory(@PathVariable ExpenseCategory expenseCategory) {
        return expenseRepository.findAllByCategory(expenseCategory.toString());

    }


}
