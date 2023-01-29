package org.example.repository;

import org.example.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Query(value = "SELECT * FROM expenses", nativeQuery = true)
    List<Expense> findAll();

    @Query(value = "SELECT * FROM expenses WHERE categories=2", nativeQuery = true)
    List<Expense> findAllByCategories();






}
