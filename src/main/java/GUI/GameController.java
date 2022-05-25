package GUI;

import GameLogic.Board;
import GameLogic.ChessPiece;
import GameLogic.Knight;
import GameLogic.Position;
import Repository.GameStateToSave;
import Repository.GameStateToSaveRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController {
    private AlterTools alterTools;
    private AlterTools.SelectionPhase selectionPhase;
    private SelectableTools selectableTools;
    private List<Position> selectablePositions;
    private ChessPiece.PieceColor lastColor;
    private StartingPage startingPage;
    private GameOverController gameOverController=new GameOverController();
    private Board board;
    private Position selected;
    private Logger logger= LogManager.getLogger();

    private static String playerOne;
    private static String playerTwo;

    public static void setPlayers(String newPlayerOne, String newPlayerTwo){
        playerOne=newPlayerOne;
        playerTwo=newPlayerTwo;
    }

    @FXML
    private GridPane boardGUI;

    private void initialiseVariableValues(){
        alterTools=new AlterTools();
        selectionPhase= AlterTools.SelectionPhase.SELECT_FROM;
        selectableTools=new SelectableTools();
        selectablePositions=new ArrayList<>();
        lastColor= ChessPiece.PieceColor.BLACK;
        startingPage=new StartingPage();
        board=new Board();
        from=null;
    }

    @FXML
    private void createNewBoardGUI(){
        boardGUI=new GridPane();
    }

    @FXML
    private void initialize() {
        initialiseVariableValues();
        createBoardGUI();
        createPieces();
        setSelectablePositions(selected,lastColor);
        fillTakenPositions();
        showSelectablePositions();
    }

    private void createBoardGUI() {
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++) {
                var square = createSquare();
                if(row%2==0 && column%2==0 || row%2==1 && column%2==1){
                    square.getStyleClass().add("light");
                }
                else {
                    square.getStyleClass().add("dark");
                }
                boardGUI.add(square, column, row);
            }
        }
        logger.debug("The Board created and showed.");
    }
    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }
    private void createPieces() {
        if(board==null){
            board=new Board();
        }
        for(int row=0; row<8;row++) {
            for (int column = 0; column < 8; column++) {
                Position temporaryPosition=new Position(row,column);
                var piece=board.getBoardField(temporaryPosition);
                if(piece!=null){
                    if(piece.getColor()== ChessPiece.PieceColor.BLACK){
                        Image image = new Image("/img/b_knight.png");
                        ImageView blackKnight = new ImageView(image);
                        getSquare(temporaryPosition).getChildren().add(blackKnight);
                    }
                    else {
                        Image image = new Image("/img/w_knight.png");
                        ImageView whiteKnight = new ImageView(image);
                        getSquare(temporaryPosition).getChildren().add(whiteKnight);
                    }
                }
                else
                {
                    getSquare(temporaryPosition).getChildren().clear();
                }
            }
        }
    }
    private StackPane getSquare(Position position) {
        for (var child : boardGUI.getChildren()) {
            Position temporaryPosition=new Position(GridPane.getRowIndex(child)-1,GridPane.getColumnIndex(child)-1);
            if (temporaryPosition.equals(position)) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }
    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        var position = new Position(row-1, col-1);
        logger.debug("Click on square: "+ position.toString());
        handleClickOnSquare(position);
    }

    private Position from;
    private void handleClickOnSquare(Position position) {
        switch (selectionPhase) {
            case SELECT_FROM -> {
                boolean itWasPossibleFrom=false;
                for(Position selectablePosition:selectablePositions){
                    if(selectablePosition.equals(position)){
                        itWasPossibleFrom=true;
                        break;
                    }
                }
                if(itWasPossibleFrom){
                    selectPosition(position);
                    alterSelectionPhase();
                    from =position;
                }
            }
            case SELECT_TO -> {
                boolean itWasPossibleTo=false;
                for(Position selectablePosition:selectablePositions){
                    if(selectablePosition.equals(position)){
                        itWasPossibleTo=true;
                    }
                }
                if(itWasPossibleTo){
                    if(board.getBoardField(from).isValidMove(from,position)){
                        board.Move(from,position);
                        createPieces();
                        fillTakenPositions();
                        lastColor=alterTools.colorAlter(lastColor);
                    }
                    deselectSelectedPosition();
                    alterSelectionPhase();
                }
            }
        }
    }



    private void fillTakenPositions() {
        for(Position takenPosition:board.getTakenPositions()){
            var square = getSquare(takenPosition);
            square.getStyleClass().add("taken");
        }
    }
    private void selectPosition(Position position) {
        selected = position;
        showSelectedPosition();
    }
    private void showSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().add("selected");
    }
    private void alterSelectionPhase() {
        selectionPhase = selectionPhase.alter();
        hideSelectablePositions();
        setSelectablePositions(selected,lastColor);
        showSelectablePositions();
    }
    private void hideSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().remove("selectable");
        }
    }
    private void showSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().add("selectable");
        }
    }
    private void deselectSelectedPosition() {
        hideSelectedPosition();
        selected = null;
    }
    private void hideSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().remove("selected");
    }
    private void setSelectablePositions(Position selected, ChessPiece.PieceColor lastColor) {
        selectablePositions.clear();
        if(selected!=null) {
            Position temporaryPosition = new Position(selected.getRow(), selected.getColumn());
            var piece = board.getBoardField(temporaryPosition);
            if(piece!=null){
                if(piece.getColor()!=lastColor){
                    selectablePositions.addAll(piece.getPossiblesMoves(selected,board));
                    if(selectablePositions.isEmpty()){
                        if(lastColor==ChessPiece.PieceColor.WHITE){
                            gameOverController.handleGameOver(lastColor,playerOne);
                        }
                        else{
                            gameOverController.handleGameOver(lastColor,playerTwo);
                        }
                    }
                    lastColor=piece.getColor();
                }
            }
        }
        else {selectablePositions.addAll(selectableTools.getPiecePositions(board,lastColor));}
    }

    @FXML
    private void handleNewGame(ActionEvent event) throws IOException {
        startingPage.startGameAgain(event);
    }

    @FXML
    private void handleSaveGame(ActionEvent event){
        var repository=new GameStateToSaveRepository();
        GameStateToSave gameStateToSave=new GameStateToSave(playerOne,playerTwo,lastColor,board);
        repository.add(gameStateToSave);
        try {
            repository.saveToFile(new File("savedGame.json"));
            logger.debug("Game successfully saved!");
        } catch (IOException e) {
            logger.warn("Game could not be saved!");
        }
    }
    @FXML
    private void handleLoadGame() {
        initialiseVariableValues();
        var repository=new GameStateToSaveRepository();
        try {
            repository.loadFromFile(new File("savedGame.json"));
            var savedGame=repository.findAll();
            lastColor=savedGame.get(0).getLastColor();
            setPlayers(savedGame.get(0).getPlayerOneName(),savedGame.get(0).getPlayerTwoName());

            var savedBoard=savedGame.get(0).getBoard();
            board=savedBoard;

            List<Position> takenPositions=savedBoard.getTakenPositions();
            List<Knight> knights=new ArrayList<>();

            for(int row=0; row<8;row++){
                for(int column=0; column<8;column++){
                    var knight=savedBoard.getBoardField(new Position(row,column));
                    if(knight!=null){
                        knights.add(knight);
                    }
                }
            }
            logger.debug("Saved game successfully loaded!");

        } catch (IOException e) {
            logger.warn("Results could not be loaded!");
        }

        for(int row=0; row<8; row++){
            for(int column=0; column<8; column++){
                getSquare(new Position(row,column)).getChildren().clear();
            }
        }
        createPieces();
        setSelectablePositions(selected,lastColor);
        fillTakenPositions();
        showSelectablePositions();
    }
    @FXML
    private void handleExit(ActionEvent event) {
        logger.info("The game ended, now we quit.");
        System.out.println("Exiting...");
        Platform.exit();
    }
}