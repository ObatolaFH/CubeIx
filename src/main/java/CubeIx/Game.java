package CubeIx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Objects;

public class Game {
    private final int GAME_SIZE = 720;
    private boolean currentInput = false;

    //For the gameloop
    private Timeline timeline;
    private final double TICK_TIME = 200;

    public Game(){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(TICK_TIME), event -> {
            getInput();
            //Main.getPlayer().moveCube();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void getInput(){
        Main.getGameScene().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case RIGHT:
                case A:
                    if(Main.getPlayer().getPosition().equals("NW") &&
                            !Objects.equals(Main.getPlayer().getPosition(), "NE")){
                        Main.getPlayer().setNextPosition("NE");
                    }
                    if(Main.getPlayer().getPosition().equals("SW") &&
                            !Objects.equals(Main.getPlayer().getPosition(), "SE")){
                        Main.getPlayer().setNextPosition("SE");
                    }
                    currentInput = true;
                    Main.getPlayer().moveCube();
                    break;
                case LEFT:
                case D:
                    currentInput = true;
                    break;
                case UP:
                case W:
                    currentInput = true;
                    break;
                case DOWN:
                case S:
                    currentInput = true;
                    break;
            }
        });
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public int getGAME_SIZE() {
        return GAME_SIZE;
    }

    public boolean isCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(boolean currentInput) {
        this.currentInput = currentInput;
    }
}
