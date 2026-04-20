package com.emp.LineCoverageTest;

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
    public void testEmptyArray() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{}, 5));
    }

    @Test
    public void testSingleElementFound() {
        assertEquals(0, BinarySearch.binarySearch(new int[]{5}, 5));
    }

    @Test
    public void testSingleElementNotFound() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{5}, 3));
    }

    @Test
    public void testElementAtBeginning() {
        assertEquals(0, BinarySearch.binarySearch(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void testElementAtEnd() {
        assertEquals(4, BinarySearch.binarySearch(new int[]{1, 2, 3, 4, 5}, 5));
    }

    @Test
    public void testElementInMiddle() {
        assertEquals(2, BinarySearch.binarySearch(new int[]{1, 2, 3, 4, 5}, 3));
    }

    @Test
    public void testElementNotFound() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{1, 2, 3, 4, 5}, 6));
    }
}
