package GameLogic;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    Position testPosition1=new Position(1,1);
    Position testPosition2=new Position(2,2);

    @Test
    void testToString() {
        assertEquals("(1,1)",testPosition1.toString());
        assertEquals("(2,2)",testPosition2.toString());
        assertFalse("(1,1)".equals(testPosition2.toString()));
        assertFalse("(2,2)".equals(testPosition1.toString()));
        assertTrue("(1,1)".equals(testPosition1.toString()));
        assertTrue("(2,2)".equals(testPosition2.toString()));
    }

    @Test
    void testEquals() {
        assertFalse(testPosition1.equals(testPosition2));
        assertFalse(testPosition2.equals(testPosition1));
        assertTrue(testPosition1.equals(testPosition1));
        assertTrue(testPosition2.equals(testPosition2));
        assertTrue(testPosition1.equals(new Position(1,1)));
        assertTrue(testPosition2.equals(new Position(2,2)));
    }

    @Test
    void getRow() {
        assertEquals(1, testPosition1.getRow());
        assertEquals(2, testPosition2.getRow());
        assertNotEquals(testPosition1.getRow(), testPosition2.getRow());
    }

    @Test
    void getColumn() {
        assertEquals(1, testPosition1.getColumn());
        assertEquals(2, testPosition2.getColumn());
        assertNotEquals(testPosition1.getColumn(), testPosition2.getColumn());
    }
}