public class Clientele {
    private boolean haircutType;
    private String customerName;
    private int leadTime, clientID;

    /**
     * @param clientID     customer serial number
     * @param haircutType  type of work - if  true, then only hairstyle otherwise hairstyle and haircut
     * @param customerName customer name
     * @param leadTime     time spent on a given customer
     */
    public Clientele(int clientID, boolean haircutType, String customerName, int leadTime) {
        this.clientID = clientID;
        this.haircutType = haircutType;
        this.customerName = customerName;
        this.leadTime = leadTime;
    }

    public boolean isHaircutType() {
        return haircutType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public int getClientID() {
        return clientID;
    }
}

