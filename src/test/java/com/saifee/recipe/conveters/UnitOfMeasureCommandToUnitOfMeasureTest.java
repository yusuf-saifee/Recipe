package com.saifee.recipe.conveters;

import com.saifee.recipe.command.UnitOfMeasureCommand;
import com.saifee.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long ID=1L;
    private static final String DESCRIPTION="Description";
    UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @BeforeEach
    void setUp() {
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullObject() {
        assertNull(uomConverter.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(uomConverter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void testConvert() {
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(ID);
        uomCommand.setDescription(DESCRIPTION);

        UnitOfMeasure uom = uomConverter.convert(uomCommand);
        assertEquals(ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}