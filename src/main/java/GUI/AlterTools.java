package GUI;

import GameLogic.ChessPiece;

/**
 * This is a color and click alter tool used by the GameController class.
 * This class is for the cleaner code.
 */

public class AlterTools {
    /**
     * Alter tool between from and to selection.
     */
    public enum SelectionPhase {
        SELECT_FROM,
        SELECT_TO;

        public SelectionPhase alter() {
            return switch (this) {
                case SELECT_FROM -> SELECT_TO;
                case SELECT_TO -> SELECT_FROM;
            };
        }
    }

    /**
     * Alter tool between WHITE and BLACK color.
     * @param color is a color should be changed
     * @return another color.
     */

    public ChessPiece.PieceColor colorAlter(ChessPiece.PieceColor color){
        if(color== ChessPiece.PieceColor.BLACK){
            return ChessPiece.PieceColor.WHITE;
        }
        else
        {
            return ChessPiece.PieceColor.BLACK;
        }
    }
}