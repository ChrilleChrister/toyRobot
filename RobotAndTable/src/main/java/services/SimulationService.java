package services;

import entities.TableTop;
import entities.ToyRobot;
import entities.enums.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SimulationService {
    private static final String PLACE_COMMAND_REGEX = "^(PLACE)\\s\\d+,\\d+,(NORTH|WEST|EAST|SOUTH)$";
    private List<String> inputList = new ArrayList<>();
    private List<String> outputList = new ArrayList<>();
    private Logger logger = Logger.getLogger(SimulationService.class.getName());
    private ToyRobotService toyRobotService = new ToyRobotService();


    public void runSimulation(final TableTop tableTop, final ToyRobot toyRobot) throws IOException {
        getInputsFromFileToList();

        inputList.forEach(s -> {
            runCommand(s, tableTop, toyRobot);
        });

        saveOutputsToFile(outputList);

    }

    public void runCommand(final String command, final TableTop tableTop, final ToyRobot toyRobot) {
        if (command.matches(PLACE_COMMAND_REGEX)) {
            toyRobotService.placeRobot(command, tableTop, toyRobot);
        } else if (command.equals(Command.LEFT.name()) && toyRobot.isOnTable()) {
            toyRobotService.turnRobotLeft(toyRobot);
        } else if (command.equals(Command.RIGHT.name()) && toyRobot.isOnTable()) {
            toyRobotService.turnRobotRight(toyRobot);
        } else if (command.equals(Command.REPORT.name()) && toyRobot.isOnTable()) {
            outputList.add(toyRobotService.ReportOnRobot(toyRobot));
        } else if (command.equals(Command.MOVE.name()) && toyRobot.isOnTable()) {
            toyRobotService.moveToyRobot(tableTop, toyRobot);
        }

    }

    public void getInputsFromFileToList() throws IOException {

        String filename = "src/main/java/robotTest.txt";
        List<String> list = Files.lines(Paths.get(filename)).collect(Collectors.toList());
        list.stream()
                .map(String::toString)
                .forEach(inputList::add);

    }

    public void saveOutputsToFile(final List<String> outputList) {
        String output = outputList.toString();
        try {
            FileWriter fileWriter = new FileWriter(new File("src/main/java/", "outputs.txt"));
            fileWriter.write(output);
            fileWriter.close();
            logger.log(Level.INFO, "outputs saved to file");
        } catch (IOException e) {
            logger.log(Level.INFO, "outputs couldn't be saved");
        }
    }

}
