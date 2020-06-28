package com.saifee.recipe.conveters;

import com.saifee.recipe.command.RecipeCommand;
import com.saifee.recipe.domain.Category;
import com.saifee.recipe.domain.Difficulty;
import com.saifee.recipe.domain.Ingredient;
import com.saifee.recipe.domain.Notes;
import com.saifee.recipe.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        recipeCommandToRecipe = new RecipeToRecipeCommand(
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand())
        );
    }

    @Test
    void testNullObject() {
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(recipeCommandToRecipe.convert(new Recipe()));
    }

    @Test
    void testConvert() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID_2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        recipe.setCookTime(COOK_TIME);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTION);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);
        recipe.setPrepTime(PREP_TIME);
        recipe.setServings(SERVING);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        RecipeCommand recipeCommand = recipeCommandToRecipe.convert(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(DIRECTION,recipeCommand.getDirections());
        assertEquals(SERVING,recipeCommand.getServings());
        assertEquals(SOURCE,recipeCommand.getSource());
        assertEquals(URL,recipeCommand.getUrl());
        assertEquals(NOTES_ID,recipeCommand.getNotes().getId());
        assertEquals(2, recipeCommand.getIngredients().size());
    }
}