package com.saifee.recipe.conveters;

import com.saifee.recipe.command.NotesCommand;
import com.saifee.recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotesCommandToNotesTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Description";

    NotesCommandToNotes notesConverter;

    @BeforeEach
    void setUp() {
        notesConverter = new NotesCommandToNotes();
    }

    @Test
    void testNullObject() {
        assertNull(notesConverter.convert(null));
    }

    @Test
    void testNotNullObject() {
        assertNotNull(notesConverter.convert(new NotesCommand()));
    }

    @Test
    void testConvert() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setDescription(DESCRIPTION);

        Notes notes = notesConverter.convert(notesCommand);
        assertEquals(ID, notes.getId());
        assertEquals(DESCRIPTION, notes.getDescription());
    }
}