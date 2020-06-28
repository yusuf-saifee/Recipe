package com.saifee.recipe.conveters;

import com.saifee.recipe.command.NotesCommand;
import com.saifee.recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotesToNotesCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Description";

    NotesToNotesCommand notesConverter;

    @BeforeEach
    void setUp() {
        notesConverter = new NotesToNotesCommand();
    }

    @Test
    void testNullObject() {
        assertNull(notesConverter.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(notesConverter.convert(new Notes()));
    }

    @Test
    void testConvert() {
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setDescription(DESCRIPTION);

        NotesCommand notesCommand = notesConverter.convert(notes);
        assertEquals(ID, notesCommand.getId());
        assertEquals(DESCRIPTION, notesCommand.getDescription());
    }
}