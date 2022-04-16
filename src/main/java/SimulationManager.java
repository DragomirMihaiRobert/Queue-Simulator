import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable {
    private int timeLimit;
    private int numberOfClients;
    private int numberOfServers;
    private int minProcessingTime;
    private int maxProcessingTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int noOfClientsDispatched;
    private float averageServiceTime;
    private LogView log;
    private Scheduler scheduler;
    private List<Client> generatedClients;
    private FileWriter myWriter;

    public SimulationManager(int numberOfClients, int numberOfServers, int timeLimit, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, FileWriter myWriter, LogView log) {
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.myWriter = myWriter;
        this.log = log;
        this.noOfClientsDispatched = 0;
        this.averageServiceTime = 0;

        generatedClients = new ArrayList<>();
        scheduler = new Scheduler(numberOfServers);
        generateNRandomClients();
    }

    private void generateNRandomClients() {
        for (int i = 0; i < numberOfClients; i++) {
            Random rand = new Random();

            int randProcessingTime = rand.nextInt((maxProcessingTime - minProcessingTime + 1));
            randProcessingTime += minProcessingTime;
            averageServiceTime += randProcessingTime;

            int randArrivalTime = rand.nextInt((maxArrivalTime - minArrivalTime + 1));
            randArrivalTime += minArrivalTime;

            Client randomClient = new Client(randArrivalTime, randProcessingTime);
            generatedClients.add(randomClient);
        }
        Collections.sort(generatedClients);

    }


    public void decrement(Scheduler scheduler, int time) {
        for (Server s : scheduler.getServers()) {
            BlockingQueue<Client> clients = s.getClients();
            if (!clients.isEmpty()) {
                int currentClientProcessingTime = clients.peek().getProcessingTime();
                currentClientProcessingTime--;
                clients.peek().setProcessingTime(currentClientProcessingTime);
                if (currentClientProcessingTime == 0) {
                    if (time + 1 == timeLimit) {
                        clients.peek().setProcessingTime(1);
                    }
                    if (time + 1 < timeLimit)
                        clients.peek().setFinishTime(time + 1);
                    clients.poll();
                } else if (time + 1 == timeLimit) {
                    clients.peek().setProcessingTime(clients.peek().getProcessingTime() + 1);
                }
            }
        }
    }

    public ArrayList<Client> createClientsCopy() {
        ArrayList<Client> clients = new ArrayList<>();
        for (Client c : generatedClients) {
            clients.add(c);
        }
        return clients;
    }

    public boolean isEmpty() {
        if (noOfClientsDispatched != numberOfClients)
            return false;
        for (Server s : scheduler.getServers())
            if (!s.getClients().isEmpty())
                return false;
        return true;
    }

    @Override
    public void run() {
        ArrayList<Client> clientsCopied = createClientsCopy();
        int currentTime = 0;
        boolean exit = false;
        while (currentTime < timeLimit && !exit) {
            while (!generatedClients.isEmpty()) {
                if (generatedClients.get(0).getArrivalTime() == currentTime) {
                    noOfClientsDispatched++;
                    generatedClients.get(0).setFinishTime(timeLimit - 1);
                    scheduler.dispatchClient(generatedClients.get(0));
                    generatedClients.remove(generatedClients.get(0));
                } else {
                    break;
                }
            }
            write(currentTime);
            if (isEmpty()) {
                exit = true;
            }
            decrement(scheduler, currentTime);
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        computeFinalLogEvents(clientsCopied);
        resetAndClose();
    }

    public void write(int currentTime) {
        String s = "Time " + currentTime + "\n" + this.show();
        log.setTextArea(log.getTextArea() + s);
        try {
            myWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void computeFinalLogEvents(ArrayList<Client> clients) {
        float totalWaitingPeriod = 0, averageWaitingPeriod;
        int maxNoOfClients = 0, peakHour = 0;
        for (Client c : clients) {
            if (c.getFinishTime() != -1)
                totalWaitingPeriod += c.getFinishTime() - c.getArrivalTime();
            else
                averageServiceTime -= c.getProcessingTime();
            if (c.getFinishTime() == timeLimit - 1 && c.getProcessingTime() != 0)
                averageServiceTime -= c.getProcessingTime();
        }
        if (noOfClientsDispatched != 0) {
            averageWaitingPeriod = totalWaitingPeriod / noOfClientsDispatched;
            averageServiceTime /= noOfClientsDispatched;
        } else {
            averageWaitingPeriod = 0;
            averageServiceTime = 0;
        }
        for (int ph = 0; ph < timeLimit; ph++) {
            int count = 0;
            for (Client c : clients)
                if (c.getFinishTime() > ph && c.getArrivalTime() <= ph)
                    count++;
            if (count > maxNoOfClients) {
                maxNoOfClients = count;
                peakHour = ph;
            }
        }
        writeFinalLogEvents(averageWaitingPeriod, peakHour, maxNoOfClients);
    }

    public void writeFinalLogEvents(float averageWaitingTime, int peakHour, int noOfClients) {
        String s = "Average waiting time:\n" + String.format("%.2f", averageWaitingTime);
        s += "\nAverage service time:\n" + String.format("%.2f", averageServiceTime);
        s += "\nPeak Hour:\n" + peakHour + "\nNumber of clients at peak hour:\n" + noOfClients;
        log.setTextArea(log.getTextArea() + s);
        try {
            myWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetAndClose() {
        for (Server s : scheduler.getServers()) {
            s.setExit(true);
        }

        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Server.setIdNumber(0);
        Client.setIdNumber(0);
    }

    public String show() {
        String s = "Waiting clients: ";
        if (generatedClients.isEmpty())
            s += "none";
        else {
            for (Client c : generatedClients) {
                s += c + "; ";
            }
        }
        s += "\n" + scheduler;
        return s;
    }

    public static void main(String[] args) {
        View principalFrame = new View();
        principalFrame.setVisible(true);
        LogView secondaryFrame = new LogView();
        Controller c = new Controller(principalFrame, secondaryFrame);
    }
}
