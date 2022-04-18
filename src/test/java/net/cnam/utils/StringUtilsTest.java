package net.cnam.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de StringUtils
 */
public class StringUtilsTest {

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
