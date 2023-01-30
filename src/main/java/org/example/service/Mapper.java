package org.example.service;

import org.example.dto.ExpenseCreationDTO;
import org.example.dto.ExpenseDTO;
import org.example.model.Expense;


public interface Mapper {

    public ExpenseDTO toDTO(Expense expense);

    public Expense toExpense(ExpenseCreationDTO expenseDTO);



}
