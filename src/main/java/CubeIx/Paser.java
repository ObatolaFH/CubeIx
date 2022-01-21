package CubeIx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paser {
    private final double PASER_SIZE = Cube.getCUBE_SIZE() * 0.6;
    private Rectangle paserBody;

    public Paser(){
        paserBody = new Rectangle();
        paserBody.setHeight(PASER_SIZE);
        paserBody.setWidth(PASER_SIZE);
        paserBody.setX((Game.getGAME_SIZE() / 2) - (PASER_SIZE / 2));
        paserBody.setY((Game.getGAME_SIZE() / 2) - (PASER_SIZE / 2));
        paserBody.setFill(Color.BLUE);
    }

    public Rectangle getPaserBody() {
        return paserBody;
    }
}
