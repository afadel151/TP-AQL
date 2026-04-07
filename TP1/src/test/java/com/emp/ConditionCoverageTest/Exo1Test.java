package com.emp.ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.emp.Palindrome;

public class Exo1Test {
    

    @Test
    public void testNull()
    {
        assertThrows(NullPointerException.class, () -> {
            Palindrome.isPalindrome(null);
        });
    }

    @Test
    public void testNotNull()
    {
        assertDoesNotThrow(() -> {
            Palindrome.isPalindrome("Hello");
        });
    }

    @Test
    public void testEmptyString()
    {
        assertTrue(Palindrome.isPalindrome(""));
    }

    @Test 
    public void testNotEmptyString()
    {
        assertFalse(Palindrome.isPalindrome("Hello"));
    }

    @Test  
    public void testPalindrome()
    {
        assertTrue(Palindrome.isPalindrome("kayak"));
    }

    @Test 
    public void testNotPalindrome()
    {
        assertFalse(Palindrome.isPalindrome("Hello World"));
    }
    
}
