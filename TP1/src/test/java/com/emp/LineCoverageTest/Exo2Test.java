package com.emp.LineCoverageTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.emp.Anagram;

public class Exo2Test {
    
    @Test
    public void testBothNull()
    {
        assertThrows(NullPointerException.class, () -> {
            Anagram.isAnagram(null, null);
        });
    }

   
    @Test
    public void testBothEmptyStrings()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            Anagram.isAnagram("", "");
        });
    }
    
    

   @Test 
   public void testInequalLengthStrings()
   {
        assertFalse(Anagram.isAnagram("hello", "worlds"));
    }

    @Test
    public void testAnagrams()
    {
        assertTrue(Anagram.isAnagram("listen", "silent"));
    }
    @Test 
    public void testNotAnagrams()
    {
        assertFalse(Anagram.isAnagram("abc", "abd"));
    }
}
