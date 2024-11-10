package com.example.springdogless.dao;

import com.example.springdogless.entity.Detail;
import com.example.springdogless.entity.DetailResponse;
import com.example.springdogless.entity.Meal;
import com.example.springdogless.entity.MealResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealDao {

    public List<Meal> listarComidas() {
        RestTemplate restTemplate = new RestTemplate();
        MealResponse response = restTemplate.getForObject("https://www.themealdb.com/api/json/v1/1/categories.php", MealResponse.class);
        List<Meal> meals = response.getCategories();

        return meals;
    }

    public List<Meal> buscarComidaPorNombre(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        MealResponse response = restTemplate.getForObject("https://www.themealdb.com/api/json/v1/1/search.php?s=X", MealResponse.class);
        List<Meal> meals = response.getCategories();

        return meals.stream()
                .filter(meal -> meal.getStrCategory().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    //Lista Detail
    public List<Detail> listarDetail() {
        RestTemplate restTemplate = new RestTemplate();
        DetailResponse response = restTemplate.getForObject("https://www.themealdb.com/api/json/v1/1/search.php?s=X", DetailResponse.class);
        List<Detail> meals = response.getMeals();
        System.out.println("Detalles obtenidos: " + meals); // Imprime los detalles

        return meals;
    }





    //Detalle en Detail
    public Detail verDetallePorNombre(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        DetailResponse response = restTemplate.getForObject("https://www.themealdb.com/api/json/v1/1/search.php?s=" + nombre, DetailResponse.class);

        System.out.println("Respuesta de la API: " + response);
        if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
            return response.getMeals().stream()
                    .filter(detail -> detail.getStrMeal().equalsIgnoreCase(nombre))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    //Buscador en Detail
    public List<Detail> buscarComidaPorNombreEnDetail(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + nombre;
        DetailResponse response = restTemplate.getForObject(url, DetailResponse.class);

        if (response != null && response.getMeals() != null) {
            return response.getMeals();
        }

        return new ArrayList<>();
    }

    //Busqueda por nombre para favoritos
    public List<Detail> buscarComidaParaFavoritos(String nombre) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + nombre;
        DetailResponse response = restTemplate.getForObject(url, DetailResponse.class);

        if (response != null && response.getMeals() != null) {
            List<Detail> meals = response.getMeals();
            return meals.stream()
                    .filter(meal -> meal.getStrCategory().toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
