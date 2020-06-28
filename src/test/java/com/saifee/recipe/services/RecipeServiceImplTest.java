package com.saifee.recipe.services;

import com.saifee.recipe.conveters.RecipeCommandToRecipe;
import com.saifee.recipe.conveters.RecipeToRecipeCommand;
import com.saifee.recipe.domain.Recipe;
import com.saifee.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecipes() {
        Recipe recipe= new Recipe();
        Set<Recipe> recipeSet = new HashSet<Recipe>();
        recipeSet.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeSet);

        assertEquals(1, recipeService.getRecipes().size());

        verify(recipeRepository, times(1)).findAll();
    }
}