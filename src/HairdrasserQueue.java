import java.util.*;

public class HairdrasserQueue {
    final static int workTime = 480,
            haircutTime = 35, rangeHaircutTime = 10,
            haircutAndShaveTime = 60, rangeHaircutAndShaveTime = 20;

    private int leadTime, clientID, passedTime;
    private LinkedList<Clientele> array;


    HairdrasserQueue() {
        array = new LinkedList<>();
        passedTime = 0;
        clientID = 0;
    }

    /**
     * @param customerName customer name
     * @param haircutType  if the parameter accepts the truth, then the client should only have a haircut; otherwise, a set of measures: haircut and shave
     * @return boolean value, if the hairdresser manages to receive the client during the shift, print true otherwise false
     */
    public boolean equeue(String customerName, boolean haircutType) {
        leadTime = clarificationTimeWork(haircutType);
        if (passedTime + leadTime <= workTime) {
            array.add(new Clientele(clientID, haircutType, customerName, leadTime));
            clientID++;
            passedTime += leadTime;
            return true;
        }
        return false;
    }

    public void dequeue() {
        array.pop();
    }

    public int getPassedTime() {
        return passedTime;
    }

    public int getLeadTime() {
        return leadTime;
    }

    /**
     * will issue a list with a description of the work of all accepted clients for the shift
     */
    public void getInfoAboutClient() {
        for (Clientele client : array)
            System.out.println(String.format("ID = %s, customer name = %s, only haircut = %s, lead time = %s min",
                    client.getClientID(), client.getCustomerName(), client.isHaircutType(), client.getLeadTime()));
    }

    /**
     * @param haircutType if true option is selected, only shearing is expected, otherwise shaving and shearing complex
     * @return time spent on one order
     */
    private int clarificationTimeWork(boolean haircutType) {
        if (haircutType) return getLeadTime(haircutTime - rangeHaircutTime, haircutTime + rangeHaircutTime);
        return getLeadTime(haircutAndShaveTime - rangeHaircutAndShaveTime, haircutAndShaveTime + rangeHaircutAndShaveTime);
    }

    /**
     * @param min minimum lead time
     * @param max maximum lead time
     * @return the hairdresser's work time in minutes for a specific situation
     */
    private int getLeadTime(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
