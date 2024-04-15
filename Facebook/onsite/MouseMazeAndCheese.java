package onsite;

import java.util.HashSet;
import java.util.Set;

public class MouseMazeAndCheese {

// Move the mouse from the initial location to the cheese

// +---+---+
// |   | CM |
// +   +   +
// | m     |
// +   +   +
// |   |   |
// +---+---+

// hasCheese(): Returns True if the mouse is at the goal state, else False.
// move(direction): Moves the mouse one step in the specified direction. Returns True if the move was valid, else False.


    // Mouse m
    enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
        String name;
        int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
        }

    static class Mouse {


        public boolean hasCheese() {
            return false;
        }

        public boolean move(String directionName) {
            return false;
        }
    }

    public boolean findCheese(Mouse mouse) {
        int row = 0, col = 0;
        Set<String> visited = new HashSet<>();
        return dfs(mouse, row, col, visited);
    }

    private boolean dfs(Mouse mouse, int row, int col, Set<String> visited) {
        String hash = hash(row, col);
        if (visited.contains(hash)) return false;
        visited.add(hash);
        if (mouse.hasCheese()) return true;

        for (var enumsDirs : Direction.values()) {
            String directionName = enumsDirs.name;
            if (mouse.move(directionName)) {
                boolean result = dfs(mouse, row + enumsDirs.x, col + enumsDirs.y, visited);
                if (result) {
                    return result;
                }
            }
        }

        return false;

    }

    private String hash(int row, int col) {
        return "(" + row + "," + col + ")";
    }

}
