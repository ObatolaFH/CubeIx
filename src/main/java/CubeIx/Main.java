package CubeIx;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CubeIx");
        Pane root = new Pane();
        Scene homeScene = new Scene(root, 720, 720);
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }
}
