package com.saifee.recipe.conveters;

import com.saifee.recipe.command.CategoryCommand;
import com.saifee.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CategoryCommandToCategoryTest {

    private static final Long ID = 1L;
    private static final String CATEGORY_NAME = "Indian";

    CategoryCommandToCategory categoryConverter;

    @BeforeEach
    void setUp() {
        categoryConverter = new CategoryCommandToCategory();
    }

    @Test
    void testNullObject() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(categoryConverter.convert(new CategoryCommand()));
    }

    @Test
    void testConvert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setCategoryName(CATEGORY_NAME);

        Category category = categoryConverter.convert(categoryCommand);
        assertEquals(ID , category.getId());
        assertEquals(CATEGORY_NAME, category.getCategoryName());
    }
}