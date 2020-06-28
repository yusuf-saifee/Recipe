package com.saifee.recipe.conveters;

import com.saifee.recipe.command.RecipeCommand;
import com.saifee.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    NotesCommandToNotes notesConverter;
    CategoryCommandToCategory categoryConverter;
    IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter,
        IngredientCommandToIngredient ingredientConverter) {

        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null) {
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setUrl(source.getUrl());
        recipe.setSource(source.getSource());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setNotes(notesConverter.convert(source.getNotes()));
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setServings(source.getServings());
        if(source.getCategories() !=null && source.getCategories().size() > 0){
            source.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }
        recipe.setImage(source.getImage());
        if(source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients().forEach(ingredient -> recipe.getIngredients().add(
                    ingredientConverter.convert(ingredient)));
        }
        return recipe;
    }
}
