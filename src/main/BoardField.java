package main;


import javafx.scene.control.Button;

/**
 * Created by margus@workstation on 25.11.2015.
 */
public class BoardField {

    public int posX;
    public int posY;
    //nupul asub laev
    private boolean hasShip;
    private Button button;
    private GameBoard parentBoard;

    public BoardField(int posX, int posY, double sizeX, double sizeY, GameBoard parent){
        //nupu asukoht mänguväljal
        this.posX = posX;
        this.posY = posY;
        this.button = new Button();
        this.button.setStyle("-fx-font-weight: bold");
        this.button.setMinSize(sizeX, sizeY);
        this.button.setOnAction(event -> {
            clickButton(true);
        });
        this.parentBoard = parent;
    }

    public void setHasShip(boolean value){
        this.hasShip = value;
    }

    public boolean isHasShip() {
        return hasShip;
    }

    public Button getButton() {
        return button;
    }

    public void clickButton(boolean humanPlayer){
        this.button.setDisable(true);
        if (hasShip){
            button.setText("X");
            button.setStyle(
                    "-fx-background-color: dimgrey;" +
                    "-fx-text-fill: red; " +
                    "-fx-font-size: 150%"
            );
            if (humanPlayer){
                this.parentBoard.receiveButtonClick(true);
            }
        }else{
            button.setStyle("-fx-background-color: white");
            if (humanPlayer){
                this.parentBoard.receiveButtonClick(false);
            }
        }
    }




}
