package phone_interview_06232021;

public class Client {
    private Server server;

    Client(Server server) {
        this.server = server;
    }

    String search() {
        int squareSize = server.max();
        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                if (server.check(i,j)) {
                    return "Found at " + i + ", " + j;
                }
            }
        }
        return "Not Found";
    }
}
