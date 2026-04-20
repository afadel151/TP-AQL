package com.emp.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.QuadraticEquation;

public class Exo4Test {

    @Test
    public void testAEqualsZero() {
        // a == 0 is true
        assertThrows(IllegalArgumentException.class, () -> {
            QuadraticEquation.solve(0, 1, 1);
        });
    }

    @Test
    public void testANotZero() {
        // a == 0 is false
        assertDoesNotThrow(() -> {
            QuadraticEquation.solve(1, 1, 1);
        });
    }

    @Test
    public void testDeltaLessThanZero() {
        // delta < 0 is true
        assertNull(QuadraticEquation.solve(1, 1, 1));
    }

    @Test
    public void testDeltaNotLessThanZero() {
        // delta < 0 is false
        assertNotNull(QuadraticEquation.solve(1, 2, 1));
    }

    @Test
    public void testDeltaEqualsZero() {
        // delta == 0 is true
        double[] result = QuadraticEquation.solve(1, 2, 1);
        assertNotNull(result);
        assertEquals(1, result.length);
    }

    @Test
    public void testDeltaNotEqualsZero() {
        // delta == 0 is false (delta > 0)
        double[] result = QuadraticEquation.solve(1, -5, 6);
        assertNotNull(result);
        assertEquals(2, result.length);
    }
}
