package net.cnam.chateau.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de StringUtils
 */
public class StringUtilsTest {

    /**
     * Test of centerString method, of class StringUtils.
     */
    @Test
    public void testCenterString_1() {
        System.out.println("testCenterString_1");
        String input = "Menu";
        char spacer = ' ';
        int minLength = 12;
        String expResult = "    Menu    ";
        String result = StringUtils.centerString(input, spacer, minLength);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of centerString method, of class StringUtils.
     */
    @Test
    public void testCenterString_2() {
        System.out.println("testCenterString_2");
        String input = "Menu";
        char spacer = '-';
        char separator = ' ';
        int minLength = 12;
        String expResult = "--- Menu ---";
        String result = StringUtils.centerString(input, spacer, separator, minLength);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of centerString method, of class StringUtils.
     */
    @Test
    public void testCenterString_3() {
        System.out.println("testCenterString_3");
        String input = "Menu principal";
        char spacer = '-';
        char separator = ' ';
        int minLength = 6;
        String expResult = " Menu principal ";
        String result = StringUtils.centerString(input, spacer, separator, minLength);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of centerString method, of class StringUtils.
     */
    @Test
    public void testCenterString_4() {
        System.out.println("testCenterString_4");
        String input = "Menu principal";
        char spacer = '-';
        int minLength = 6;
        String expResult = "Menu principal";
        String result = StringUtils.centerString(input, spacer, minLength);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of centerString method, of class StringUtils.
     */
    @Test
    public void testCenterString_5() {
        System.out.println("testCenterString_5");
        String input = "Menu principal";
        char spacer = ' ';
        int minLength = 16;
        String expResult = " Menu principal ";
        String result = StringUtils.centerString(input, spacer, minLength);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of centerString method, of class StringUtils.
     */
    @Test
    public void testCenterString_6() {
        System.out.println("testCenterString_6");
        String input = "Menu principal";
        char spacer = ' ';
        int minLength = 15;
        String expResult = "Menu principal ";
        String result = StringUtils.centerString(input, spacer, minLength);
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of centerString method, of class StringUtils.
     */
    @Test
    public void testCenterString_7() {
        System.out.println("testCenterString_7");
        String input = "Menu principal";
        char spacer = ' ';
        int minLength = 14;
        String expResult = "Menu principal";
        String result = StringUtils.centerString(input, spacer, minLength);
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
