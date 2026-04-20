package com.emp.LineCoverageTest;

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
    public void testGreaterThan3999() {
        assertThrows(IllegalArgumentException.class, () -> {
            RomanNumeral.toRoman(4000);
        });
    }

    @Test
    public void testOne() {
        assertEquals("I", RomanNumeral.toRoman(1));
    }

    @Test
    public void testFour() {
        assertEquals("IV", RomanNumeral.toRoman(4));
    }

    @Test
    public void testNine() {
        assertEquals("IX", RomanNumeral.toRoman(9));
    }

    @Test
    public void testForty() {
        assertEquals("XL", RomanNumeral.toRoman(40));
    }

    @Test
    public void testNinety() {
        assertEquals("XC", RomanNumeral.toRoman(90));
    }

    @Test
    public void testFourHundred() {
        assertEquals("CD", RomanNumeral.toRoman(400));
    }

    @Test
    public void testNineHundred() {
        assertEquals("CM", RomanNumeral.toRoman(900));
    }

    @Test
    public void testRegularNumber() {
        assertEquals("LVIII", RomanNumeral.toRoman(58));
    }

    @Test
    public void testComplexNumber() {
        assertEquals("MCMXCIV", RomanNumeral.toRoman(1994));
    }

    @Test
    public void testMaximum() {
        assertEquals("MMMCMXCIX", RomanNumeral.toRoman(3999));
    }
}
