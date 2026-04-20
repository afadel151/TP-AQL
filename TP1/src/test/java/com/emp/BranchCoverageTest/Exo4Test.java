package com.emp.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.QuadraticEquation;

public class Exo4Test {

    @Test
    public void testAEqualsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuadraticEquation.solve(0, 1, 1);
        });
    }

    @Test
    public void testANotZero() {
        assertDoesNotThrow(() -> {
            QuadraticEquation.solve(1, 1, 1);
        });
    }

    @Test
    public void testDeltaNegative() {
        assertNull(QuadraticEquation.solve(1, 1, 1));
    }

    @Test
    public void testDeltaZero() {
        double[] result = QuadraticEquation.solve(1, 2, 1);
        assertNotNull(result);
        assertEquals(1, result.length);
    }

    @Test
    public void testDeltaPositive() {
        double[] result = QuadraticEquation.solve(1, -5, 6);
        assertNotNull(result);
        assertEquals(2, result.length);
    }
}
