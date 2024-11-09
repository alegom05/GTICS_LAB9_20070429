package com.example.springdogless.controllers;

import com.example.springdogless.Repository.ComidaRepository;
import com.example.springdogless.dao.MealDao;
import com.example.springdogless.entity.Comida;
import com.example.springdogless.entity.Detail;
import com.example.springdogless.entity.DetailResponse;
import com.example.springdogless.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping({ "", "/"})
public class MealController {

    @Autowired
    MealDao mealDao;
    @Autowired
    ComidaRepository comidaRepository;

    @GetMapping({"/meal", "", "/"})
    public String listaComidas(Model model) {
        model.addAttribute("listaComidas", mealDao.listarComidas());
        return "category/list";
    }

    @GetMapping("/buscar")
    public String buscarComidas(@RequestParam("query") String nombre, Model model) {
        model.addAttribute("listaComidas", mealDao.buscarComidaPorNombre(nombre));
        return "category/list";
    }

    @GetMapping({"/detail", "/detail/"})
    public String listaDetail(Model model) {
        model.addAttribute("listaComidas", mealDao.listarDetail());
        return "detail/list";
    }

    @GetMapping("/detalle")
    public String listaDetail(@RequestParam("nombre") String nombre, Model model) {
        Detail detalle = mealDao.verDetallePorNombre(nombre);
        if (detalle != null) {
            model.addAttribute("detalle", detalle);
            return "detail/detail"; // Asegúrate de tener la vista 'detail/view.html'
        } else {
            model.addAttribute("error", "No se encontraron detalles para el nombre proporcionado.");
            return "detail/error"; // O una vista de error si prefieres
        }
    }



    @GetMapping("/buscarDetail")
    public String buscarDetail(@RequestParam("query") String nombre, Model model) {
        model.addAttribute("listaComidas", mealDao.buscarComidaPorNombreEnDetail(nombre));
        return "detail/list";
    }


    @PostMapping("/add-to-favorites")
    public String addToFavorites(@RequestParam("mealName") String mealName) {
        // Suponiendo que el método devuelve una lista
        List<Detail> detailsList = mealDao.buscarComidaPorNombreEnDetail(mealName);

        // Verifica que la lista no esté vacía antes de acceder a los elementos
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
        }

        return "redirect:/detalle?mealName=" + mealName;
    }






}
