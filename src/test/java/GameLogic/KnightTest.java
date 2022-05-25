package GameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    Position testPosition=new Position(4, 4);
    Knight testKnight1 = new Knight(ChessPiece.PieceColor.BLACK,testPosition);


    @Test
    void numberOfPossibleMoves() {
        assertEquals(8,testKnight1.getNumberOfPossibleMoves(testPosition,new Board()));
        Position testPosition2=new Position(0,6);
        assertEquals(3,testKnight1.getNumberOfPossibleMoves(testPosition2,new Board()));
        assertFalse(0==testKnight1.getNumberOfPossibleMoves(testPosition,new Board()));
    }

    @Test
    void isValidMoveShouldTrue() {
        Position testToPosition1=new Position(6,5);
        Position testToPosition2=new Position(6,3);
        Position testToPosition3=new Position(2,5);
        Position testToPosition4=new Position(2,3);
        Position testToPosition5=new Position(3,6);
        Position testToPosition6=new Position(3,2);
        Position testToPosition7=new Position(5,6);
        Position testToPosition8=new Position(5,2);

        assertTrue(testKnight1.isValidMove(testPosition,testToPosition1));
        assertTrue(testKnight1.isValidMove(testPosition,testToPosition2));
        assertTrue(testKnight1.isValidMove(testPosition,testToPosition3));
        assertTrue(testKnight1.isValidMove(testPosition,testToPosition4));
        assertTrue(testKnight1.isValidMove(testPosition,testToPosition5));
        assertTrue(testKnight1.isValidMove(testPosition,testToPosition6));
        assertTrue(testKnight1.isValidMove(testPosition,testToPosition7));
        assertTrue(testKnight1.isValidMove(testPosition,testToPosition8));

        Position testFromPosition1=new Position(7,1);

        assertTrue(testKnight1.isValidMove(testFromPosition1,testToPosition2));
        assertTrue(testKnight1.isValidMove(testFromPosition1,testToPosition8));
    }

    @Test
    void isValidMoveShouldFalse() {
        Position testFromPosition1= new Position(0,0);
        Position testToPosition1=new Position(6,5);
        Position testToPosition2=new Position(6,3);
        Position testToPosition3=new Position(2,5);
        Position testToPosition4=new Position(2,3);
        Position testToPosition5=new Position(3,6);
        Position testToPosition6=new Position(3,2);
        Position testToPosition7=new Position(5,6);
        Position testToPosition8=new Position(5,2);

        assertFalse(testKnight1.isValidMove(testPosition,testPosition));
        assertFalse(testKnight1.isValidMove(testFromPosition1,testToPosition1));
        assertFalse(testKnight1.isValidMove(testFromPosition1,testToPosition2));
        assertFalse(testKnight1.isValidMove(testFromPosition1,testToPosition3));
        assertFalse(testKnight1.isValidMove(testFromPosition1,testToPosition4));
        assertFalse(testKnight1.isValidMove(testFromPosition1,testToPosition5));
        assertFalse(testKnight1.isValidMove(testFromPosition1,testToPosition6));
        assertFalse(testKnight1.isValidMove(testFromPosition1,testToPosition7));
        assertFalse(testKnight1.isValidMove(testFromPosition1,testToPosition8));
    }
}