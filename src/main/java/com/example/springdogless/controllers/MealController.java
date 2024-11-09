package com.example.springdogless.controllers;

import com.example.springdogless.dao.MealDao;
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

    /*
    @PostMapping("/add-to-favorites")
    public String addToFavorites(@RequestParam("mealName") String mealName) {
        // Fetch the meal from the database using the mealName
        Detail detail = mealDao.buscarComidaPorNombreEnDetail(mealName);

        // Update the favorite field
        if (detail != null) {
            detail.setFavorite(1);
            mealService.save(meal); // Save the updated meal
        }

        // Redirect to a relevant page (e.g., details or a list view)
        return "redirect:/detalle?mealName=" + mealName;
    }
    */



}
