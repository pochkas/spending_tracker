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

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;


    @GetMapping(value = "/{userid}")
    public ResponseEntity getAll(@PathVariable UUID userid) {
        return ResponseEntity.ok(expenseService.getAll(userid));
    }


    @PostMapping(value = "/{userid}")
    public ResponseEntity addExpense(@PathVariable UUID userid, @RequestBody ExpenseCreationDTO expenseDTO) {
        Expense expenseResponse = expenseDTO.toExpense(userid);
        Long id = expenseService.addExpense(expenseResponse).getId();
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userid}/{id}")
    public ResponseEntity update(@PathVariable UUID userid, @PathVariable Long id, @RequestBody ExpenseCreationDTO expenseDTO) {
        expenseService.update(userid, id, expenseDTO.getCategory(), expenseDTO.getPrice(), expenseDTO.getDate());
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userid}/{id}")
    public ResponseEntity delete(@PathVariable UUID userid, @PathVariable Long id) {
        expenseService.delete(userid, id);
        return ResponseEntity.ok("Expense was deleted");
    }

    @GetMapping(value = "/{userid}/{id}")
    public ResponseEntity getExpense(@PathVariable UUID userid, @PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpense(userid, id));
    }

    @GetMapping(value = "/{userid}/byCategory/{expenseCategory}")
    public ResponseEntity getExpenseByCategories(@PathVariable UUID userid, @PathVariable ExpenseCategory expenseCategory) {
        return ResponseEntity.ok(expenseService.findAllByCategory(userid, expenseCategory));
    }

    @GetMapping(value = "/{userid}/groupByCategory")
    public ResponseEntity groupExpenseByCategories(@PathVariable UUID userid) {
        return ResponseEntity.ok(expenseService.groupByCategory(userid));
    }

    @GetMapping(value = "/{userid}/groupByCategoryAndMonth")
    public ResponseEntity groupExpenseByCategoryAndMonth(@PathVariable UUID userid) {
        return ResponseEntity.ok(expenseService.groupByCategoryAndMonth(userid));
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


