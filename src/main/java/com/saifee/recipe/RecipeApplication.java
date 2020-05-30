package com.saifee.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeApplication {

    public static void main (String args[]) throws Exception {
        SpringApplication.run(RecipeApplication.class, args);
        System.out.println("Hello World Spring!! ");
    }
}
