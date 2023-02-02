package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ExpenseCreationDTO;
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

        try {
            return ResponseEntity.ok(expenseService.getAll());
        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error. Expenses have not been found");
        }

    }


    @PostMapping
    public ResponseEntity addExpense(@RequestBody ExpenseCreationDTO expenseDTO) {
        try {


            Expense expenseResponse = expenseDTO.toExpense();
            Long id = expenseService.addExpense(expenseResponse).getId();

            return new ResponseEntity(id, HttpStatus.CREATED);

        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error. Expense has not been added");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ExpenseCreationDTO expenseDTO) {
        try {
            expenseService.update(id, expenseDTO.getCategory(), expenseDTO.getPrice(), expenseDTO.getDate());

            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error. Expense has not been updated");
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            expenseService.delete(id);
            return ResponseEntity.ok("Expense was deleted");

        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error. Expense was not deleted");
        }


    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getExpense(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(expenseService.getExpense(id));
        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error. Expense has not been found");
        }

    }

    @GetMapping(value = "/byCategory/{expenseCategory}")
    public ResponseEntity getExpenseByCategories(@PathVariable ExpenseCategory expenseCategory) {
        try {
            return ResponseEntity.ok(expenseService.findAllByCategory(expenseCategory));
        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error. Expense has not been found");
        }

    }

    @GetMapping(value = "/groupByCategory")
    public ResponseEntity groupExpenseByCategories() {
        try {
            return ResponseEntity.ok(expenseService.groupByCategory());
        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error. Expenses have not been found");
        }

    }

}


