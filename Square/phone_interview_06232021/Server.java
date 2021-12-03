package phone_interview_06232021;

public class Server {
    private final int x;
    private final int y;
    private final int max;

    Server( int max, int x, int y) {
        this.max = max;
        this.x = x;
        this.y = y;
    }

    public boolean check(int x, int y) {
        System.out.println("Checking " + x + ", " + y);
        return this.x == x && this.y == y;
    }

    public int max() {
        return max;
    }
}
