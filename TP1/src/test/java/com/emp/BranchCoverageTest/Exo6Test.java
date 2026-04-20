package com.emp.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.FizzBuzz;

public class Exo6Test {

    @Test
    public void testLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            FizzBuzz.fizzBuzz(0);
        });
    }

    @Test
    public void testValidInput() {
        assertDoesNotThrow(() -> {
            FizzBuzz.fizzBuzz(1);
        });
    }

    @Test
    public void testDivisibleByFifteen() {
        assertEquals("FizzBuzz", FizzBuzz.fizzBuzz(15));
    }

    @Test
    public void testNotDivisibleByFifteen() {
        assertNotEquals("FizzBuzz", FizzBuzz.fizzBuzz(3));
    }

    @Test
    public void testDivisibleByThree() {
        assertEquals("Fizz", FizzBuzz.fizzBuzz(3));
    }

    @Test
    public void testNotDivisibleByThree() {
        assertNotEquals("Fizz", FizzBuzz.fizzBuzz(5));
    }

    @Test
    public void testDivisibleByFive() {
        assertEquals("Buzz", FizzBuzz.fizzBuzz(5));
    }

    @Test
    public void testNotDivisibleByFive() {
        assertNotEquals("Buzz", FizzBuzz.fizzBuzz(3));
    }
}
