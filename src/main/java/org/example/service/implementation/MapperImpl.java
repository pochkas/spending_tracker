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

    public List<ExpenseDTO> getAllFine(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2147483647") int size, @RequestParam(defaultValue = "id,asc") String[] sort) {

        return ((List<Expense>) expenseService
                .findAll(page, size, sort))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {

        Expense expenseRequest = modelMapper.map(expenseDTO, Expense.class);

        Expense expense = expenseService.addExpense(expenseRequest);

        expenseRepository.save(expense);

        ExpenseDTO expenseResponse = modelMapper.map(expense, ExpenseDTO.class);

        return expenseResponse;

    }

    public ExpenseDTO update(@RequestBody ExpenseDTO expenseDTO){

        Expense expenseRequest = modelMapper.map(expenseDTO, Expense.class);
        Expense expense=expenseService.update(expenseRequest);
        ExpenseDTO expenseResponse=modelMapper.map(expense, ExpenseDTO.class);
        return expenseResponse;

    }

    public Long delete(@PathVariable Long id){
        Expense expense=expenseRepository.findById(id).get();
        if (expense == null) {
            throw new RuntimeException("Could not find this Expense");
        }
        expenseRepository.deleteById(id);
        return  id;
    }

    public Expense getDto(@PathVariable Long id){
        Expense expense=expenseRepository.findById(id).get();
        if (expense == null) {
            throw new RuntimeException("Could not find this Expense");
        }
        return expenseRepository.findById(id).get();
    }


}
