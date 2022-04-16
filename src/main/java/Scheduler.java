import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;


    public Scheduler(int maxNoServers) {
        this.maxNoServers = maxNoServers;
        servers = new ArrayList<>(maxNoServers);
        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server();
            servers.add(server);
        }
        for (Server s : servers) {
            Thread newThread = new Thread(s);
            newThread.start();
        }
    }

    public void dispatchClient(Client client) {
        Server currentServer = servers.get(0);
        int minWaitingPeriod = currentServer.getWaitingPeriod();

        for (Server s : servers) {
            if (s.getWaitingPeriod() < minWaitingPeriod) {
                minWaitingPeriod = s.getWaitingPeriod();
                currentServer = s;
            }
        }
        currentServer.addClient(client);
    }

    public List<Server> getServers() {
        return servers;
    }

    public String toString() {
        String s = "";
        for (Server server : servers) {
            s += server + "\n";
        }
        return s;
    }
}
