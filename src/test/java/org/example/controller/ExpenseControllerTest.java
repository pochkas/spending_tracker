package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.converters.CategoriesAndPrice;
import org.example.converters.CategoryPriceMonth;
import org.example.exception.ExpenseException;
import org.example.exception.UserFacingException;
import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.example.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ExpenseController.class)
public class ExpenseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ExpenseService expenseService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllSuccess() throws Exception {


        mockMvc.perform(get("/expenses")
                        .contentType("application/json"))
                .andExpect(status().isOk());

        verify(expenseService, times(1)).getAll();
    }

    @Test
    void addSuccess() throws Exception {

        LocalDateTime dateTime = LocalDateTime.now();
        Expense expense = new Expense(ExpenseCategory.APARTMENT, 500.50, dateTime);

        when(expenseService.addExpense(eq(new Expense(ExpenseCategory.APARTMENT, 500.50, dateTime)))).thenReturn(expense);

        mockMvc.perform(post("/expenses")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isCreated());

        verify(expenseService, times(1)).addExpense(expense);
    }

    @Test
    void getByIdSuccess() throws Exception {

        Long id = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        Expense expense = new Expense(ExpenseCategory.APARTMENT, 500.50, dateTime);
        expense.setId(1L);


        when(expenseService.getExpense(eq(id))).thenReturn(expense);

        mockMvc.perform(get("/expenses/{id}", 1L)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.category").value(ExpenseCategory.APARTMENT.toString()))
                .andExpect(jsonPath("$.price").value(500.50))
                .andExpect(jsonPath("$.date").value(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

        verify(expenseService, times(1)).getExpense(id);

    }

    @Test
    void findAllByCategorySuccess() throws Exception {

        ExpenseCategory category = ExpenseCategory.FOOD;
        LocalDateTime dateTime = LocalDateTime.now();
        Expense expense = new Expense(ExpenseCategory.FOOD, 500.50, dateTime);
        List<Expense> list = new ArrayList<>();
        list.add(expense);

        when(expenseService.findAllByCategory(eq(category))).thenReturn(list);

        mockMvc.perform(get("/expenses/byCategory/{expenseCategory}", ExpenseCategory.FOOD)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value(category.toString()))
                .andExpect(jsonPath("$[0].price").value(500.50))
                .andExpect(jsonPath("$[0].date").value(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

        verify(expenseService, times(1)).findAllByCategory(category);

    }

    @Test
    void groupByCategorySuccess() throws Exception {

        List<CategoriesAndPrice> list = new ArrayList<>();
        list.add(new CategoriesAndPrice() {
            @Override
            public String getCategory() {
                return ExpenseCategory.FOOD.toString();
            }

            @Override
            public Double getPrice() {
                return 500.50;
            }
        });

        when(expenseService.groupByCategory()).thenReturn(list);

        mockMvc.perform(get("/expenses/groupByCategory")
                        .contentType("application/json"))
                .andExpect(jsonPath("$[0].category").value("FOOD"))
                .andExpect(jsonPath("$[0].price").value(500.50))
                .andExpect(status().isOk());


        verify(expenseService, times(1)).groupByCategory();

    }

    @Test
    void updateSuccess() throws Exception {

        Expense expense = new Expense(ExpenseCategory.FOOD, 500.50, null);
        expense.setId(1L);

        doNothing().when(expenseService).update(eq(1l), eq(ExpenseCategory.FOOD), eq(500.50), eq(null));

        mockMvc.perform(put("/expenses/{id}", 1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(expense)))
                        .andExpect(status().isOk());

        verify(expenseService, times(1)).update(1L, ExpenseCategory.FOOD, 500.50, null);

    }

    @Test
    void deleteSuccess() throws Exception {

        LocalDateTime dateTime = LocalDateTime.now();
        Expense expense = new Expense(ExpenseCategory.APARTMENT, 500.50, dateTime);
        expense.setId(1L);
        Long id = 1L;

        when(expenseService.delete(eq(id))).thenReturn(id);

        mockMvc.perform(delete("/expenses/{id}", 1L)
                        .contentType("application/json"))
                        .andExpect(status().isOk());

        verify(expenseService, times(1)).delete(id);



    }

    @Test
    void groupExpenseByCategoryAndMonthSuccess() throws Exception {

        List<CategoryPriceMonth> list = new ArrayList<>();
        list.add(new CategoryPriceMonth() {
            @Override
            public String getCategory() {
                return ExpenseCategory.FOOD.toString();
            }

            @Override
            public Double getPrice() {
                return 500.50;
            }

            @Override
            public int getMonthDate() {
                return 11;
            }

            @Override
            public int getYearDate() {
                return 2023;
            }
        });

        when(expenseService.groupByCategoryAndMonth()).thenReturn(list);

        mockMvc.perform(get("/expenses/groupByCategoryAndMonth")
                        .contentType("application/json"))
                .andExpect(jsonPath("$[0].category").value("FOOD"))
                .andExpect(jsonPath("$[0].price").value(500.50))
                .andExpect(jsonPath("$[0].monthDate").value(11))
                .andExpect(jsonPath("$[0].yearDate").value(2023))
                .andExpect(status().isOk());


        verify(expenseService, times(1)).groupByCategoryAndMonth();

    }


    @Test
    void getAllError() throws Exception {
        when(expenseService.getAll()).thenThrow(new ExpenseException("Error", 1L));

        mockMvc.perform(get("/expenses")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());

        verify(expenseService, times(1)).getAll();
    }
}
