package com.saifee.recipe.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    void findByDescription() {
        assertEquals("Teaspoon", unitOfMeasureRepository.findByDescription("Teaspoon").get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        assertEquals("Cup", unitOfMeasureRepository.findByDescription("Cup").get().getDescription());
    }
}