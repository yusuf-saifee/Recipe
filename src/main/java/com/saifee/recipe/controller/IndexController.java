package com.saifee.recipe.controller;

import com.saifee.recipe.repositories.CategoryRepository;
import com.saifee.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/","/index"})
    public String getIndexPage(){

        categoryRepository.findByCategoryName("American").ifPresent(value ->
                System.out.println("Cat is: " + value.getId()));
        unitOfMeasureRepository.findByDescription("Cup").ifPresent( value ->
                System.out.println("UOM is: " + value.getId()));
        return "index";
    }

}
