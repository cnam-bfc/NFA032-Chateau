package net.cnam.utils.console;

import org.junit.Test;
import static org.junit.Assert.*;

public class CGraphicsTest {

    /**
     * Test of renderAddLine method, of class CGraphics.
     */
    @Test
    public void testRenderAddLine_1() {
        System.out.println("testRenderAddLine_1");
        String[] lines = new String[3];
        lines[0] = "a";
        lines[1] = "b";
        int linePointer = 2;
        String line = "c";
        int expResult = 3;
        int result = CGraphics.renderAddLine(lines, linePointer, line);
        assertEquals(expResult, result);
    }

    /**
     * Test of renderAddLine method, of class CGraphics.
     */
    @Test
    public void testRenderAddLine_2() {
        System.out.println("testRenderAddLine_2");
        String[] lines = new String[]{"a", "b", "c"};
        int linePointer = 3;
        String line = "d";
        int expResult = 3;
        int result = CGraphics.renderAddLine(lines, linePointer, line);
        assertEquals(expResult, result);
    }
}
