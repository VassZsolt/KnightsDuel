package GameLogic;

/**
 * This is a model of a ChessPiece with the required properties.
 */

public abstract class ChessPiece {

    /**
     * Represents the possible piece colors (Black and White).
     */
    public enum PieceColor{
        BLACK,
        WHITE
    }

    private PieceColor color;
    private Position position;

    /**
     * When we create a chessPiece like a knight it must have {@code PieceColor} and {@code Position} properties.
     * @param newColor represents the PieceColor property value
     * @param newPosition represents the Position property value.
     */

    public ChessPiece(PieceColor newColor, Position newPosition){
        this.color=newColor;
        this.position=newPosition;
    }

    public PieceColor getColor() {return color;}
    public Position getPosition() {return position;}
}
