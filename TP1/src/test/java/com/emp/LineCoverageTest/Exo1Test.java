package com.emp.LineCoverageTest;
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
    public void testEmptyString()
    {
        assertTrue(Palindrome.isPalindrome(""));
    }
    
    @Test
    public void testSingleCharacter_returnsTrue() {
        assertTrue(Palindrome.isPalindrome("a"));
    }
    
    @Test
    public void testPalindrome()
    {
        assertTrue(Palindrome.isPalindrome("A man a plan a canal Panama"));
    }

    @Test
    public void testNotPalindrome()
    {
        assertFalse(Palindrome.isPalindrome("Hello World"));
    }
    

    
}
