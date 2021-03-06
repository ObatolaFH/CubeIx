package CubeIx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Cube {
    private static final double CUBE_SIZE = 60;
    private String position;
    private int loopPosition;
    private String nextPosition;
    private Rectangle body;
    private double x_position = 0;
    private double y_position = 0;
    Stage stage;

    public Cube() {
        body = new Rectangle();
        body.setHeight(CUBE_SIZE);
        body.setWidth(CUBE_SIZE);
        body.setX(0);
        body.setY(0);
        loopPosition = 0;
        position = "NW";
        nextPosition = "NW";
        body.setFill(Color.RED);
    }

    public void moveCube() {
        switch (position) {
            case "NW":
                if (Main.getGame().isCurrentInput() && nextPosition.equals("NE")) {
                    this.body.setX(x_position + 10);
                    this.x_position += 10;
                    if (this.body.getX() == (Game.getGAME_SIZE()) - CUBE_SIZE) {
                        Main.getGame().setCurrentInput(false);
                        position = "NE";
                        loopPosition = 0;
                    }
                }else if (Main.getGame().isCurrentInput() && nextPosition.equals("SW")) {
                    this.body.setY(y_position + 10);
                    this.y_position += 10;
                    if (this.body.getY() == (Game.getGAME_SIZE()) - CUBE_SIZE) {
                        Main.getGame().setCurrentInput(false);
                        position = "SW";
                        loopPosition = 0;
                    }
                }
                break;
            case "NE":
                if (Main.getGame().isCurrentInput() && nextPosition.equals("NW")) {
                    this.body.setX(x_position - 10);
                    this.x_position -= 10;
                    if (this.body.getX() == 0) {
                        Main.getGame().setCurrentInput(false);
                        position = "NW";
                        loopPosition = 0;
                    }
                }else if (Main.getGame().isCurrentInput() && nextPosition.equals("SE")) {
                    this.body.setY(y_position + 10);
                    this.y_position += 10;
                    if (this.body.getY() == (Game.getGAME_SIZE()) - CUBE_SIZE) {
                        Main.getGame().setCurrentInput(false);
                        position = "SE";
                        loopPosition = 0;
                    }
                }
                break;
            case "SW":
                if (Main.getGame().isCurrentInput() && nextPosition.equals("SE")) {
                    this.body.setX(x_position + 10);
                    this.x_position += 10;
                    if (this.body.getX() == (Game.getGAME_SIZE()) - CUBE_SIZE) {
                        Main.getGame().setCurrentInput(false);
                        position = "SE";
                        loopPosition = 0;
                    }
                }else if (Main.getGame().isCurrentInput() && nextPosition.equals("NW")) {
                    this.body.setY(y_position - 10);
                    this.y_position -= 10;
                    if (this.body.getY() == 0) {
                        Main.getGame().setCurrentInput(false);
                        position = "NW";
                        loopPosition = 0;
                    }
                }
                break;
            case "SE":
                if (Main.getGame().isCurrentInput() && nextPosition.equals("SW")) {
                    this.body.setX(x_position - 10);
                    this.x_position -= 10;
                    if (this.body.getX() == 0) {
                        Main.getGame().setCurrentInput(false);
                        position = "SW";
                        loopPosition = 0;
                    }
                }else if (Main.getGame().isCurrentInput() && nextPosition.equals("NE")) {
                    this.body.setY(y_position - 10);
                    this.y_position -= 10;
                    if (this.body.getY() == 0) {
                        Main.getGame().setCurrentInput(false);
                        position = "NE";
                        loopPosition = 0;
                    }
                }
                break;
        }
        loopPosition++;
    }

    public static double getCUBE_SIZE() {
        return CUBE_SIZE;
    }

    public String getPosition() {
        return this.position;
    }

    public String getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(String nextPosition) {
        this.nextPosition = nextPosition;
    }

    public Rectangle getBody() {
        return body;
    }

    public int getLoopPosition(){
        return loopPosition;
    }
}
