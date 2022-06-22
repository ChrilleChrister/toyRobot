import entities.TableTop;
import entities.ToyRobot;
import entities.enums.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.SimulationService;
import services.ToyRobotService;

import java.util.ArrayList;
import java.util.List;

public class RobotSimulatorTest {
    private List<String> inputs;
    private List<String> outputs;
    private ToyRobot toyRobot;
    private TableTop tableTop;
    private SimulationService simulationService;

    @BeforeEach
    public void setUp() {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.toyRobot = new ToyRobot();
        this.tableTop = new TableTop();
        this.simulationService = new SimulationService();
    }


    @Test
    public void ShouldPlaceRobotOnTableWithPositionZeroZero_When_PlaceCommandIsCalled() {


        ToyRobot expected = new ToyRobot(0,0, Direction.NORTH);

        inputs.add("PLACE 0,0,NORTH");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected.getDirection(), toyRobot.getDirection());
        Assertions.assertEquals(expected.getXPosition(), toyRobot.getXPosition());
        Assertions.assertEquals(expected.getYPosition(), toyRobot.getYPosition());

    }

    @Test
    public void ShouldReportOnRobot_When_ReportCommandIsCalled() {

        ToyRobotService toyRobotService = new ToyRobotService();

        outputs.add("Output: 0,1,NORTH");

        var expected = outputs.get(0);

        inputs.add("PLACE 0,0,NORTH");
        inputs.add("MOVE");
        inputs.add("REPORT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        var actual = toyRobotService.ReportOnRobot(toyRobot);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void ShouldPlaceRobotOnTable_When_PlaceCommandIsCalled() {


        ToyRobot expected = new ToyRobot(0,0, Direction.NORTH);
        expected.setOnTable(true);

        inputs.add("PLACE 0,0,NORTH");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected.isOnTable(), toyRobot.isOnTable());
    }


    @Test
    public void ShouldIgnoreCommands_When_PlaceCommandHasNotBeenCalled() {


        ToyRobot expected = new ToyRobot();

        inputs.add("LEFT");
        inputs.add("LEFT");
        inputs.add("RIGHT");
        inputs.add("RIGHT");
        inputs.add("MOVE");
        inputs.add("MOVE");
        inputs.add("REPORT");
        inputs.add("REPORT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(false, toyRobot.isOnTable());
        Assertions.assertEquals(expected.getDirection(), toyRobot.getDirection());
        Assertions.assertEquals(expected.getXPosition(), toyRobot.getXPosition());
        Assertions.assertEquals(expected.getYPosition(), toyRobot.getYPosition());
    }

    @Test
    public void ShouldMoveRobot_When_MoveCommandIsCalled() {


        ToyRobot expected = new ToyRobot(0,1, Direction.NORTH);
        ToyRobot expected2 = new ToyRobot(3,2, Direction.SOUTH);
        ToyRobot expected3 = new ToyRobot(4,3, Direction.EAST);
        ToyRobot expected4 = new ToyRobot(2,3, Direction.WEST);

        inputs.add("PLACE 0,0,NORTH");
        inputs.add("MOVE");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected.getDirection(), toyRobot.getDirection());
        Assertions.assertEquals(expected.getXPosition(), toyRobot.getXPosition());
        Assertions.assertEquals(expected.getYPosition(), toyRobot.getYPosition());

        inputs.add("PLACE 3,2,SOUTH");
        inputs.add("MOVE");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected2.getDirection(), toyRobot.getDirection());
        Assertions.assertEquals(expected2.getXPosition(), toyRobot.getXPosition());
        Assertions.assertEquals(expected2.getYPosition(), toyRobot.getYPosition());

        inputs.add("PLACE 3,3,EAST");
        inputs.add("MOVE");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected3.getDirection(), toyRobot.getDirection());
        Assertions.assertEquals(expected3.getXPosition(), toyRobot.getXPosition());
        Assertions.assertEquals(expected3.getYPosition(), toyRobot.getYPosition());

        inputs.add("PLACE 3,3,WEST");
        inputs.add("MOVE");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected4.getDirection(), toyRobot.getDirection());
        Assertions.assertEquals(expected4.getXPosition(), toyRobot.getXPosition());
        Assertions.assertEquals(expected4.getYPosition(), toyRobot.getYPosition());

    }

    @Test
    public void ShouldRotateRobotLeft_When_LeftCommandIsCalled() {


        ToyRobot expected = new ToyRobot(2,2, Direction.WEST);
        ToyRobot expected2 = new ToyRobot(2,2, Direction.SOUTH);
        ToyRobot expected3 = new ToyRobot(2,2, Direction.EAST);
        ToyRobot expected4 = new ToyRobot(2,2, Direction.NORTH);

        inputs.add("PLACE 2,2,NORTH");
        inputs.add("LEFT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected.getDirection(), toyRobot.getDirection());

        inputs.add("PLACE 2,2,WEST");
        inputs.add("LEFT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected2.getDirection(), toyRobot.getDirection());

        inputs.add("PLACE 2,2,SOUTH");
        inputs.add("LEFT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected3.getDirection(), toyRobot.getDirection());

        inputs.add("PLACE 2,2,EAST");
        inputs.add("LEFT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected4.getDirection(), toyRobot.getDirection());

    }

    @Test
    public void ShouldRotateRobotRight_When_RightCommandIsCalled() {


        ToyRobot expected = new ToyRobot(2,2, Direction.SOUTH);
        ToyRobot expected2 = new ToyRobot(2,2, Direction.WEST);
        ToyRobot expected3 = new ToyRobot(2,2, Direction.NORTH);
        ToyRobot expected4 = new ToyRobot(2,2, Direction.EAST);

        inputs.add("PLACE 2,2,EAST");
        inputs.add("RIGHT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected.getDirection(), toyRobot.getDirection());

        inputs.add("PLACE 2,2,SOUTH");
        inputs.add("RIGHT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected2.getDirection(), toyRobot.getDirection());

        inputs.add("PLACE 2,2,WEST");
        inputs.add("RIGHT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected3.getDirection(), toyRobot.getDirection());

        inputs.add("PLACE 2,2,NORTH");
        inputs.add("RIGHT");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected4.getDirection(), toyRobot.getDirection());

    }

    @Test
    public void ShouldIgnoreMoveCommand_When_RobotIsOnEdgeOfTable() {


        ToyRobot expected = new ToyRobot(0, 4, Direction.NORTH);


        inputs.add("PLACE 0,0,NORTH");
        inputs.add("MOVE");
        inputs.add("MOVE");
        inputs.add("MOVE");
        inputs.add("MOVE");
        inputs.add("MOVE");
        inputs.add("MOVE");
        inputs.add("MOVE");
        inputs.add("MOVE");

        inputs.forEach(s -> {
            simulationService.runCommand(s, tableTop, toyRobot);
        });

        Assertions.assertEquals(expected.getDirection(), toyRobot.getDirection());
        Assertions.assertEquals(expected.getXPosition(), toyRobot.getXPosition());
    }
}
