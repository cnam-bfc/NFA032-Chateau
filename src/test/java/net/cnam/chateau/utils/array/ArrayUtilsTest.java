package net.cnam.chateau.utils.array;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de ArrayUtils
 */
public class ArrayUtilsTest {

    /**
     * Test of isIncludedInArray method, of class ArrayUtils.
     */
    @Test
    public void testIsIncludedInArray_reference() {
        System.out.println("testIsIncludedInArray_reference");
        Personne element = new Personne("A", 1);
        Personne[] table = new Personne[]{element, new Personne("B", 2), new Personne("C", 3)};
        boolean result = ArrayUtils.isIncludedInArray(table, element);
        assertTrue(result);
        System.out.println("OK");
    }

    /**
     * Test of isIncludedInArray method, of class ArrayUtils.
     */
    @Test
    public void testIsIncludedInArray_value() {
        System.out.println("testIsIncludedInArray_value");
        Personne[] table = new Personne[]{new Personne("A", 1), new Personne("B", 2), new Personne("C", 3)};
        Personne element = new Personne("A", 1);
        boolean result = ArrayUtils.isIncludedInArray(table, element);
        assertFalse(result);
        System.out.println("OK");
    }

    /**
     * Test of addOnTopOfArray method, of class ArrayUtils.
     */
    @Test
    public void testAddOnTopOfArray_multipleElements_1() {
        System.out.println("testAddOnTopOfArray_multipleElements_1");
        Integer[] table = new Integer[]{1, 2, 3};
        Integer[] elements = new Integer[]{4};
        Integer[] expResult = new Integer[]{4, 1, 2, 3};
        Integer[] result = ArrayUtils.addOnTopOfArray(table, elements);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of addOnTopOfArray method, of class ArrayUtils.
     */
    @Test
    public void testAddOnTopOfArray_multipleElements_2() {
        System.out.println("testAddOnTopOfArray_multipleElements_2");
        Integer[] table = new Integer[]{1, 2, 3};
        Integer[] elements = new Integer[]{4, 5, 6, 7, 8};
        Integer[] expResult = new Integer[]{4, 5, 6, 7, 8, 1, 2, 3};
        Integer[] result = ArrayUtils.addOnTopOfArray(table, elements);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of addOnBottomOfArray method, of class ArrayUtils.
     */
    @Test
    public void testAddOnBottomOfArray_multipleElements_1() {
        System.out.println("testAddOnBottomOfArray_multipleElements_1");
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
        System.out.println("testAddOnBottomOfArray_multipleElements_2");
        Integer[] table = new Integer[]{1, 2, 3};
        Integer[] elements = new Integer[]{4, 5, 6, 7, 8};
        Integer[] expResult = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] result = ArrayUtils.addOnBottomOfArray(table, elements);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of addOnTopOfArray method, of class ArrayUtils.
     */
    @Test
    public void testAddOnTopOfArray_oneElement() {
        System.out.println("testAddOnTopOfArray_oneElement");
        Integer[] table = new Integer[]{1, 2};
        int element = 3;
        Integer[] expResult = new Integer[]{3, 1, 2};
        Integer[] result = ArrayUtils.addOnTopOfArray(table, element);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of addOnBottomOfArray method, of class ArrayUtils.
     */
    @Test
    public void testAddOnBottomOfArray_oneElement() {
        System.out.println("testAddOnBottomOfArray_oneElement");
        Integer[] table = new Integer[]{1, 2};
        int element = 3;
        Integer[] expResult = new Integer[]{1, 2, 3};
        Integer[] result = ArrayUtils.addOnBottomOfArray(table, element);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of extendOnTopOfArray method, of class ArrayUtils.
     */
    @Test
    public void testExtendOnTopOfArray_one() {
        System.out.println("testExtendOnTopOfArray_one");
        String[] table = new String[]{"A", "B"};
        String[] expResult = new String[table.length + 1];
        expResult[1] = "A";
        expResult[2] = "B";
        String[] result = ArrayUtils.extendOnTopOfArray(table);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of extendOnBottomOfArray method, of class ArrayUtils.
     */
    @Test
    public void testExtendOnBottomOfArray_one() {
        System.out.println("testExtendOnBottomOfArray_one");
        String[] table = new String[]{"A", "B"};
        String[] expResult = new String[table.length + 1];
        expResult[0] = "A";
        expResult[1] = "B";
        String[] result = ArrayUtils.extendOnBottomOfArray(table);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of extendOnTopOfArray method, of class ArrayUtils.
     */
    @Test
    public void testExtendOnTopOfArray_multiple() {
        System.out.println("testExtendOnTopOfArray_multiple");
        String[] table = new String[]{"A", "B"};
        int extendedSize = 4;
        String[] expResult = new String[6];
        expResult[4] = "A";
        expResult[5] = "B";
        Object[] result = ArrayUtils.extendOnTopOfArray(table, extendedSize);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of extendOnBottomOfArray method, of class ArrayUtils.
     */
    @Test
    public void testExtendOnBottomOfArray_multiple() {
        System.out.println("testExtendOnBottomOfArray_multiple");
        String[] table = new String[]{"A", "B"};
        int extendedSize = 4;
        String[] expResult = new String[6];
        expResult[0] = "A";
        expResult[1] = "B";
        Object[] result = ArrayUtils.extendOnBottomOfArray(table, extendedSize);
        assertArrayEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of copyArray method, of class ArrayUtils.
     */
    @Test
    public void testCopyArray_simple() throws Exception {
        System.out.println("testCopyArray_simple");
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
        System.out.println("testCopyArray_complex");
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

    /**
     * Une classe Personne avec des getters pour le nom et l'âge et des méthodes
     * equals.
     */
    static class Personne {
        private final String nom;
        private final int age;

        public Personne(String nom, int age) {
            this.nom = nom;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Personne person)) {
                return false;
            }
            return person.nom.equals(nom) && person.age == age;
        }
    }
}
