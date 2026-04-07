package com.emp.BranchCoverageTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.emp.Anagram;

public class Exo2Test {

    @Test 
    public void testBothNull() {
        assertThrows(NullPointerException.class, () -> {
            Anagram.isAnagram(null, null);
        });
    }


    @Test
    public void testS1Null() {
        assertThrows(NullPointerException.class, () -> {
            Anagram.isAnagram(null, "world");
        });
    }

    @Test
    public void testS2Null() {
        assertThrows(NullPointerException.class, () -> {
            Anagram.isAnagram("hello", null);
        });
    }

    @Test 
    public void boThEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Anagram.isAnagram("", "");
        });
    }

    @Test
    public void testS1Empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Anagram.isAnagram("", "world");
        });
    }

    @Test
    public void testS2Empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Anagram.isAnagram("hello", "");
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
        assertFalse(Anagram.isAnagram("hello", "world"));
    }

    @Test 
    public void testAnagramStrings()
    {
        assertTrue(Anagram.isAnagram("listen", "silent"));
    }

    @Test
    public void testNotAnagramStrings()
    {
        assertFalse(Anagram.isAnagram("abc", "abd"));
    }

}
