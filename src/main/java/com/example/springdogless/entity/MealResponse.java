package com.example.springdogless.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MealResponse {
    private List<Meal> categories;
}