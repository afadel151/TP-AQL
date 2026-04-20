package com.emp.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.FizzBuzz;

public class Exo6Test {

    @Test
    public void testNLessThanOne() {
        // n < 1 is true
        assertThrows(IllegalArgumentException.class, () -> {
            FizzBuzz.fizzBuzz(0);
        });
    }

    @Test
    public void testNNotLessThanOne() {
        // n < 1 is false
        assertDoesNotThrow(() -> {
            FizzBuzz.fizzBuzz(1);
        });
    }

    @Test
    public void testNDivisibleByFifteen() {
        // n % 15 == 0 is true
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(15));
    }

    @Test
    public void testNNotDivisibleByFifteen() {
        // n % 15 == 0 is false
        assertNotEquals("FizzBuzz", FizzBuzz.fizzBuzz(3));
    }

    @Test
    public void testNDivisibleByThree() {
        // n % 3 == 0 is true (and n % 15 != 0)
        assertEquals("Fizz", FizzBuzz.fizzBuzz(3));
    }

    @Test
    public void testNNotDivisibleByThree() {
        // n % 3 == 0 is false
        assertNotEquals("Fizz", FizzBuzz.fizzBuzz(5));
    }

    @Test
    public void testNDivisibleByFive() {
        // n % 5 == 0 is true (and n % 15 != 0)
        assertEquals("Buzz", FizzBuzz.fizzBuzz(5));
    }

    @Test
    public void testNNotDivisibleByFive() {
        // n % 5 == 0 is false
        assertNotEquals("Buzz", FizzBuzz.fizzBuzz(3));
    }
}
