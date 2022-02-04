package CubeIx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.Random;

public class Paser {
    private final double PASER_SIZE = Cube.getCUBE_SIZE() * 0.6;
    private Rectangle paserBody;
    private Rectangle laser;
    private double[] laserPosition_x;
    private double[] laserPosition_y;
    private Random ran;
    private int randomNumber;
    private boolean shooting;
    private String laserPosition;

    public Paser() {
        paserBody = new Rectangle();
        ran = new Random();
        paserBody.setHeight(PASER_SIZE);
        paserBody.setWidth(PASER_SIZE);
        paserBody.setX((Game.getGAME_SIZE() / 2) - (PASER_SIZE / 2));
        paserBody.setY((Game.getGAME_SIZE() / 2) - (PASER_SIZE / 2));
        paserBody.setFill(Color.BLUE);

        laser = new Rectangle();
        laser.setHeight(PASER_SIZE * 0.5);
        laser.setWidth(PASER_SIZE / 4);
        laserPosition_x = new double[]{paserBody.getX() - (laser.getWidth() / 2),
                paserBody.getX() + PASER_SIZE - (laser.getWidth() / 2),
                paserBody.getX() - (laser.getWidth() / 2), paserBody.getX() + PASER_SIZE - (laser.getWidth() / 2)};

        laserPosition_y = new double[]{paserBody.getY() - laser.getHeight(), paserBody.getY() - laser.getHeight(),
                paserBody.getY() + PASER_SIZE, paserBody.getY() + PASER_SIZE};

        laser.setX(laserPosition_x[0]);
        laser.setY(laserPosition_y[0]);
        laser.setFill(Color.YELLOW);
    }

    public void shootingLaser(int speed, Pane pane){
        if(!shooting) {
            this.laserPosition = "N/A";
            this.randomNumber = ran.nextInt(4);
            laser.setX(laserPosition_x[randomNumber]);
            laser.setY(laserPosition_y[randomNumber]);

            switch (this.randomNumber) {
                case 0 -> laser.getTransforms().add(new Rotate(-45, paserBody.getX(), paserBody.getY()));
                case 1 -> laser.getTransforms().add(new Rotate(45, (paserBody.getX() + PASER_SIZE),
                        paserBody.getY()));
                case 2 -> laser.getTransforms().add(new Rotate(45, paserBody.getX(),
                        (paserBody.getY() + PASER_SIZE)));
                case 3 -> laser.getTransforms().add(new Rotate(-45, (paserBody.getX() + PASER_SIZE),
                        (paserBody.getY() + PASER_SIZE)));
            }
            pane.getChildren().addAll(laser);
            this.shooting = true;
        }
        switch (this.randomNumber) {
            case 0, 1 -> this.laser.setY(this.laser.getY() - speed);
            case 2, 3 -> this.laser.setY(this.laser.getY() + speed);
        }
        //Main.getGame().getTimeline().stop();
        if(laser.getY() < -Cube.getCUBE_SIZE() || laser.getY() > (Game.getGAME_SIZE() + PASER_SIZE)){
            pane.getChildren().removeAll(laser);
            switch (this.randomNumber) {
                case 0 -> {
                    laser.getTransforms().add(new Rotate(45, paserBody.getX(), paserBody.getY()));
                    laserPosition = "NW";
                }
                case 1 -> {
                    laser.getTransforms().add(new Rotate(-45, (paserBody.getX() + PASER_SIZE),
                            paserBody.getY()));
                    laserPosition = "NE";
                }
                case 2 -> {
                    laser.getTransforms().add(new Rotate(-45, paserBody.getX(),
                            (paserBody.getY() + PASER_SIZE)));
                    laserPosition = "SW";
                }
                case 3 -> {
                    laser.getTransforms().add(new Rotate(45, (paserBody.getX() + PASER_SIZE),
                            (paserBody.getY() + PASER_SIZE)));
                    laserPosition = "SE";
                }
            }
            this.shooting = false;
            if(laserPosition.equals(Main.getPlayer().getPosition()) &&
                    laserPosition.equals(Main.getPlayer().getNextPosition())){
                Main.getGame().getTimeline().stop();
            }
        }
    }

    public Rectangle getPaserBody() {
        return paserBody;
    }

    public Rectangle getLaser() {
        return laser;
    }
}
