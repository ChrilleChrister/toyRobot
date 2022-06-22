import entities.TableTop;
import entities.ToyRobot;
import services.SimulationService;

import java.io.IOException;

public class SimulatorMain {


    public static void main(String[] args) throws IOException {

        SimulationService simulationService = new SimulationService();
        TableTop tableTop = new TableTop();
        ToyRobot toyRobot = new ToyRobot();

        simulationService.runSimulation(tableTop, toyRobot);

    }
}
