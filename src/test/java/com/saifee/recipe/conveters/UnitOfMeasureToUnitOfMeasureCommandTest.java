package com.saifee.recipe.conveters;

import com.saifee.recipe.command.UnitOfMeasureCommand;
import com.saifee.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long ID=1L;
    private static final String DESCRIPTION="Description";
    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @BeforeEach
    void setUp() {
        uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullObject() {
        assertNull(uomConverter.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(uomConverter.convert(new UnitOfMeasure()));
    }

    @Test
    void testConvert() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);

        UnitOfMeasureCommand uomCommand = uomConverter.convert(uom);
        assertEquals(ID, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());
    }
}