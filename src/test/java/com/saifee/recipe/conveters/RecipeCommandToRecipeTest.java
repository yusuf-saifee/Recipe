package com.saifee.recipe.conveters;

import com.saifee.recipe.command.CategoryCommand;
import com.saifee.recipe.command.IngredientCommand;
import com.saifee.recipe.command.NotesCommand;
import com.saifee.recipe.command.RecipeCommand;
import com.saifee.recipe.domain.Difficulty;
import com.saifee.recipe.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecipeCommandToRecipeTest {

    private static final Long ID = 1l;
    private static final String DESCRIPTION = "Indian Recipe";
    private static final Integer PREP_TIME = 15;
    private static final Integer COOK_TIME = 20;
    private static final Integer SERVING = 4;
    private static final String SOURCE ="Food Forever";
    private static final String URL = "https://indianfoodforever.com";
    private static final String DIRECTION = "Direction";
    private static final Difficulty DIFFICULTY = Difficulty.HARD;
    private static final Long NOTES_ID = 2L;
    private static final Long CAT_ID_1 = 3L;
    private static final Long CAT_ID_2 = 4L;
    private static final Long INGREDIENT_ID_1 = 3L;
    private static final Long INGREDIENT_ID_2 = 4L;
    RecipeCommandToRecipe recipeCommandToRecipe;
    @BeforeEach
    void setUp() {
        recipeCommandToRecipe = new RecipeCommandToRecipe(
                new NotesCommandToNotes(),
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure())
        );
    }

    @Test
    void testNullObject() {
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    void testConvert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_2);

        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTION);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID_1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_ID_2);

        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        recipeCommand.setNotes(notes);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setServings(SERVING);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTION,recipe.getDirections());
        assertEquals(SERVING,recipe.getServings());
        assertEquals(SOURCE,recipe.getSource());
        assertEquals(URL,recipe.getUrl());
        assertEquals(NOTES_ID,recipe.getNotes().getId());
        assertEquals(2, recipe.getIngredients().size());
    }
}