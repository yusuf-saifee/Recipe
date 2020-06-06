package com.saifee.recipe.controller;

import com.saifee.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/","/index"})
    public String getIndexPage(Model model){
        log.debug("Loading Index Controller..");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
