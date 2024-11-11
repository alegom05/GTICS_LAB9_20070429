package com.example.springdogless.controllers;

import com.example.springdogless.Repository.ComidaRepository;
import com.example.springdogless.dao.MealDao;
import com.example.springdogless.dto.MealDTO;
import com.example.springdogless.entity.Comida;
import com.example.springdogless.entity.Detail;
import com.example.springdogless.entity.DetailResponse;
import com.example.springdogless.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({ "", "/"})
public class MealController {

    @Autowired
    MealDao mealDao;
    @Autowired
    ComidaRepository comidaRepository;

    //Lista de meal
    @GetMapping({"/meal", "", "/"})
    public String listaComidas(Model model) {
        model.addAttribute("listaComidas", mealDao.listarComidas());
        return "category/list";
    }

    //Buscador de categorias
    @GetMapping("/buscarCategoria")
    public String buscarCategoria(@RequestParam("nombre") String nombre, Model model) {
        List<Meal> comidas = mealDao.buscadorCategoria(nombre);

        if (!comidas.isEmpty()) {
            model.addAttribute("listaComidas", comidas);
        } else {
            model.addAttribute("mensaje", "No se encontraron resultados para la categoría buscada.");
        }

        return "category/list";
    }

    //Lista de Detail
    @GetMapping({"/detail", "/detail/"})
    public String listaDetail(Model model) {
        model.addAttribute("listaComidas", mealDao.listarDetail());
        return "detail/list";
    }

    //Buscador de meal
    @GetMapping("/buscarDetail")
    public String buscarDetail(@RequestParam("nombre") String nombre, Model model) {
        List<Detail> comidas = mealDao.buscadorDetail(nombre);

        if (!comidas.isEmpty()) {
            model.addAttribute("listaComidas", comidas);
        } else {
            model.addAttribute("mensaje", "No se encontraron resultados para la categoría buscada.");
        }

        return "detail/list";
    }

    //Vista de detalle
    @GetMapping("/detalle")
    public String DetalleDetail(@RequestParam("nombre") String nombre, Model model) {
        Detail detalle = mealDao.verDetallePorNombre(nombre);

        if (detalle != null) {
            model.addAttribute("detalle", detalle);
            return "detail/detail";
        } else {
            model.addAttribute("error", "No se encontraron detalles para el nombre proporcionado.");
            return "detail/error";
        }
    }

    //Añadir a favoritos
    @PostMapping("/anadirFavoritos")
    public String addToFavorites(@RequestParam("mealName") String mealName) {
        List<Detail> detailsList = mealDao.buscarComidaPorNombreEnDetail(mealName);

        if (detailsList != null && !detailsList.isEmpty()) {
            Detail details = detailsList.get(0);

            Comida comida = new Comida();
            comida.setStrMeal(details.getStrMeal());
            comida.setStrDrinkAlternate(details.getStrDrinkAlternate());
            comida.setStrCategory(details.getStrCategory());
            comida.setStrArea(details.getStrArea());
            comida.setStrTags(details.getStrTags());
            comida.setStrInstructions(details.getStrInstructions());
            comida.setStrMealThumb(details.getStrMealThumb());
            comida.setStrIngredient1(details.getStrIngredient1());
            comida.setStrIngredient2(details.getStrIngredient2());
            comida.setStrIngredient3(details.getStrIngredient3());
            comida.setStrIngredient4(details.getStrIngredient4());
            comida.setFavorite(1);

            comidaRepository.save(comida);
            System.out.println("Comida guardada en la base de datos.");

        }
        System.out.println("No se encontraron detalles para " + mealName);

        return "redirect:/detalle?nombre=" + mealName;
    }

    @GetMapping({"/favorite", "/favorite/"})
    public String listaFavorite(Model model) {
        model.addAttribute("listaComidas", comidaRepository.findAll());
        return "favorite/list";
    }


}
