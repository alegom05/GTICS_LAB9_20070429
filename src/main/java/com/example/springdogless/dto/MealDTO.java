package com.example.springdogless.dto;

import com.example.springdogless.entity.Meal;

import java.util.List;

public class MealDTO {

    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
