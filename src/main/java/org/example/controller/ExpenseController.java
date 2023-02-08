package org.example.controller;

import lombok.extern.slf4j.Slf4j;

import org.example.dto.ExpenseCreationDTO;
import org.example.exception.ExpenseException;
import org.example.exception.UserFacingException;
import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.example.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;


    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(expenseService.getAll());
    }


    @PostMapping
    public ResponseEntity addExpense(@RequestBody ExpenseCreationDTO expenseDTO) {
        Expense expenseResponse = expenseDTO.toExpense();
        Long id = expenseService.addExpense(expenseResponse).getId();
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ExpenseCreationDTO expenseDTO) {
        expenseService.update(id, expenseDTO.getCategory(), expenseDTO.getPrice(), expenseDTO.getDate());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.ok("Expense was deleted");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }

    @GetMapping(value = "/byCategory/{expenseCategory}")
    public ResponseEntity getExpenseByCategories(@PathVariable ExpenseCategory expenseCategory) {
        return ResponseEntity.ok(expenseService.findAllByCategory(expenseCategory));
    }

    @GetMapping(value = "/groupByCategory")
    public ResponseEntity groupExpenseByCategories() {
        return ResponseEntity.ok(expenseService.groupByCategory());
    }

    @GetMapping(value = "/groupByCategoryAndMonth")
    public ResponseEntity groupExpenseByCategoryAndMonth() {
        return ResponseEntity.ok(expenseService.groupByCategoryAndMonth());
    }


    @ExceptionHandler(UserFacingException.class)
    public ResponseEntity handleException(UserFacingException e) {
        log.error("Error occurred: " + e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleExceptionForOther(Exception e) {
        log.error("Error occurred: " + e.getMessage());
        return ResponseEntity.internalServerError().body("Sorry, try again later.");
    }

}


