package com.saifee.recipe.conveters;

import com.saifee.recipe.command.IngredientCommand;
import com.saifee.recipe.domain.Ingredient;
import com.saifee.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class IngredientToIngredientCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Description";
    private static final BigDecimal AMOUNT = BigDecimal.ONE;
    private static final UnitOfMeasure UOM = new UnitOfMeasure();

    IngredientToIngredientCommand ingredientConverter;

    @BeforeEach
    void setUp() {
        ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullObject() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(ingredientConverter.convert(new Ingredient()));
    }

    @Test
    void testConvert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setUom(UOM);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertNotNull(ingredientCommand.getUom());
    }
}