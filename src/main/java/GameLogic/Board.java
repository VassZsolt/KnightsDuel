package GameLogic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a classic chessboard.
 */

public class Board {
    private final Knight[][] board = new Knight[8][8];
    private final List<Position> takenPositions=new ArrayList<>();

    private static final Logger logger= LogManager.getLogger();
    /**
     * Create a new Board object with the Knights on the starting Position.
     */
    public Board(){
        board[0][6]=new Knight(
                ChessPiece.PieceColor.BLACK,
                new Position(0,6)
        );

        board[7][1]=new Knight(
                ChessPiece.PieceColor.WHITE,
                new Position(7,1)
        );
        logger.debug("The board created with the starting state");
    }

    /**
     * Create a new Board object for the Load Game option with the Knights actual position
     * and the already taken Positions.
     *
     * @param knights is a list contains 2 Knight object.
     * @param takenPositions is a list contains 0 or more Positions
     */
    public Board(List<Knight> knights, List<Position> takenPositions){
        this.takenPositions.clear();
        this.takenPositions.addAll(takenPositions);
        for(Knight knight: knights) {
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    if (knight.getPosition().equals(new Position(row,column))){
                        board[row][column]=knight;
                    }
                }
            }
        }

        logger.debug("The board created with the last saved state");
    }

    /**
     * Decide whether a position is taken or not.
     * @param position is the Position that will be examined.
     * @return True when the position is already taken and false otherwise.
     */
    public boolean isTakenPosition(Position position){
        for(Position takenPosition: takenPositions){
            if(takenPosition.equals(position))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Take a position if it wasn't taken yet.
     * @param position is the Position object what will be taken.
     */
    public void takePosition(Position position){
        if(!isTakenPosition(position)){
            takenPositions.add(position);
            logger.debug("This position taken: "+position.toString());
        }
    }

    public List<Position> getTakenPositions(){
        return this.takenPositions;
    }

    /**
     * {@param position} is the Position of the field what we would like to get.
     * When there is a Knight on this position
     * @return with the Knight object otherwise returns null.
     */
    public Knight getBoardField(@NotNull Position position){ //! DANGER: Can return with null
        return board[position.getRow()][position.getColumn()];
    }

    /**
     * Move the Knight object from the board field defined by the 1st param to another field,
     * which is defined by the 2nd param
     * @param from is the field position that will be null after the step
     * @param to is the field position what will have the {@code from} value.
     */
    public void Move(@NotNull Position from, Position to){
        if(board[from.getRow()][from.getColumn()].isValidMove(from,to)) {
            board[to.getRow()][to.getColumn()] = board[from.getRow()][from.getColumn()];
            board[from.getRow()][from.getColumn()] = null;
            takePosition(from);
            board[to.getRow()][to.getColumn()].getPosition().setRow(to.getRow());
            board[to.getRow()][to.getColumn()].getPosition().setColumn(to.getColumn());
        }
    }
}