package org.example.repository;

import org.example.converters.CategoriesAndPrice;

import org.example.model.Expense;
import org.example.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Query(value = "SELECT * FROM expenses", nativeQuery = true)
    List<Expense> findAll();


    @Query(value = "SELECT * FROM expenses WHERE category=:category",  nativeQuery = true)
    List<Expense> findAllByCategory(@Param("category") String category);

    @Query(value = "SELECT category as category, SUM(price) AS price FROM expenses GROUP BY category", nativeQuery = true)
    List<CategoriesAndPrice> groupByCategory();


    @Query(value = "SELECT * FROM expenses WHERE id=:id",
            nativeQuery = true)
    Optional<Expense> findById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE expenses SET category=:category, price=:price, date=:date WHERE id=:id", nativeQuery = true)
    void update(@Param("id") Long id, @Param("category") String category, @Param("price") Double price, @Param("date") LocalDateTime date);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO expenses (category, price, date) values(:category, :price, :date)", nativeQuery = true)
    void insert(@Param("category") String category, @Param("price") Double price , @Param("date")LocalDateTime date);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM expenses WHERE id=:id", nativeQuery = true)
    void deleteExp(@Param("id") Long id);
}
