import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.*;

public class Server implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private int id;
    private static int idNumber = 0;
    private boolean exit = false;

    public Server() {
        this.id = ++idNumber;
        this.clients = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addClient(Client newClient) {
        clients.add(newClient);
        waitingPeriod.set(newClient.getProcessingTime() + this.getWaitingPeriod());
    }

    @Override
    public void run() {
        while (!exit) {
            while (!clients.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                waitingPeriod.decrementAndGet();
            }
        }
    }

    public static void setIdNumber(int value) {
        idNumber = value;
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean getExit() {
        return this.exit;
    }


    public String toString() {
        String s = "Queue " + id + ": ";
        if (clients.isEmpty())
            return s + "closed";
        for (Client c : clients) {
            s += c + "; ";
        }
        return s;
    }
}
