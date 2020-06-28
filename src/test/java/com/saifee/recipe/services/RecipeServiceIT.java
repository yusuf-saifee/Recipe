package com.saifee.recipe.services;

import com.saifee.recipe.command.RecipeCommand;
import com.saifee.recipe.conveters.RecipeCommandToRecipe;
import com.saifee.recipe.conveters.RecipeToRecipeCommand;
import com.saifee.recipe.domain.Recipe;
import com.saifee.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceIT {

    private static final String NEW_DESCRIPTION = "New Description";
    @Autowired
    RecipeRepository recipeRepository;
    
    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeService recipeService;

    @Test
    @Transactional
    public void testSaveRecipe(){

        //given
        Recipe recipe = recipeRepository.findAll().iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        //when
        recipeCommand.setDescription(NEW_DESCRIPTION);
        recipeCommand = recipeService.saveRecipe(recipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, recipeCommand.getDescription());
        assertEquals(recipe.getId(), recipeCommand.getId());
        assertEquals(recipe.getCategories().size(), recipeCommand.getCategories().size());
        assertEquals(recipe.getIngredients().size(), recipeCommand.getIngredients().size());
    }
}
