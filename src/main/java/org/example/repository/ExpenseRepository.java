package org.example.repository;

import org.example.converters.CategoriesAndPrice;
import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Query(value = "SELECT * FROM expenses", nativeQuery = true)
    List<Expense> findAll();


    @Query(value = "SELECT * FROM expenses WHERE categories=:categories",  nativeQuery = true)
    List<Expense> findAllByCategories(@Param("categories") String categories);

    @Query(value = "SELECT categories as category, SUM(price) AS price FROM expenses GROUP BY categories", nativeQuery = true)
    List<CategoriesAndPrice> groupByCategories();

    @Query(value = "SELECT * FROM expenses WHERE id=:id",
            nativeQuery = true)
    Optional<Expense> findById(@Param("id") Long id);
}
