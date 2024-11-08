package com.example.springdogless.dao;

import com.example.springdogless.entity.Meal;
import com.example.springdogless.entity.MealResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MealDao {

    public List<Meal> listarComidas() {
        RestTemplate restTemplate = new RestTemplate();
        MealResponse response = restTemplate.getForObject("https://www.themealdb.com/api/json/v1/1/categories.php", MealResponse.class);
        List<Meal> meals = response.getCategories();

        return meals;
    }

    public List<Meal> buscarComidaPorNombre(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        MealResponse response = restTemplate.getForObject("https://www.themealdb.com/api/json/v1/1/categories.php", MealResponse.class);
        List<Meal> meals = response.getCategories();

        return meals.stream()
                .filter(meal -> meal.getStrCategory().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }
}
