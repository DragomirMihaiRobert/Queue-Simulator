public class Client implements Comparable<Client> {
    private int arrivalTime;
    private int processingTime;
    private int finishTime;
    private int id;
    private static int idNumber = 0;

    public Client(int arrivalTime, int processingTime) {
        id = ++idNumber;
        this.arrivalTime = arrivalTime;
        this.finishTime = -1;
        this.processingTime = processingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int compareTo(Client otherClient) {
        return this.arrivalTime - otherClient.getArrivalTime();
    }

    public String toString() {
        return "(" + id + "," + arrivalTime + "," + processingTime + ")";
    }

    public boolean equals(Client c) {
        if (this.id == c.getId())
            return true;
        return false;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public static void setIdNumber(int value) {
        idNumber = value;
    }
}
