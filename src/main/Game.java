package main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by margus@workstation on 26.11.2015.
 */
public class Game {

    private GameBoard playerBoard;
    private GameBoard enemyBoard;
    private Scene scene;
    private Label playerScoreLabel;
    private int playerScore = 0;
    private Label enemyScoreLabel;
    private int enemyScore = 0;
    private Label message;

    public Game(int sizeX, int sizeY, int shipCount){
        VBox gameLayout = new VBox();
        gameLayout.setPrefSize(400, 850);

        Label playerLabel = new Label("Sinu mängulaud");
        this.playerBoard = new GameBoard(sizeX, sizeY, shipCount, this);
        GridPane playerGrid = playerBoard.getBoardLayout();

        Label enemyLabel = new Label ("Vastase mängulaud");
        this.enemyBoard = new GameBoard(sizeX, sizeY, shipCount, this);
        GridPane enemyGrid = enemyBoard.getBoardLayout();

        //lisan elemendid
        gameLayout.getChildren().addAll(playerLabel, playerGrid, enemyLabel, enemyGrid);


        //parempoolne layout
        VBox rightLayout = new VBox();
        rightLayout.setPadding(new Insets(0, 0, 0, 10));

        Button button = new Button("Uus mäng");
        button.setOnAction(event -> {
            BattleShips.newGameAction();
        });
        this.playerScoreLabel = new Label("Sinu punkte: 0");
        this.enemyScoreLabel = new Label("Arvuti punkte: 0");
        this.message = new Label("");
        //lisan layout-i
        rightLayout.getChildren().addAll(button, this.playerScoreLabel, this.enemyScoreLabel, this.message);

        //terve akna layout
        HBox fullLayout = new HBox();
        fullLayout.getChildren().addAll(gameLayout, rightLayout);
        this.scene = new Scene(fullLayout, 600, 850);

    }

    public Scene getScene() {
        return scene;
    }

    public void endGame(){
        this.message.setText("Mäng läbi!");
        this.message.setStyle("-fx-font-size: 150%; -fx-text-fill: red");
        //lülita nupud välja
        this.playerBoard.disableButtons();
        this.enemyBoard.disableButtons();
    }

    public void enemyPlay(){
        //leiab mänguväljalt juhusliku nupu
        int random = (int) (Math.random() * this.playerBoard.getButtonList().size());

        //klikin nupul
        BoardField field = this.playerBoard.getButtonList().get(random);
        field.clickButton(false);

        //eemaldan nupu mänguvälja nuppude nimekirjast, kuna sellele uuest nagunii klikkida ei saa
        this.playerBoard.removeButton(field);

        //kui arvuti sai laevale pihta, mängigu uuesti
        if (field.isHasShip()){
            increaseEnemyScore();
            enemyPlay();
        }
    }

    public void increasePlayerScore(){
        this.playerScore++;
        this.playerScoreLabel.setText("Sinu punkte: " + Integer.toString(this.playerScore));
    }

    public void increaseEnemyScore(){
        this.enemyScore++;
        this.enemyScoreLabel.setText("Arvuti punkte: " + Integer.toString(this.enemyScore));
    }
}
