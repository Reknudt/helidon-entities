
package me.paulau.mp.database;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void lang() {
        String lang = Locale.getDefault().getDisplayLanguage();
        assertEquals("English", lang);
    }
}
