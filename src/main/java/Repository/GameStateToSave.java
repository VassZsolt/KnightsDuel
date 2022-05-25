package Repository;

import GameLogic.Board;
import GameLogic.ChessPiece;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameStateToSave {
    private String playerOneName;
    private String playerTwoName;
    private ChessPiece.PieceColor lastColor;
    private Board board;

    public GameStateToSave(String playerOneName, String playerTwoName, ChessPiece.PieceColor lastColor, Board board){
        this.playerOneName=playerOneName;
        this.playerTwoName=playerTwoName;
        this.lastColor=lastColor;
        this.board=board;
    }
    public String getPlayerOne(){
        return playerOneName;
    }
    public String getPlayerTwoName() {
        return playerTwoName;
    }
    public ChessPiece.PieceColor getLastColor() {
        return lastColor;
    }
    public Board getBoard() {
        return board;
    }
}
