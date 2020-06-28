package com.saifee.recipe.conveters;

import com.saifee.recipe.command.CategoryCommand;
import com.saifee.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CategoryToCategoryCommandTest {

    private static final Long ID = 1L;
    private static final String CATEGORY_NAME = "Indian";

    CategoryToCategoryCommand categoryConverter;

    @BeforeEach
    void setUp() {
        categoryConverter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(categoryConverter.convert(new Category()));
    }

    @Test
    void testConvert() {
        Category category = new Category();
        category.setId(ID);
        category.setCategoryName(CATEGORY_NAME);

        CategoryCommand categoryCommand = categoryConverter.convert(category);
        assertEquals(ID , categoryCommand.getId());
        assertEquals(CATEGORY_NAME, categoryCommand.getCategoryName());
    }
}