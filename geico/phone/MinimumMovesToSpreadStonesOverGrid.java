package phone;

import java.util.*;

public class MinimumMovesToSpreadStonesOverGrid {

    public static void main(String[] args) {
        // Test case 1
        int[][] A1 = {{1, 0, 1}, {1, 3, 0}, {2, 0, 1}};
        System.out.println(solution(A1) + " should be 3.");

        // Test case 2
        int[][] A2 = {{2, 0, 2}, {1, 0, 0}, {2, 1, 1}};
        System.out.println(solution(A2) + " should be 4.");

        // Test case 3
        int[][] A3 = {{0, 6, 0}, {2, 0, 0}, {0, 1, 0}};
        System.out.println(solution(A3) + " should be 9.");
    }

    public static int solution(int[][] A) {
        List<int[]> empty = new ArrayList<>();
        List<int[]> overFilled = new ArrayList<>();
//        row, col, flag ( 1 available , 0 taken)

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (A[r][c] == 0) {
                    empty.add(new int[]{r, c});
                } else if (A[r][c] > 1) {
                    overFilled.add(new int[]{r, c, 1});
                }
            }
        }

        return helper(0, empty, overFilled, A);
    }

    private static int helper(int pos, List<int[]> empty, List<int[]> overFilled, int[][] grid) {
        if (pos == empty.size())
            return 0;

        int min = Integer.MAX_VALUE;

        for (int[] current : overFilled) {
//            If the current one is already taken, then ignore.
            if (current[2] == 0) continue;
            int[] fillPosition = empty.get(pos);
            int row = current[0], col = current[1];
            grid[row][col]--;
            if (grid[row][col] == 1) {
//                Make the overfilled flag, as taken.
                current[2] = 0;
            }
            int next = helper(pos + 1, empty, overFilled, grid);
            int dis = getDistance(fillPosition, current);
            min = Math.min(min, next + dis);
            grid[row][col]++;
//                Make the overfilled flag, as available, for a better solution.
            current[2] = 1;
        }
        return min;
    }

    private static int getDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
