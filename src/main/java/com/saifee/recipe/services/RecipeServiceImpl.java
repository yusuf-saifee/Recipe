package com.saifee.recipe.services;

import com.saifee.recipe.command.RecipeCommand;
import com.saifee.recipe.conveters.RecipeCommandToRecipe;
import com.saifee.recipe.conveters.RecipeToRecipeCommand;
import com.saifee.recipe.domain.Recipe;
import com.saifee.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;
    RecipeCommandToRecipe recipeCommandToRecipe;
    RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
        RecipeToRecipeCommand recipeToRecipeCommand) {

        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Loading All Recipes...");
        Set<Recipe> recipeSet = new HashSet<Recipe>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet:: add);
        return recipeSet;
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found.");
        }
        return recipeOptional.get();
    }

    @Override
    public RecipeCommand saveRecipe(RecipeCommand recipeCommand) {
        log.debug("Saving Recipe..");
        return recipeToRecipeCommand.convert(recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand)));
    }
}
