package org.example.controller;

import org.example.dto.ExpenseCreationDTO;
import org.example.dto.ExpenseDTO;
import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.example.repository.ExpenseRepository;
import org.example.service.ExpenseService;
import org.example.service.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {


    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private Mapper mapper;

    public ExpenseController(ExpenseService expenseService, Mapper mapper) {
        this.expenseService = expenseService;
        this.mapper = mapper;
    }


    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String fines, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2147483647") int size, @RequestParam(defaultValue = "fineId,asc") String[] sort) {

        try {
            return ResponseEntity.ok(expenseService.findAll(page, size, sort));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Expenses have not been found");
        }

    }

    @PostMapping
    public ResponseEntity addExpense(@RequestBody ExpenseCreationDTO expenseDTO) {
        try {

            Expense expenseResponse = mapper.toExpense(expenseDTO);
            Long id = expenseService.addExpense(expenseResponse).getId();

            return new ResponseEntity(id, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Expense has not been added");
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ExpenseCreationDTO expenseDTO) {
        try {

            Expense expenseResponse = mapper.toExpense(expenseDTO);
            Long id = expenseService.update(expenseResponse).getId();

            return new ResponseEntity(id, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Fine has not been updated");
        }

    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Long id) {
        try {
            expenseService.delete(id);
            return ResponseEntity.ok("Expense was deleted");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Expense was not deleted");
        }


    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getExpense(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(expenseService.getExpense(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Expense has not been found");
        }

    }

    @GetMapping(value = "/{expenseCategory}")
    public ResponseEntity getExpenseByCategories(@PathVariable ExpenseCategory expenseCategory) {
        try {
            return ResponseEntity.ok(expenseRepository.findAllByCategories(expenseCategory.toString()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Expense has not been found");
        }

    }

}


