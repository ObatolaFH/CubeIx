package CubeIx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Cube {
    private final double CUBE_SIZE = 65;
    private String position;
    private String nextPosition;
    private Rectangle body;
    private double x_position = 0;
    private double y_position = 0;
    Stage stage;

    public Cube(Pane pane) {
        body = new Rectangle();
        body.setHeight(CUBE_SIZE);
        body.setWidth(CUBE_SIZE);
        body.setX(0);
        body.setY(0);
        position = "NW";
        body.setFill(Color.RED);
        pane.getChildren().addAll(body);
    }

    public void moveCube() {
        if (Main.getGame().isCurrentInput()) {
            switch (position) {
                case "NW":
                    if (Main.getGame().isCurrentInput() && nextPosition.equals("NE")) {
                        this.body.setX(x_position + 5);
                        this.x_position += 5;
                        if (this.body.getX() == Main.getGame().getGAME_SIZE()) {
                            Main.getGame().setCurrentInput(false);
                        } else if (Main.getGame().isCurrentInput() && nextPosition.equals("SW")) {
                            this.body.setY(x_position + 5);
                            this.y_position += 5;
                            if (this.body.getY() == Main.getGame().getGAME_SIZE()) {
                                Main.getGame().setCurrentInput(false);
                            }
                        }
                    }
                    break;
                /*
            case "NE":
                if (Main.getGame().isCurrentInput() && nextPosition.equals("NE")) {
                    this.body.setX(x_position + 5);
                    if (this.body.getX() == Main.getGame().getGAME_SIZE()) {
                        Main.getGame().setCurrentInput(false);
                        position = "NW";
                    }
                } else if (Main.getGame().isCurrentInput() && nextPosition.equals("SW")) {
                    this.body.setY(x_position + 5);
                    if (this.body.getY() == Main.getGame().getGAME_SIZE()) {
                        Main.getGame().setCurrentInput(false);
                    }
                }
                 */
            }
        }
    }

    public double getCUBE_SIZE() {
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
}
