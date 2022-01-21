package CubeIx;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    private static Game game;
    private static Pane homeRoot;
    private static Pane gameRoot;
    private static Cube player;
    private static Paser paser;
    private static Scene homeScene;
    private static Scene gameScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        game = new Game();
        game.getTimeline().stop();
        homeRoot = new Pane();
        gameRoot = new Pane();
        player = new Cube();
        paser = new Paser();

        homeScene = new Scene(homeRoot, Game.getGAME_SIZE(), Game.getGAME_SIZE());
        gameScene = new Scene(gameRoot, Game.getGAME_SIZE(), Game.getGAME_SIZE());



        primaryStage.setTitle("CubeIx");


        Button startGame = new Button("Start Game");
        homeRoot.getChildren().addAll(startGame);
        gameRoot.getChildren().addAll(player.getBody(), paser.getPaserBody());
        startGame.setOnAction(event -> {
            primaryStage.setScene(gameScene);
            game.getTimeline().play();
        });
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public static Game getGame() {
        return game;
    }

    public static Pane getHomeRoot() {
        return homeRoot;
    }

    public static Pane getGameRoot() {
        return gameRoot;
    }

    public static Cube getPlayer() {
        return player;
    }

    public static Scene getHomeScene() {
        return homeScene;
    }

    public static Scene getGameScene() {
        return gameScene;
    }
}
