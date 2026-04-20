package com.emp.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.BinarySearch;

public class Exo3Test {

    @Test
    public void testArrayNull() {
        // array == null is true
        assertThrows(NullPointerException.class, () -> {
            BinarySearch.binarySearch(null, 5);
        });
    }

    @Test
    public void testArrayNotNull() {
        // array == null is false
        assertDoesNotThrow(() -> {
            BinarySearch.binarySearch(new int[]{1, 2, 3}, 2);
        });
    }

    @Test
    public void testLowLessThanOrEqualHigh() {
        // low <= high is true (element found)
        assertEquals(1, BinarySearch.binarySearch(new int[]{1, 2, 3}, 2));
    }

    @Test
    public void testLowGreaterThanHigh() {
        // low <= high is false (element not found)
        assertEquals(-1, BinarySearch.binarySearch(new int[]{1, 2, 3}, 5));
    }

    @Test
    public void testArrayMidEqualsElement() {
        // array[mid] == element is true
        assertEquals(1, BinarySearch.binarySearch(new int[]{1, 2, 3}, 2));
    }

    @Test
    public void testArrayMidNotEqualsElement() {
        // array[mid] == element is false
        assertEquals(-1, BinarySearch.binarySearch(new int[]{1, 2, 3}, 5));
    }

    @Test
    public void testArrayMidLessOrEqualElement() {
        // array[mid] <= element is true (goes to low = mid + 1)
        assertEquals(2, BinarySearch.binarySearch(new int[]{1, 3, 5}, 5));
    }

    @Test
    public void testArrayMidGreaterThanElement() {
        // array[mid] <= element is false (goes to high = mid - 1)
        assertEquals(0, BinarySearch.binarySearch(new int[]{1, 3, 5}, 1));
    }
}
