package org.example.model;

public enum ExpenseCategory {

    SHOP("Shop"),
    CLOTHES("Clothes"),
    FOOD("Food"),
    CAFE("Cafe"),
    APARTMENT("Apartment"),
    INVESTMENT("Investment"),
    TECHNICS("Technics"),
    AUTO("Auto"),
    TRAVEL("Travel"),
    TRANSPORT("Transport"),
    OTHER("Other");

    String categoryName;
    ExpenseCategory(String categoryName) {
        this.categoryName = categoryName;
    }
    //override the inherited method
    @Override
    public String toString() {
        return categoryName;
    }




}
