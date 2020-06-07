package com.saifee.recipe.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

     Category category;

    @Mock
    Recipe recipe;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @AfterEach
    void tearDown() { }

    @Test
    void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void getCategoryName() {
        String categoryName = "American";
        category.setCategoryName(categoryName);
        assertEquals(categoryName, category.getCategoryName());
    }

    @Test
    void getRecipes() {
        MockitoAnnotations.initMocks(this);
        Set<Recipe> recipeSet = new HashSet<Recipe>();
        recipeSet.add(recipe);
        category.setRecipes(recipeSet);
        assertEquals(1, category.getRecipes().size());
    }
}