package org.example.controller;

import org.example.dto.ExpenseDTO;
import org.example.service.ExpenseService;
import org.example.service.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private Mapper mapper;

    public ExpenseController(ExpenseService expenseService, Mapper mapper) {
        this.expenseService = expenseService;
        this.mapper = mapper;
    }

//    @GetMapping
//    @ResponseBody
//    public List<ExpenseDTO> getExpense() {
//        return expenseService.getAll()
//                .stream()
//                .map(mapper::toDTO)
//                .collect(toList());
//    }

    @GetMapping
    public ResponseEntity findAll(@RequestParam(required = false) String fines, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2147483647") int size, @RequestParam(defaultValue = "fineId,asc") String[] sort) {

        try {
            return ResponseEntity.ok(expenseService.findAll(page, size, sort));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Expenses have not been found");
        }

    }

    @PostMapping
    public ResponseEntity addExpense(ExpenseDTO expenseDTO) {
        try {

            ExpenseDTO expenseResponse = mapper.addExpense(expenseDTO);

            return new ResponseEntity<ExpenseDTO>(expenseResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Expense has not been added");
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody ExpenseDTO expenseDTO) {
        try {

            ExpenseDTO expenseResponse = mapper.update(expenseDTO);

            return new ResponseEntity<ExpenseDTO>(expenseResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Fine has not been updated");
        }

    }

    @DeleteMapping()
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

}


