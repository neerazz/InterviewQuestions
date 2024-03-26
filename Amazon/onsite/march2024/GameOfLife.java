package onsite.march2024;

public class GameOfLife {

    /*

Given a world, produce the new state of the world.

Write code which produces the next evolution, based on the following rules:

- Any live cell with fewer than two live neighbors dies, as if by underpopulation.
- Any live cell with two or three live neighbors lives on to the next generation.
- Any live cell with more than three live neighbors dies, as if by overpopulation.
- Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Time: O(m*n)
Space:  O(m*n)
*/

    // live =1, dead = 0
    public int[][] gameOfLife(int[][] board) {


        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        int rows = board.length, cols = board[0].length;

        int[][] result = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int livN = 0;

                for (int k = 0; k < 8; k++) {
                    int x = row + dx[k], y = col + dy[k];
                    if (x >= 0 && x < rows && y >= 0 && y < cols) {
                        livN += board[x][y];
                    }
                }

                if (board[row][col] == 1) {
                    if (livN < 2 || livN > 3) {
                        result[row][col] = 0;
                    }
                    result[row][col] = 1;
                } else if (board[row][col] == 0) {
                    if (livN == 3) {
                        // dead to alive
                        result[row][col] = 1;
                    }
                }
            }

        }
        return result;
    }
}
