package org.example.service.implementation;

import org.example.dto.ExpenseCreationDTO;
import org.example.dto.ExpenseDTO;
import org.example.model.Expense;
import org.example.repository.ExpenseRepository;
import org.example.service.ExpenseService;
import org.example.service.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ExpenseDTO toDTO(Expense expense) {

        ExpenseDTO dto = new ExpenseDTO();

        dto.setCategories(expense.getCategories());
        dto.setPrice(expense.getPrice());
        dto.setDate(expense.getDate());

        return dto;
    }

    @Override
    public Expense toExpense(ExpenseCreationDTO expenseCreationDTO) {
        return new Expense(expenseCreationDTO.getCategories(), expenseCreationDTO.getPrice(), expenseCreationDTO.getDate());
    }


}
