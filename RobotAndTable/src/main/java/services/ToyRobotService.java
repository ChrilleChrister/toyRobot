package services;

import entities.TableTop;
import entities.ToyRobot;
import entities.enums.Direction;

import java.util.logging.Level;
import java.util.logging.Logger;



public class ToyRobotService {
    private Logger logger = Logger.getLogger(ToyRobotService.class.getName());

    public void placeRobot(String command, TableTop tableTop, ToyRobot toyRobot) {
        String positionCoordinates = command.substring(command.indexOf(' ') + 1, command.indexOf(',') + 2);
        String direction = command.substring(command.indexOf(',') + 3);

        Integer startXposition = Integer.parseInt(String.valueOf(positionCoordinates.charAt(0)));
        Integer startYposition = Integer.parseInt(String.valueOf(positionCoordinates.charAt(2)));

        if (startXposition <= tableTop.getWidth() && startXposition >= tableTop.getMinWidth()
                && startYposition <= tableTop.getHeight() && startYposition >= tableTop.getMinHeight()) {
            toyRobot.setXPosition(startXposition);
            toyRobot.setYPosition(startYposition);
            toyRobot.setDirection(Direction.valueOf(direction));
            toyRobot.setOnTable(true);
        } else {
            logger.log(Level.INFO, "Place command ignored");
        }
    }

    public String ReportOnRobot(final ToyRobot toyRobot) {
        if (!toyRobot.isOnTable()) {
            logger.log(Level.INFO, "Report command is ignored");
            return "Robot missing";
        } else {
            return toyRobot.toString();
        }
    }

    public void turnRobotLeft(final ToyRobot toyRobot) {

        if (!toyRobot.isOnTable()) {
            logger.log(Level.INFO, "Left command is ignored");
        } else {
            switch (toyRobot.getDirection()) {
                case NORTH:
                    toyRobot.setDirection(Direction.WEST);
                    break;
                case SOUTH:
                    toyRobot.setDirection(Direction.EAST);
                    break;
                case EAST:
                    toyRobot.setDirection(Direction.NORTH);
                    break;
                case WEST:
                    toyRobot.setDirection(Direction.SOUTH);
                    break;
            }
            logger.log(Level.INFO, "Robot rotated " + toyRobot.getDirection());
        }
    }

    public void turnRobotRight(final ToyRobot toyRobot) {

        if (!toyRobot.isOnTable()) {
            logger.log(Level.INFO, "Right ccmmand is ignored");
        } else {
            switch (toyRobot.getDirection()) {
                case NORTH:
                    toyRobot.setDirection(Direction.EAST);
                    break;
                case SOUTH:
                    toyRobot.setDirection(Direction.WEST);
                    break;
                case EAST:
                    toyRobot.setDirection(Direction.SOUTH);
                    break;
                case WEST:
                    toyRobot.setDirection(Direction.NORTH);
                    break;
            }
            logger.log(Level.INFO, "Robot rotated " + toyRobot.getDirection());
        }
    }

    public void moveToyRobot(final TableTop tableTop, final ToyRobot toyRobot) {

        if (!toyRobot.isOnTable()) {
            logger.log(Level.INFO, "Move command is ignored");
        } else {
            switch (toyRobot.getDirection()) {
                case NORTH:
                    if (toyRobot.getYPosition() < tableTop.getHeight()) {
                        toyRobot.increaseYPosition();
                        logger.log(Level.INFO, "robot is moved");
                    } else {
                        logger.log(Level.INFO, "robot move command is ignored");
                    }
                    break;
                case SOUTH:
                    if (toyRobot.getYPosition() > tableTop.getHeight()) {
                        toyRobot.decreaseYPosition();
                    } else {
                        logger.log(Level.INFO, "robot move command is ignored");
                    }
                    break;
                case EAST:
                    if (toyRobot.getXPosition() < tableTop.getWidth()) {
                        toyRobot.increaseXPosition();
                    } else {
                        logger.log(Level.INFO, "robot move command is ignored");
                    }
                    break;
                case WEST:
                    if (toyRobot.getXPosition() > tableTop.getMinHeight()) {
                        toyRobot.decreaseXPosition();
                    } else {
                        logger.log(Level.INFO, "robot move command is ignored");
                    }
                    break;
            }
        }
    }


}
