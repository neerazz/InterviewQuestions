package june2022;

import java.util.List;
import java.util.Stack;

/**
 * Created on:  Jun 23, 2022
 * Ref: https://leetcode.com/problems/sum-of-total-strength-of-wizards/discuss/2061985/JavaC%2B%2BPython-One-Pass-Solution
 */

public class CacheOptimalPower {

    public static void main(String[] args) {
        System.out.println(findTotalPower_old(List.of(1, 2, 3, 4)));
    }

    public static int findTotalPower(int[] A) {
        int res = 0, ac = 0, mod = (int)1e9 + 7, n = A.length;
        Stack<Integer> stack = new Stack<>();
        int[] acc = new int[n + 2];
        for (int r = 0; r <= n; ++r) {
            int a = r < n ? A[r] : 0;
            ac = (ac + a) % mod;
            acc[r + 1] = (ac + acc[r]) % mod;
            while (!stack.isEmpty() && A[stack.peek()] > a) {
                int i = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek();
                long lacc = l < 0 ? acc[i] : acc[i] - acc[l], racc = acc[r] - acc[i];
                int ln = i - l, rn = r - i;
                res = (int)(res + (racc * ln - lacc * rn) % mod * A[i] % mod) % mod;
            }
            stack.push(r);
        }
        return (res + mod) % mod;
    }

    private static int findTotalPower_old(List<Integer> power) {
        long result = 0;
        int mod = 1_000_000_007;
        int len = power.size();
        for (int i = 0; i < len; i++) {
            long min = Integer.MAX_VALUE;
            long sum = 0;
            for (int j = i; j < len; j++) {
                sum += power.get(j);
                min = Math.min(min, power.get(j));
                long currentPower = sum * min;
                result += currentPower % mod;
                result %= mod;
            }
        }
        return (int) (result % mod);
    }


}
