package GameLogic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private List<Position> testTakenPositions=new ArrayList<>();
    private List<Knight> testKnights =new ArrayList<>(2);


    @Test
    void isTakenPosition() {
        Board testBoard=new Board(testKnights,testTakenPositions);
        testBoard.takePosition(new Position(1,1));
        testBoard.takePosition(new Position(2,2));
        testBoard.takePosition(new Position(3,3));

        assertTrue(testBoard.isTakenPosition(new Position(1,1)));
        assertTrue(testBoard.isTakenPosition(new Position(2,2)));
        assertTrue(testBoard.isTakenPosition(new Position(3,3)));

        assertFalse(testBoard.isTakenPosition(new Position(1,2)));
        assertFalse(testBoard.isTakenPosition(new Position(2,3)));
        assertFalse(testBoard.isTakenPosition(new Position(3,4)));
    }


    @Test
    void getTakenPositions() {
        Board testBoard=new Board(testKnights,testTakenPositions);
        testBoard.takePosition(new Position(1,1));
        assertTrue(testBoard.getTakenPositions().get(0).equals(new Position(1,1)));
        assertFalse(testBoard.getTakenPositions().get(0).equals(new Position(2,2)));
    }

    @Test
    void getBoardField() {
        Board testBoard=new Board();
        Knight testKnight=new Knight(ChessPiece.PieceColor.BLACK,new Position(0,6));
        Knight testKnight2=new Knight(ChessPiece.PieceColor.WHITE,new Position(7,1));
        assertEquals(testBoard.getBoardField(new Position(0,6)).getColor(),testKnight.getColor());
        assertEquals(testBoard.getBoardField(new Position(7,1)).getColor(),testKnight2.getColor());
        assertEquals(testBoard.getBoardField(new Position(1,1)),null);
    }
}