package com.saifee.recipe.controller;

import com.saifee.recipe.command.RecipeCommand;
import com.saifee.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping({"/recipe"})
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/{id}/show"})
    public String showRecipe(Model model, @PathVariable String id){
        log.debug("Getting Recipe Page..");
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping({"/new"})
    public String newRecipe(Model model){
        log.info("New Recipe Page..");
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @PostMapping({"/"})
    public String saveRecipe(Model model, @ModelAttribute RecipeCommand recipeCommand){
        log.debug("Save Recipe Page..");
        RecipeCommand recipe = recipeService.saveRecipe(recipeCommand);
        return "redirect:"+recipe.getId()+"/show";
    }
}
