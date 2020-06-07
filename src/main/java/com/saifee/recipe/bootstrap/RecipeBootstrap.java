package com.saifee.recipe.bootstrap;

import com.saifee.recipe.domain.Category;
import com.saifee.recipe.domain.Difficulty;
import com.saifee.recipe.domain.Ingredient;
import com.saifee.recipe.domain.Notes;
import com.saifee.recipe.domain.Recipe;
import com.saifee.recipe.domain.UnitOfMeasure;
import com.saifee.recipe.repositories.CategoryRepository;
import com.saifee.recipe.repositories.RecipeRepository;
import com.saifee.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    RecipeRepository recipeRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;
    CategoryRepository categoryRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading Recipe Boot Strap...");
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<Recipe>();

        //Unit of Measure
        Optional<UnitOfMeasure> uomTeaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!uomTeaspoonOptional.isPresent()){
            new RuntimeException("Expected UOM is not found.");
        }
        Optional<UnitOfMeasure> uomTablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!uomTablespoonOptional.isPresent()){
            new RuntimeException("Expected UOM is not found.");
        }
        Optional<UnitOfMeasure> uomCupOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!uomCupOptional.isPresent()){
            new RuntimeException("Expected UOM is not found.");
        }
        Optional<UnitOfMeasure> uomPinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if (!uomPinchOptional.isPresent()){
            new RuntimeException("Expected UOM is not found.");
        }
        Optional<UnitOfMeasure> uomOunceOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if (!uomOunceOptional.isPresent()){
            new RuntimeException("Expected UOM is not found.");
        }
        Optional<UnitOfMeasure> uomPoundOptional = unitOfMeasureRepository.findByDescription("Pound");
        if (!uomPoundOptional.isPresent()){
            new RuntimeException("Expected UOM is not found.");
        }

        //getOptionals
        UnitOfMeasure uomTeaspoon = uomTeaspoonOptional.get();
        UnitOfMeasure uomTablespoon = uomTablespoonOptional.get();
        UnitOfMeasure uomCup = uomCupOptional.get();
        UnitOfMeasure uomPinch = uomPinchOptional.get();
        UnitOfMeasure uomOunce = uomOunceOptional.get();
        UnitOfMeasure uomPound = uomPoundOptional.get();

        Optional<Category> categoryIndianOptional = categoryRepository.findByCategoryName("Indian");
        if (!categoryIndianOptional.isPresent()){
            new RuntimeException("Expected Category is not found.");
        }
        Optional<Category> categoryAmericanOptional = categoryRepository.findByCategoryName("American");
        if (!categoryAmericanOptional.isPresent()){
            new RuntimeException("Expected Category is not found.");
        }

        //get Category Optionals
        Category categoryIndian = categoryIndianOptional.get();
        Category categoryAmerican = categoryAmericanOptional.get();

        //1. Coconut Chicken Recipe
        Recipe coconutChicken = new Recipe();

        coconutChicken.setDescription("Coconut Chicken Curry Recipe");
        coconutChicken.setPrepTime(15);
        coconutChicken.setCookTime(30);
        coconutChicken.setDifficulty(Difficulty.HARD);
        coconutChicken.setSource("INDIAN FOOD FOREVER- taste of united India.");
        coconutChicken.setUrl("https://www.indianfoodforever.com/non-veg/chicken/coconut-curry-chicken.html");

        //Notes
        Notes cocoNotes = new Notes();
        cocoNotes.setDescription("Here is a delicious recipe for Indian coconut chicken curry. " +
                "This recipe of coconut chicken is with coconut milk.");
        coconutChicken.setNotes(cocoNotes);

        coconutChicken.setDirections("1. Preheat the oven to 350 degrees F.\n" +
                "2. Spread the cashews in a pie plate and bake for 5 minutes, or until fragrant and lightly toasted. " +
                "Transfer to a plate to cool.\n" +
                "3. Lightly season the chicken with salt. In a large, deep skillet, heat 3 tablespoons of the oil " +
                "until smoking.\n" +
                "4. Add the chicken and cook over moderately high heat until golden brown, about 1 1/2 minutes per side." +
                " Transfer the chicken to a plate and reduce the heat to moderate.\n" +
                "5. Add the remaining 1 tablespoon of oil to the skillet and heat until smoking. Add the mustard seeds " +
                "and cook for about 1 minute, or until they stop popping. Add the cumin seeds, coriander and curry " +
                "powder, and cook, stirring occasionally, until fragrant, about 1 minute.\n" +
                "6. Add the onion, ginger and garlic, and cook until the onion softens, about 10 minutes; if the " +
                "mixture seems dry, add up to 1/4 cup of water to prevent sticking.\n" +
                "7. Stir in the coconut milk and bring to a boil. Reduce the heat to low. Return the chicken to the " +
                "skillet and simmer until cooked through, about 5 minutes. Stir in the peas and cook for 1 minute. " +
                "Transfer the coconut chicken curry to a bowl, sprinkle with the cilantro and cashews and serve.");

        //Ingredient
        coconutChicken.addIngredient(new Ingredient("Whole unsalted cashews", new BigDecimal(0.25),uomTeaspoon));
        coconutChicken.addIngredient(new Ingredient("Skinless, boneless chicken breasts, cut into 1-inch pieces", new BigDecimal(1), uomPound));
        coconutChicken.addIngredient(new Ingredient("Salt to taste", null, null));
        coconutChicken.addIngredient(new Ingredient("Vegetable oil", new BigDecimal(0.25),uomCup));
        coconutChicken.addIngredient(new Ingredient("Black mustard seeds", new BigDecimal(0.25),uomTeaspoon));
        coconutChicken.addIngredient(new Ingredient("Cumin seeds", new BigDecimal(0.25),uomTeaspoon));
        coconutChicken.addIngredient(new Ingredient("1/4 tsp ground coriander", new BigDecimal(0.25),uomTeaspoon));
        coconutChicken.addIngredient(new Ingredient("Curry powder", new BigDecimal(1),uomTablespoon));
        coconutChicken.addIngredient(new Ingredient("Medium onion, thinly sliced", new BigDecimal(1),null));
        coconutChicken.addIngredient(new Ingredient("Finely grated fresh ginger", new BigDecimal(1), uomTeaspoon));
        coconutChicken.addIngredient(new Ingredient("Garlic clove, minced", new BigDecimal(1),null));
        coconutChicken.addIngredient(new Ingredient("Unsweetened coconut milk",new BigDecimal(14),uomOunce));
        coconutChicken.addIngredient(new Ingredient("Frozen peas",new BigDecimal(0.25), uomCup));
        coconutChicken.addIngredient(new Ingredient("Chopped cilantro",new BigDecimal(2), uomTablespoon));


        //Categories
        coconutChicken.getCategories().add(categoryIndian);
        coconutChicken.getCategories().add(categoryAmerican);

        recipes.add(coconutChicken);

        //2. Eggless Chocolate Mouse Recipe
        Recipe chocMousse = new Recipe();

        chocMousse.setDescription("EGGLESS CHOCOLATE MOUSSE RECIPE");
        chocMousse.setPrepTime(25);
        chocMousse.setCookTime(50);
        chocMousse.setDifficulty(Difficulty.EASY);
        chocMousse.setSource("INDIAN FOOD FOREVER- taste of united India.");
        chocMousse.setUrl("https://www.indianfoodforever.com/desserts/eggless-chocolate-mousse-without-gelatin.html");

        //Notes
        Notes chocMousseNotes = new Notes();
        chocMousseNotes.setDescription("Here is a super easy eggless chocolate mousse trifle using only whipping " +
                "cream. Kids will simply love this silky light and fluffy chocolate mousse. What’s more " +
                "it is gelatin free. It’s truly a mouthwatering dessert. This is the simplest and quickest" +
                " eggless mousse you can ever come across. It just takes 15 minutes to make it and 30 minutes" +
                " to set it. So look no further this is the chocolate mousse trifle recipe you have been " +
                "looking for. Lets learn how to make eggless chocolate mousse.");
        chocMousse.setNotes(chocMousseNotes);

        chocMousse.setDirections("1. Crush the Oreo chocolate cookies and grind them to a fine powder in a mixer " +
                "grinder. Take out in a bowl.\n" +
                "2. Now pour melted butter on the grinded cookie powder and and mix well. You will get a crumbly " +
                "mixture.\n" +
                "3. Now take a serving bowl or shot glasses and make a layer of this mixture as the base layer.\n" +
                "4. In case you want you can even use chocolate cake too. Keep the glasses aside.\n" +
                "5. In a glass bowl put the chopped dark chocolate and microwave at high power for 30 seconds. " +
                "Using a spoon or fork flip the chocolate. Repeat the process and microwave again for 30 seconds " +
                "and stir again. Keep repeating the process till chocolate melts fully and is shiny and smooth. " +
                "Keep it aside to cool at room temperature.\n" +
                "6. To beat the cream, take a big bowl which has been chilled in the refrigerator for 15 – 20 mins," +
                " add chilled heavy cream and beat it till soft peaks. You can use a hand beater or an electric one." +
                " But remember to beat on low speed initially. Once the cream starts to thicken then you can " +
                "increase the speed. Beat the cream till it is airy, light and has doubled in volume.\n" +
                "7. Once the soft peaks are formed. Divide the whipped heavy cream into 2 parts. One for the plain" +
                " layer and the other for the chocolate layer.\n" +
                "8. To prepare the chocolate layer, add the melted chocolate at room temperature to one half of " +
                "the whipped cream and mix well with cut and fold method. Using a light hand mix completely to " +
                "have an even color.\n" +
                "9. Now pour one layer of chocolate mousse over the prepared cookie layer in the glass. " +
                "Thickness should be 1 inch. Then pour second layer of chocolate mousse. Finally decorate with a " +
                "piece of oreo cookie and choco chips or chocolate vermecilli or grated chocolate.\n" +
                "10. Put the glasses into refrigerate to chill for 4 – 5 hours.\n" +
                "11. The mousse tastes better only when chilled.\n" +
                "12. ENJOY!!  (Refer: https://www.indianfoodforever.com/desserts/eggless-chocolate-mousse-without-gelatin.html)");

        //Ingredient
        chocMousse.addIngredient(new Ingredient("Unwhipped Cream", new BigDecimal(0.50),uomCup));
        chocMousse.addIngredient(new Ingredient("Dark Chocolate", new BigDecimal(1),uomCup));
        chocMousse.addIngredient(new Ingredient("Pack Oreo Chocolate Cookies / Hide n Seek", new BigDecimal(1),null));
        chocMousse.addIngredient(new Ingredient("Butter", new BigDecimal(1),uomTablespoon));
        chocMousse.addIngredient(new Ingredient("Chocolate Chips", new BigDecimal(2),uomTablespoon));

        //Categories
        chocMousse.getCategories().add(categoryIndian);
        chocMousse.getCategories().add(categoryAmerican);

        recipes.add(chocMousse);
        return recipes;

    }
}








