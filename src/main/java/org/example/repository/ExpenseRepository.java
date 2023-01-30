package org.example.repository;

import org.example.convertertoDB.CategoriesAndPriceInterface;
import org.example.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Query(value = "SELECT * FROM expenses", nativeQuery = true)
    List<Expense> findAll();


    @Query(value = "SELECT * FROM expenses WHERE categories=4", nativeQuery = true)
    List<Expense> findAllByCategories();

    @Query(value = "SELECT categories as category, SUM(price) AS price FROM expenses GROUP BY categories", nativeQuery = true)
    List<CategoriesAndPriceInterface> groupByCategories();

//    @Query(value = "SELECT categories FROM expenses GROUP BY ORDER BY expenses.price DESC LIMIT 1", nativeQuery = true)
//    String findCategoriesWithMaxPrice();








}
