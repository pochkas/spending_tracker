package org.example.service;

import org.example.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MappingService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ExpenseService expenseService;


}
