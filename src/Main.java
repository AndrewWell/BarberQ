public class Main {
    public static void main(String[] args) {
        HairdrasserQueue hairQ = new HairdrasserQueue();
        for (int i = 0; i < 15; i++) {
            if (hairQ.equeue("Client " + 1, Math.random() < 0.5))
                System.out.println("Client will be accepted today");
            else System.out.println(String.format("There are %s minutes left until the end of the working shift," +
                            " %s minutes are needed for your observation, come back tomorrow.",
                    480 - hairQ.getPassedTime(), hairQ.getLeadTime()));
        }
        hairQ.getInfoAboutClient();
    }
}