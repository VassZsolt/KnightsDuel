package GameLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Knight piece.
 */

public class Knight extends ChessPiece {

    /**
     * Create a new Knight object with the params that defined by ChessPiece class.
     */
    public Knight(ChessPiece.PieceColor newColor, Position newPosition) {super(newColor, newPosition);}

    /**
     * Check every {@code possiblePosition} to store in a list the possible moves,
     * that means it's a valid Knight move and
     * the target field can't be taken yet.
     * @param from is the starting positions
     * @return a list contains the {@code possiblePositions}
     */

    public List<Position> getPossiblesMoves(Position from, Board board){
        List<Position> possibleMoves=new ArrayList<>();
        for(int row=0;row<8;row++){
            for(int column=0; column<8;column++){
                Position possiblePosition=new Position(row,column);
                if(isValidMove(from,possiblePosition) && (!board.isTakenPosition(possiblePosition))
                        && board.getBoardField(possiblePosition)==null){
                    possibleMoves.add(possiblePosition);
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Call the getPossibleMoves method with the given params and
     * @return the size of the possibleMoves list
     * what is a number between 0 and 8 (where 0 means no valid moves).
     */
    public int getNumberOfPossibleMoves(Position from, Board board){
        return getPossiblesMoves(from,board).size();
    }

    /**
     * Decides whether the step is valid or not.
     * @param from is the start position
     * @param to is the target positions
     * @return True when the move is valid and false otherwise.
     */
    public boolean isValidMove(Position from, Position to){
        return Math.abs(from.getRow() - to.getRow()) == 2 && Math.abs(from.getColumn() - to.getColumn()) == 1
                || Math.abs(from.getRow() - to.getRow()) == 1 && Math.abs(from.getColumn() - to.getColumn()) == 2;

    }
}