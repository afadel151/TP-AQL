package com.emp.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.BinarySearch;

public class Exo3Test {

    @Test
    public void testNullArray() {
        assertThrows(NullPointerException.class, () -> {
            BinarySearch.binarySearch(null, 5);
        });
    }

    @Test
    public void testNotNullArray() {
        assertDoesNotThrow(() -> {
            BinarySearch.binarySearch(new int[]{1, 2, 3}, 2);
        });
    }

    @Test
    public void testElementFound() {
        assertEquals(1, BinarySearch.binarySearch(new int[]{1, 2, 3}, 2));
    }

    @Test
    public void testElementNotFound() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{1, 2, 3}, 5));
    }

    @Test
    public void testElementLessThanMid() {
        // Tests the else branch of array[mid] <= element
        assertEquals(0, BinarySearch.binarySearch(new int[]{1, 3, 5}, 1));
    }

    @Test
    public void testElementGreaterThanMid() {
        // Tests the if branch of array[mid] <= element
        assertEquals(2, BinarySearch.binarySearch(new int[]{1, 3, 5}, 5));
    }
}
