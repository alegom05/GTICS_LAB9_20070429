package com.example.springdogless.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeal;
    private String strMeal;
    private String strDrinkAlternate;
    private String strCategory;

    private String strArea;
    private String strTags;
    private String strInstructions;
    private String strMealThumb;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private Integer favorite;



}
