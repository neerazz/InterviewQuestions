package phone;

import java.util.List;


public class WeightStacking {

    public static void main(String[] args) {
        getMinimumMoves(List.of(1, 2, 3));
    }

    private static int getMinimumMoves(List<Integer> blocks) {
        int swapCount = 0;

        int minPos = 0, len = blocks.size();
        for (int i = 0; i < len; i++) {
            if (blocks.get(i) < blocks.get(minPos)) {
                minPos = i;
            }
        }

        while (minPos > 0) {
            swap(blocks, minPos - 1, minPos);
            minPos--;
            swapCount++;
        }

        int maxPos = 0;
        for (int i = 0; i < len; i++) {
            if (blocks.get(i) > blocks.get(maxPos)) {
                maxPos = i;
            }
        }

        while (maxPos < len - 1) {
            swap(blocks, maxPos, maxPos + 1);
            maxPos++;
            swapCount++;
        }

        return swapCount;
    }

    private static void swap(List<Integer> blocks, int i, int j) {
        int temp = blocks.get(i);
        blocks.set(i, blocks.get(j));
        blocks.set(j, temp);
    }
}
