import java.util.*;

import static java.lang.Math.pow;

public class HairdrasserQueue {
    final static int workTime = 480,
            haircutTime = 35, rangeHaircutTime = 10,
            haircutAndShaveTime = 60, rangeHaircutAndShaveTime = 20;

    private int leadTime, clientID, passedTime;
    /**
     * @param lambda intensity of applications entering the system (average number of applications entering the system per unit of time)
     * @param n number of requests in the system (served requests + queue)
     * @param t_ob average service time
     */
    private int n;
    private double lambda, mu, t_ob;
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
        System.out.println(String.format("Total: %s clients were accepted during the shift", array.size()));
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

    /**
     * @return reduced flow rate
     */
    private double p() {
        return this.lambda / mu();
    }

    /**
     * @return the probability that the service channel is one
     */
    private double p_0() {
        double p = p();
        return (1 - p) / (1 - pow(p, this.n + 1));
    }

    /**
     * @return service intensity
     */
    private double mu() {
        return 1 / this.t_ob;
    }

    /**
     * @return the probability that an order is in the system
     */
    private double p_n() {
        if (p() != 1) return p_0() * pow(p(), this.n);
        return 1 / (this.n + 1);
    }
}


