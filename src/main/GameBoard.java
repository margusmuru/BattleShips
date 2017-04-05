package main;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by margus@workstation on 25.11.2015.
 */
public class GameBoard {

    private GridPane boardLayout;
    private List<BoardField> buttonList;
    private int sizeX;
    private int sizeY;
    private int shipCount;
    private int hitCount;
    private Game curGame;

    public GameBoard(int sizeX, int sizeY, int shipCount, Game curGame){
        this.shipCount = shipCount;
        //salvestan siia mänguvälja suuruse
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        //loon uue layout-i nuppudega
        this.boardLayout = new GridPane();
        //loon uue ArrayList-i, milles on kõikmänguvälja nupud
        this.buttonList = new ArrayList<>();
        //refereering praegusele mänguObjektile
        this.curGame = curGame;

        //loo mänguvälja nupud
        for (int i = 0; i < sizeX; i++){
            for (int j = 0; j < sizeY; j++){
                //uus mänguvälja nupp
                BoardField field = new BoardField(i, j, 400 / sizeX, 400 / sizeY, this);
                //määran nupu asukohta GridPane-il
                GridPane.setConstraints(field.getButton(), i, j);
                //lisan nupu gridpane-ile
                this.boardLayout.getChildren().add(field.getButton());
                //pole kindel kas vaja TODO
                this.buttonList.add(field);
            }
        }
        fillBoardRandomly(shipCount);
    }

    public GridPane getBoardLayout() {
        return boardLayout;
    }

    public void receiveButtonClick(boolean hit){
        //loendan laevade pihtasaamisi
        if (hit){
            this.hitCount++;
            this.curGame.increasePlayerScore();
        }
        //kontrollin, kas kõik laevad on hävitatud ja peaks mängu lõpetama
        if (this.hitCount == this.shipCount){
            this.curGame.endGame();
        }

        //arvuti kord mängida, kui mängija ei tabanud laeva
        if (!hit){
            this.curGame.enemyPlay();
        }

    }

    public void removeButton(BoardField field){
        this.buttonList.remove(field);
    }

    public List<BoardField> getButtonList() {
        return buttonList;
    }

    public void fillBoardRandomly(int shipCount){
        for (int i = 0; i < shipCount; i++){
            boolean shipAdded = false;
            // teen, kuni leian nupu, millel pole juba laeva
            while(!shipAdded){
                //juhuslik nupp nuppude nimekirjast
                int random = (int)(Math.random() * this.buttonList.size() - 1);
                //kontrollin
                if (!this.buttonList.get(random).isHasShip()){
                    this.buttonList.get(random).setHasShip(true);
                    shipAdded = true;
                }
            }
        }
        //laevad lisatud
    }

    public void disableButtons(){
        for (int i = 0; i < this.buttonList.size(); i++){
            this.buttonList.get(i).getButton().setDisable(true);
        }
    }

}
