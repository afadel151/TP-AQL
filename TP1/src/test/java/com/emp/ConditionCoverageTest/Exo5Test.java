package com.emp.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.RomanNumeral;

public class Exo5Test {

    @Test
    public void testNLessThanOne() {
        // n < 1 is true
        assertThrows(IllegalArgumentException.class, () -> {
            RomanNumeral.toRoman(0);
        });
    }

    @Test
    public void testNNotLessThanOne() {
        // n < 1 is false
        assertDoesNotThrow(() -> {
            RomanNumeral.toRoman(1);
        });
    }

    @Test
    public void testNGreaterThan3999() {
        // n > 3999 is true
        assertThrows(IllegalArgumentException.class, () -> {
            RomanNumeral.toRoman(4000);
        });
    }

    @Test
    public void testNNotGreaterThan3999() {
        // n > 3999 is false
        assertDoesNotThrow(() -> {
            RomanNumeral.toRoman(3999);
        });
    }

    @Test
    public void testNGreaterThanOrEqualToValue() {
        // n >= values[i] is true (while loop executes)
        assertEquals("MMM", RomanNumeral.toRoman(3000));
    }

    @Test
    public void testNLessThanValue() {
        // n >= values[i] is false (while loop skipped)
        assertEquals("I", RomanNumeral.toRoman(1));
    }
}
