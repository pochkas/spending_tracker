package org.example.model;

public enum ExpenseCategory {

    SHOP("SHOP"),
    CLOTHES("CLOTHES"),
    FOOD("FOOD"),
    CAFE("CAFE"),
    APARTMENT("APARTMENT"),
    INVESTMENT("INVESTMENT"),
    TECHNICS("TECHNICS"),
    AUTO("AUTO"),
    TRAVEL("TRAVEL"),
    TRANSPORT("TRANSPORT"),
    OTHER("OTHER");

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
