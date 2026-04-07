package com.emp.ConditionCoverageTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.emp.Anagram;

public class Exo2Test {

    @Test
    public void testNull()
    {
        assertThrows(NullPointerException.class, () -> {
            Anagram.isAnagram(null, "world");
        });
    }

    @Test 
    public void testNotNull()
    {
        assertDoesNotThrow(() -> {
            Anagram.isAnagram("hello", "world");
        });
    }

    @Test 
    public void testEmptyString()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            Anagram.isAnagram("", "world");
        });
    }
    public void testNotEmptyString()
    {
        assertDoesNotThrow(() -> {
            Anagram.isAnagram("hello", "world");
        });
    }

    @Test
    public void testInequalLengthStrings()
    {
        assertFalse(Anagram.isAnagram("hello", "worlds"));
    }

    @Test 
    public void testEqualLengthStrings()
    {
        assertDoesNotThrow(() -> {
            Anagram.isAnagram("hello", "world");
        });
    }

    @Test 
    public void testAnagramStrings()
    {
        assertTrue(Anagram.isAnagram("listen", "silent"));
    }

}
