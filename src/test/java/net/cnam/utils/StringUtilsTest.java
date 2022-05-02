package net.cnam.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de StringUtils
 */
public class StringUtilsTest {

    /**
     * Test of centerText method, of class StringUtils.
     */
    @Test
    public void testCenterText_1() {
        System.out.println("testCenterText_1");
        String text = "Menu";
        char spacer = ' ';
        int length = 12;
        String expResult = "    Menu    ";
        String result = StringUtils.centerText(text, spacer, length);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of centerText method, of class StringUtils.
     */
    @Test
    public void testCenterText_2() {
        System.out.println("testCenterText_2");
        String text = "Menu";
        char spacer = '-';
        char separator = ' ';
        int length = 12;
        String expResult = "--- Menu ---";
        String result = StringUtils.centerText(text, spacer, separator, length);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of convertStringToStringArray method, of class StringUtils.
     */
    @Test
    public void testConvertStringToStringArray() {
        System.out.println("testConvertStringToStringArray");
        String string = "ab\na\nabcdef\nabc";
        String[] expResult = new String[]{"ab", "a", "abcdef", "abc"};
        String[] result = StringUtils.convertStringToStringArray(string);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of convertStringArrayToString method, of class StringUtils.
     */
    @Test
    public void testConvertStringArrayToString() {
        System.out.println("testConvertStringArrayToString");
        String[] table = new String[]{"ab", "a", "abcdef", "abc"};
        String expResult = "ab\na\nabcdef\nabc";
        String result = StringUtils.convertStringArrayToString(table);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of getMaximumLength method, of class StringUtils.
     */
    @Test
    public void testGetMaximumLength() {
        System.out.println("testGetMaximumLength");
        String[] table = new String[]{"ab", "a", "abcdef", "abc"};
        int expResult = 6;
        int result = StringUtils.getMaximumLength(table);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

}
