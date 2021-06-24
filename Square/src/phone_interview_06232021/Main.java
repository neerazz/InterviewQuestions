package phone_interview_06232021;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(5, 2, 3);
        System.out.println(server.check(2,4));  // => false
        System.out.println(server.check(2,3));  // => true
        System.out.println(server.max());   //5

        Client client = new Client(server);
        client.search();
    }
}
