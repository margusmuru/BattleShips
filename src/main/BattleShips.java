package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by margus@workstation on 25.11.2015.
 */
public class BattleShips extends Application{

    private static Stage window;


    public static void main(String[] args){
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        //akna suurus mittemuudetavaks
        this.window.setResizable(false);
        window.setTitle("Laevade pommitamine");

        //uus põhiline layout tervele aknale
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        Button newGameButton = new Button("Uus mäng");
        newGameButton.setPrefSize(300, 150);
        newGameButton.setStyle("-fx-font-size: 200%");
        newGameButton.setOnAction(event -> newGameAction());

        layout.getChildren().addAll(newGameButton);

        // alguskuva, millel nupp "uus mäng"
        Scene scene = new Scene(layout, 600, 850);

        //määran programmi akna scene-iks vaikimisi scene-i
        window.setScene(scene);
        //kuvan akent
        window.show();
    }

    public static void newGameAction(){
        //loon uue objekti "Game"
        //game loob uue mänguvälja koos loogikaga
        Game newGame = new Game(5, 5, 10);
        //Game-il on oma scene. määran selle programmi akna jaoks
        Scene gameScene = newGame.getScene();
        window.setScene(gameScene);
    }





}
