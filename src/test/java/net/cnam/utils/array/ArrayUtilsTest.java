package net.cnam.utils.array;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Classe de test de ArrayUtils
 */
public class ArrayUtilsTest {

    /**
     * Test of addOnBottomOfArray method, of class ArrayUtils.
     */
    @Test
    public void testAddOnBottomOfArray_multipleElements_1() {
        System.out.println("addOnBottomOfArray");
        Integer[] table = new Integer[]{1, 2, 3};
        Integer[] elements = new Integer[]{4};
        Integer[] expResult = new Integer[]{1, 2, 3, 4};
        Integer[] result = ArrayUtils.addOnBottomOfArray(table, elements);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of addOnBottomOfArray method, of class ArrayUtils.
     */
    @Test
    public void testAddOnBottomOfArray_multipleElements_2() {
        System.out.println("addOnBottomOfArray");
        Integer[] table = new Integer[]{1, 2, 3};
        Integer[] elements = new Integer[]{4, 5, 6, 7, 8};
        Integer[] expResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] result = ArrayUtils.addOnBottomOfArray(table, elements);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of addOnBottomOfArray method, of class ArrayUtils.
     */
    @Test
    public void testAddOnBottomOfArray_oneElement() {
        System.out.println("addOnBottomOfArray");
        Integer[] table = new Integer[]{1, 2};
        int element = 3;
        Integer[] expResult = new Integer[]{1, 2, 3};
        Integer[] result = ArrayUtils.addOnBottomOfArray(table, element);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of extendArray method, of class ArrayUtils.
     */
    @Test
    public void testExtendArray_one() {
        System.out.println("extendArray");
        String[] table = new String[]{"A", "B"};
        String[] expResult = new String[table.length + 1];
        expResult[0] = "A";
        expResult[1] = "B";
        String[] result = ArrayUtils.extendArray(table);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of extendArray method, of class ArrayUtils.
     */
    @Test
    public void testExtendArray_multiple() {
        System.out.println("extendArray");
        String[] table = new String[]{"A", "B"};
        int extendedSize = 4;
        String[] expResult = new String[6];
        expResult[0] = "A";
        expResult[1] = "B";
        Object[] result = ArrayUtils.extendArray(table, extendedSize);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of copyArray method, of class ArrayUtils.
     */
    @Test
    public void testCopyArray_simple() throws Exception {
        System.out.println("copyArray");
        String[] from = new String[]{"A", "B", "C"};
        String[] to = new String[3];
        ArrayUtils.copyArray(from, to);
        assertArrayEquals(from, to);
        System.out.println("OK");
    }

    /**
     * Test of copyArray method, of class ArrayUtils.
     */
    @Test
    public void testCopyArray_complex() throws Exception {
        System.out.println("copyArray");
        String[] from = new String[]{"D", "E", "F"};
        String[] to = new String[6];
        to[0] = "A";
        to[1] = "B";
        to[2] = "C";
        int copyIndex = 3;
        ArrayUtils.copyArray(from, to, copyIndex);
        String[] expResult = new String[]{"A", "B", "C", "D", "E", "F"};
        assertArrayEquals(expResult, to);
        System.out.println("OK");
    }

}
