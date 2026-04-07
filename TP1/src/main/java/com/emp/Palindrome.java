package com.emp;

public class Palindrome {
    public static boolean isPalindrome(String s) {
        if (s == null) {
            throw new NullPointerException("String must not be null");
        }
        s = s.toLowerCase().replaceAll("\\s+", "");
        int i = 0;
        int j = s.length() - 1;
        System.out.println("i: " + i + ", j: " + j);
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}