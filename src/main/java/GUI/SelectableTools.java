package GUI;

import GameLogic.Board;
import GameLogic.ChessPiece;
import GameLogic.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the right color's pieces positions.
 * This class used by GameController and I extracted it for a cleaner code.
 */
public class SelectableTools {
    public List<Position> getPiecePositions(Board board, ChessPiece.PieceColor lastColor){
        List<Position> piecePositions=new ArrayList<>();
        for(int row=0; row<8;row++){
            for(int column=0; column<8;column++){
                Position temporaryPosition = new Position(row, column);
                var piece = board.getBoardField(temporaryPosition);
                if(piece!=null){
                    if(piece.getColor()!=lastColor) {
                        piecePositions.add(temporaryPosition);
                    }
                }
            }
        }
        return piecePositions;
    }
}