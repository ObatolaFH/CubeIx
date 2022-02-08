package CubeIx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.Random;

public class Paser {
    private final double PASER_SIZE = Cube.getCUBE_SIZE() * 0.6;
    private Rectangle paserBody;
    private Rectangle laser, laser2;
    private double[] laserPosition_x;
    private double[] laserPosition_y;
    private Random ran, ran2;
    private int randomNumber, randomNumber2;
    private boolean shooting, shooting2;
    private boolean hit;
    private boolean dead;
    private String laserPosition, laserPosition2;
    private int healthBar;

    public Paser() {
        paserBody = new Rectangle();
        ran = new Random();
        ran2 = new Random();
        healthBar = 100;
        paserBody.setHeight(PASER_SIZE);
        paserBody.setWidth(PASER_SIZE);
        paserBody.setX((Game.getGAME_SIZE() / 2) - (PASER_SIZE / 2));
        paserBody.setY((Game.getGAME_SIZE() / 2) - (PASER_SIZE / 2));
        paserBody.setFill(Color.BLUE);

        laser = new Rectangle();
        laser.setHeight(PASER_SIZE * 0.5);
        laser.setWidth(PASER_SIZE / 4);

        laser2 = new Rectangle();
        laser2.setHeight(PASER_SIZE * 0.5);
        laser2.setWidth(PASER_SIZE / 4);

        laserPosition_x = new double[]{paserBody.getX() - (laser.getWidth() / 2),
                paserBody.getX() + PASER_SIZE - (laser.getWidth() / 2),
                paserBody.getX() - (laser.getWidth() / 2), paserBody.getX() + PASER_SIZE - (laser.getWidth() / 2)};

        laserPosition_y = new double[]{paserBody.getY() - laser.getHeight(), paserBody.getY() - laser.getHeight(),
                paserBody.getY() + PASER_SIZE, paserBody.getY() + PASER_SIZE};

        laser.setX(laserPosition_x[0]);
        laser.setY(laserPosition_y[0]);
        laser.setFill(Color.YELLOW);

        laser2.setX(laserPosition_x[0]);
        laser2.setY(laserPosition_y[0]);
        laser2.setFill(Color.YELLOW);
    }

    public void shootingLaser(int speed, Pane pane){
        if(Main.getGame().getScore() == 25) {
            Main.getGame().setSpeed(3);
            shootingLaser2(speed, pane);
        }

        if(!shooting && !dead) {
            this.laserPosition = "N/A";
            this.hit = false;
            Main.getPlayer().getBody().setFill(Color.RED);
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
        if(laser.getY() < -(Math.sqrt(Math.pow(Cube.getCUBE_SIZE(), 2) + Math.pow(Cube.getCUBE_SIZE(), 2)) * 0.75) ||
                laser.getY() > (Game.getGAME_SIZE() + (Math.sqrt(Math.pow(Cube.getCUBE_SIZE(), 2) + Math.pow(Cube.getCUBE_SIZE(), 2)) / 2))){

            switch (this.randomNumber) {
                case 0 -> laserPosition = "NW";
                case 1 -> laserPosition = "NE";
                case 2 -> laserPosition = "SW";
                case 3 -> laserPosition = "SE";
            }


            if(laser.getY() < (Game.getGAME_SIZE() / 2) - (Math.sqrt(Math.pow(Game.getGAME_SIZE() / 2, 2) + Math.pow(Game.getGAME_SIZE() / 2, 2))) ||
                    laser.getY() > (Game.getGAME_SIZE() / 2) + Math.sqrt(Math.pow(Game.getGAME_SIZE() / 2, 2) + Math.pow(Game.getGAME_SIZE() / 2, 2))){
                this.shooting = false;

                switch (this.randomNumber) {
                    case 0 -> laser.getTransforms().add(new Rotate(45, paserBody.getX(), paserBody.getY()));
                    case 1 -> laser.getTransforms().add(new Rotate(-45, (paserBody.getX() + PASER_SIZE),
                            paserBody.getY()));
                    case 2 -> laser.getTransforms().add(new Rotate(-45, paserBody.getX(),
                                (paserBody.getY() + PASER_SIZE)));
                    case 3 -> laser.getTransforms().add(new Rotate(45, (paserBody.getX() + PASER_SIZE),
                                (paserBody.getY() + PASER_SIZE)));
                }

                Main.getGame().setScore(Main.getGame().getScore() + 1);
                System.out.println(Main.getGame().getScore());

                pane.getChildren().removeAll(laser);
            }

            if(Main.getPlayer().getLoopPosition() < 5 && laserPosition.equals(Main.getPlayer().getPosition())){
                pane.getChildren().removeAll(laser);
                if(!hit) {
                    healthBar -= 10;
                    System.out.println(healthBar);
                    Main.getPlayer().getBody().setFill(Color.BLUE);
                }
                if(this.healthBar == 0) {
                    dead = true;
                }
                hit = true;

            }else if(Main.getPlayer().getLoopPosition() > 60 && laserPosition.equals(Main.getPlayer().getNextPosition())){
                pane.getChildren().removeAll(laser);
                if(!hit) {
                    healthBar -= 10;
                    System.out.println(healthBar);
                    Main.getPlayer().getBody().setFill(Color.BLUE);
                }
                if(this.healthBar == 0) {
                    dead = true;
                }
                hit = true;
            }

        }
        if(Main.getGame().getScore() > 25){
            shootingLaser2(speed, pane);
        }
    }

    public void shootingLaser2(int speed, Pane pane){
        if(!shooting2 && !dead) {
            this.laserPosition2 = "N/A";
            this.hit = false;
            Main.getPlayer().getBody().setFill(Color.RED);

            this.randomNumber2 = ran2.nextInt(4);
            if(randomNumber == randomNumber2){
                switch (randomNumber2) {
                    case 0, 1 -> randomNumber2 += 1;
                    case 2, 3 -> randomNumber2 -= 1;
                }
            }

            laser2.setX(laserPosition_x[randomNumber2]);
            laser2.setY(laserPosition_y[randomNumber2]);

            switch (this.randomNumber2) {
                case 0 -> laser2.getTransforms().add(new Rotate(-45, paserBody.getX(), paserBody.getY()));
                case 1 -> laser2.getTransforms().add(new Rotate(45, (paserBody.getX() + PASER_SIZE),
                        paserBody.getY()));
                case 2 -> laser2.getTransforms().add(new Rotate(45, paserBody.getX(),
                        (paserBody.getY() + PASER_SIZE)));
                case 3 -> laser2.getTransforms().add(new Rotate(-45, (paserBody.getX() + PASER_SIZE),
                        (paserBody.getY() + PASER_SIZE)));
            }
            pane.getChildren().addAll(laser2);
            this.shooting2 = true;
        }
        switch (this.randomNumber2) {
            case 0, 1 -> this.laser2.setY(this.laser2.getY() - speed);
            case 2, 3 -> this.laser2.setY(this.laser2.getY() + speed);
        }
        if(laser2.getY() < -(Math.sqrt(Math.pow(Cube.getCUBE_SIZE(), 2) + Math.pow(Cube.getCUBE_SIZE(), 2)) * 0.75) ||
                laser2.getY() > (Game.getGAME_SIZE() + (Math.sqrt(Math.pow(Cube.getCUBE_SIZE(), 2) + Math.pow(Cube.getCUBE_SIZE(), 2)) / 2))){

            switch (this.randomNumber2) {
                case 0 -> laserPosition2 = "NW";
                case 1 -> laserPosition2 = "NE";
                case 2 -> laserPosition2 = "SW";
                case 3 -> laserPosition2 = "SE";
            }


            if(laser2.getY() < (Game.getGAME_SIZE() / 2) - (Math.sqrt(Math.pow(Game.getGAME_SIZE() / 2, 2) + Math.pow(Game.getGAME_SIZE() / 2, 2))) ||
                    laser2.getY() > (Game.getGAME_SIZE() / 2) + Math.sqrt(Math.pow(Game.getGAME_SIZE() / 2, 2) + Math.pow(Game.getGAME_SIZE() / 2, 2))){
                this.shooting2 = false;

                switch (this.randomNumber2) {
                    case 0 -> laser2.getTransforms().add(new Rotate(45, paserBody.getX(), paserBody.getY()));
                    case 1 -> laser2.getTransforms().add(new Rotate(-45, (paserBody.getX() + PASER_SIZE),
                            paserBody.getY()));
                    case 2 -> laser2.getTransforms().add(new Rotate(-45, paserBody.getX(),
                            (paserBody.getY() + PASER_SIZE)));
                    case 3 -> laser2.getTransforms().add(new Rotate(45, (paserBody.getX() + PASER_SIZE),
                            (paserBody.getY() + PASER_SIZE)));
                }

                pane.getChildren().removeAll(laser2);
            }

            if(Main.getPlayer().getLoopPosition() < 5 && laserPosition2.equals(Main.getPlayer().getPosition())){
                pane.getChildren().removeAll(laser2);
                if(!hit) {
                    healthBar -= 10;
                    System.out.println(healthBar);
                    Main.getPlayer().getBody().setFill(Color.BLUE);
                }
                if(this.healthBar == 0) {
                    dead = true;
                }
                hit = true;

            }else if(Main.getPlayer().getLoopPosition() > 60 && laserPosition.equals(Main.getPlayer().getNextPosition())){
                pane.getChildren().removeAll(laser);
                if(!hit) {
                    healthBar -= 10;
                    System.out.println(healthBar);
                    Main.getPlayer().getBody().setFill(Color.BLUE);
                }
                if(this.healthBar == 0) {
                    dead = true;
                }
                hit = true;
            }

        }
    }

    public Rectangle getPaserBody() {
        return paserBody;
    }

    public Rectangle getLaser() {
        return laser;
    }

    public boolean getDead(){
        return dead;
    }
}
