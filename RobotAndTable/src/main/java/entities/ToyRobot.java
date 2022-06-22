package entities;

import entities.enums.Direction;

public class ToyRobot {

    private Direction direction;
    private Integer xPosition;
    private Integer yPosition;
    private boolean isOnTable = false;



    public ToyRobot(Integer xPosition, Integer yPosition, Direction direction ) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;
    }

    public ToyRobot() {

    }

    public boolean isOnTable() {
        return isOnTable;
    }

    public void setOnTable(boolean onTable) {
        isOnTable = onTable;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getXPosition() {
        return xPosition;
    }

    public void setXPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getYPosition() {
        return yPosition;
    }

    public void setYPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public void increaseYPosition() {
        yPosition++;
    }

    public void decreaseYPosition() {
        yPosition--;
    }

    public void increaseXPosition() {
        xPosition++;
    }

    public void decreaseXPosition() {
        xPosition--;
    }

    @Override
    public String toString() {
        return String.format("Output: %d,%d,%s", getXPosition(), getYPosition(), getDirection());
    }

}
