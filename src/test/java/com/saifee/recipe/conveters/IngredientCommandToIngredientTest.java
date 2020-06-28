package com.saifee.recipe.conveters;

import com.saifee.recipe.command.IngredientCommand;
import com.saifee.recipe.command.UnitOfMeasureCommand;
import com.saifee.recipe.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class IngredientCommandToIngredientTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Description";
    private static final BigDecimal AMOUNT = BigDecimal.ONE;
    private static final UnitOfMeasureCommand UOM = new UnitOfMeasureCommand();

    IngredientCommandToIngredient ingredientConverter;

    @BeforeEach
    void setUp() {
        ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullObject() {

        assertNull(ingredientConverter.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    void testConvert() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setUom(UOM);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNotNull(ingredient.getUom());
    }
}