package CubeIx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Objects;

public class Game {
    private static final double GAME_SIZE = 720;
    private boolean currentInput = false;

    //For the gameloop
    private Timeline timeline;
    private final double TICK_TIME = 10;
    private int speed = 3;
    private int counter = 0;
    private int score = 0;

    public Game() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(TICK_TIME), event -> {
            Main.getPaser().shootingLaser(speed, Main.getGameRoot());
            getInput();
            if(currentInput) {
                Main.getPlayer().moveCube();
            }
            counter++;
            if(counter == 3000){
                speed++;
                counter = 0;
                System.out.println(speed);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void getInput() {
        if(Main.getPaser().getDead()){
            Main.getPlayer().getBody().setFill(Color.BLACK);
            if(Main.getPlayer().getLoopPosition() == 1){
                this.timeline.stop();
            }
        }
        Main.getGameScene().setOnKeyPressed(event -> {
            if (!currentInput) {
                switch (event.getCode()) {
                    case RIGHT:
                    case D:
                        if (Main.getPlayer().getPosition().equals("NW")) {
                            Main.getPlayer().setNextPosition("NE");
                            currentInput = true;
                        } else if (Main.getPlayer().getPosition().equals("SW")) {
                            Main.getPlayer().setNextPosition("SE");
                            currentInput = true;
                        }
                        break;
                    case LEFT:
                    case A:
                        if (Main.getPlayer().getPosition().equals("NE")) {
                            Main.getPlayer().setNextPosition("NW");
                            currentInput = true;
                        } else if (Main.getPlayer().getPosition().equals("SE")) {
                            Main.getPlayer().setNextPosition("SW");
                            currentInput = true;
                        }
                        break;
                    case UP:
                    case W:
                        if (Main.getPlayer().getPosition().equals("SE")) {
                            Main.getPlayer().setNextPosition("NE");
                            currentInput = true;
                        } else if (Main.getPlayer().getPosition().equals("SW")) {
                            Main.getPlayer().setNextPosition("NW");
                            currentInput = true;
                        }
                        break;
                    case DOWN:
                    case S:
                        if (Main.getPlayer().getPosition().equals("NW")) {
                            Main.getPlayer().setNextPosition("SW");
                            currentInput = true;
                        } else if (Main.getPlayer().getPosition().equals("NE")) {
                            Main.getPlayer().setNextPosition("SE");
                            currentInput = true;
                        }
                        break;
                }
            }
        });
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public static double getGAME_SIZE() {
        return GAME_SIZE;
    }

    public boolean isCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(boolean currentInput) {
        this.currentInput = currentInput;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
