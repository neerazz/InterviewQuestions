package phone;

import java.util.List;

public class MaxBearableLoss {

    public static int calculateMaxLossMonths(List<Integer> PnL) {
        int maxMonths = 0;
        int n = PnL.size();

        for (int i = 0; i < (1 << n); i++) { // Iterate over all 2^n combinations
            int cumulativePnL = 0;
            int negativeCount = 0;
            for (int j = 0; j < n; j++) {
                int currentPnL = (i & (1 << j)) == 0 ? PnL.get(j) : -PnL.get(j);
                cumulativePnL += currentPnL;
                if (cumulativePnL <= 0) { // Invalid scenario
                    break;
                }
                if (currentPnL < 0) {
                    negativeCount++;
                }
            }
            if (cumulativePnL > 0) { // Valid scenario
                maxMonths = Math.max(maxMonths, negativeCount);
            }
        }
        return maxMonths;
    }

    public static void main(String[] args) {
//        System.out.println(calculateMaxLossMonths(List.of(1, 1, 1, 1, 1)) + " = 2");
//        System.out.println(calculateMaxLossMonths(List.of(5, 1, 1, 1, 1, 1)) + " = 2");
//        System.out.println(calculateMaxLossMonths(List.of(5, 2, 3, 5, 2, 3)) + " = 3");
        System.out.println(calculateMaxLossMonths(new int[]{1, 1, 1, 1, 1}) + " = 2");
        System.out.println(calculateMaxLossMonths(new int[]{5, 1, 1, 1, 1, 1}) + " = 2");
        System.out.println(calculateMaxLossMonths(new int[]{5, 2, 3, 5, 2, 3}) + " = 2");
    }

    public static int calculateMaxLossMonths(int[] pnl) {
        if (pnl == null || pnl.length == 0) {
            // Edge case: PnL array is null or empty
            return 0;
        }

        int maxMonths = 0;
        int cumulativePnL = 0;
        int minCumulativePnL = 0;

        // Calculate initial cumulative PnL and find the minimum value
        for (int value : pnl) {
            cumulativePnL += value;
            minCumulativePnL = Math.min(minCumulativePnL, cumulativePnL);
        }

        // If the minimum cumulative PnL is non-negative, all months are already positive
        if (minCumulativePnL >= 0) {
            return pnl.length;
        }

        // Iterate through the array and flip the sign of PnL values to maximize positive months
        for (int i = 0; i < pnl.length; i++) {
            int flippedPnL = -pnl[i];
            minCumulativePnL += flippedPnL - pnl[i];

            // If flipping makes the cumulative PnL non-negative, increment the maxMonths
            if (minCumulativePnL >= 0) {
                maxMonths++;
                pnl[i] = flippedPnL; // Update the PnL array with the flipped value
            }
        }

        return maxMonths;
    }
}
