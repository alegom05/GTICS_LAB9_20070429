package com.example.springdogless.controllers;

import com.example.springdogless.dao.MealDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "meal/list";
    }

    @GetMapping({"/detail"})
    public String listaDetail(Model model) {
        model.addAttribute("listaComidas", mealDao.listarDetail());
        return "detail/list";
    }

    @GetMapping("/buscarDetail")
    public String buscarDetail(@RequestParam("query") String nombre, Model model) {
        model.addAttribute("listaComidas", mealDao.buscarComidaPorNombreEnDetail(nombre));
        return "detail/list";
    }
}
