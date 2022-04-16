import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    private View frame;
    private LogView secondFrame;

    public Controller(View frame, LogView secondFrame) {
        this.frame = frame;
        this.secondFrame = secondFrame;
        this.frame.addStartButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n, q, timeLimit, minArrival, maxArrival, minService, maxService;
                secondFrame.setTextArea("");
                try {
                    n = Integer.parseInt(frame.getNumberOfClients());
                    q = Integer.parseInt(frame.getNumberOfServers());
                    timeLimit = Integer.parseInt(frame.getTimeLimit());
                    minArrival = Integer.parseInt(frame.getMinArrivalTime());
                    maxArrival = Integer.parseInt(frame.getMaxArrivalTime());
                    minService = Integer.parseInt(frame.getMinServiceTime());
                    maxService = Integer.parseInt(frame.getMaxServiceTime());
                    if (checkInput(n, q, timeLimit, minArrival, maxArrival, minService, maxService)) {
                        secondFrame.showLogView();
                        try {
                            FileWriter myWriter = new FileWriter("log.txt");
                            SimulationManager gen = new SimulationManager(n, q, timeLimit, minArrival, maxArrival, minService, maxService, myWriter, secondFrame);
                            Thread t = new Thread(gen);
                            t.start();
                        } catch (IOException exception) {
                            System.out.println("An error occurred.");
                            exception.printStackTrace();
                        }
                    }
                } catch (NumberFormatException exception) {
                    frame.showError("Introduced numbers has to be positive integers !");
                }
            }
        });

    }

    public boolean checkInput(int n, int q, int timeLimit, int minArrival, int maxArrival, int minService, int maxService) {
        if (n <= 0 || q <= 0 || timeLimit <= 0 || minArrival <= 0 || maxArrival <= 0 || minService <= 0 || maxService <= 0) {
            frame.showError("Introduced numbers has to be positive integers !");
            return false;
        }
        if (minArrival > maxArrival) {
            frame.showError("Minimum arrival time has to be smaller or equal to maximum arrival time !");
            return false;
        }
        if (minService > maxService) {
            frame.showError("Minimum service time has to be smaller or equal to maximum service time !");
            return false;
        }
        return true;
    }
}
