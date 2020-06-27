package com.saifee.recipe.services;

import com.saifee.recipe.domain.Recipe;


import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long recipeId);
}
