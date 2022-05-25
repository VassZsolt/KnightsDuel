package GameLogic;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents the position of a Piece on the board.
 */

public class Position {
    private int row;
    private int column;

    /**
     * Create a new {@code Position} object with the given params.
     * @param newRow is the row component
     * @param newColumn is the column component
     */

    public Position(int newRow, int newColumn) {
        this.row=newRow;
        this.column=newColumn;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int newRow){
        row=newRow;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int newColumn) {
        column=newColumn;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", row, column);
    }

    public boolean equals(@NotNull Position position){
        return this.row == position.getRow() && this.column == position.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}