package org.example.repository;

import org.example.converters.CategoriesAndPrice;

import org.example.converters.CategoryPriceMonth;
import org.example.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Query(value = "SELECT * FROM expenses WHERE userid=:userid", nativeQuery = true)
    List<Expense> findAll(@Param("userid") UUID userid);


    @Query(value = "SELECT * FROM expenses WHERE category=:category AND userid=:userid", nativeQuery = true)
    List<Expense> findAllByCategory(@Param("userid") UUID userid, @Param("category") String category);

    @Query(value = "SELECT category as category, SUM(price) AS price FROM expenses WHERE userid=:userid GROUP BY category", nativeQuery = true)
    List<CategoriesAndPrice> groupByCategory(@Param("userid") UUID userid);

    @Query(value = "SELECT * FROM expenses WHERE userid=:userid AND id=:id", nativeQuery = true)
    Optional<Expense> findById(@Param("userid") UUID userid, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE expenses SET category=:category, price=:price, date=:date WHERE userid=:userid AND id=:id", nativeQuery = true)
    int update(@Param("userid") UUID userid, @Param("id") Long id, @Param("category") String category, @Param("price") Double price, @Param("date") LocalDateTime date);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO expenses (userid, category, price, date) values(:userid, :category, :price, :date)", nativeQuery = true)
    void insert(@Param("userid") UUID userid, @Param("category") String category, @Param("price") Double price, @Param("date") LocalDateTime date);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM expenses WHERE userid=:userid AND id=:id", nativeQuery = true)
    void deleteExp(@Param("userid") UUID userid, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "SELECT category as category, SUM(price) AS price, extract(month from date) as monthDate, extract(year from date) as yearDate, count(*) as count " +
            "FROM expenses WHERE userid=:userid GROUP BY category, monthDate, yearDate", nativeQuery = true)
    List<CategoryPriceMonth> groupByCategoryAndMonth(@Param("userid") UUID userid);


}
