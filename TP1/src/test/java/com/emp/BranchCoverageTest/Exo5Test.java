package com.emp.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.RomanNumeral;

public class Exo5Test {

    @Test
    public void testLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            RomanNumeral.toRoman(0);
        });
    }

    @Test
    public void testValidRange() {
        assertDoesNotThrow(() -> {
            RomanNumeral.toRoman(100);
        });
    }

    @Test
    public void testGreaterThan3999() {
        assertThrows(IllegalArgumentException.class, () -> {
            RomanNumeral.toRoman(4000);
        });
    }

    @Test
    public void testWhileLoopExecutes() {
        // Test that the while loop inside the for loop executes
        assertEquals("MMM", RomanNumeral.toRoman(3000));
    }

    @Test
    public void testWhileLoopSkips() {
        // Test that the while loop is skipped when n < values[i]
        assertEquals("I", RomanNumeral.toRoman(1));
    }
}
