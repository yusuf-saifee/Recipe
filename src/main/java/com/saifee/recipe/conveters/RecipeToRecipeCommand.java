package com.saifee.recipe.conveters;

import com.saifee.recipe.command.RecipeCommand;
import com.saifee.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    NotesToNotesCommand notesConverter;
    CategoryToCategoryCommand categoryConverter;
    IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter,
        IngredientToIngredientCommand ingredientConverter) {

        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null) {
            return null;
        }
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setServings(source.getServings());
        if(source.getCategories()!= null && source.getCategories().size() > 0){
            source.getCategories().forEach(category -> recipeCommand.getCategories().add(
                    categoryConverter.convert(category)));
        }
        recipeCommand.setImage(source.getImage());
        if(source.getIngredients()!= null && source.getIngredients().size() > 0){
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(
                    ingredientConverter.convert(ingredient)));
        }
        return recipeCommand;
    }
}
