package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] a1 = new int[]{-100, 1, 5};
        int[] a2 = new int[]{-90, -2, 0};
        int[] a3 = new int[]{-20, -1, 4};
        System.out.println(Arrays.toString(merge(a1, a2, a3)));
    }

    public List<Integer> merge_2(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        int iL = a.length, jL = b.length, kL = c.length;
        LinkedHashSet<Integer> result = new LinkedHashSet<>();

        while (i < iL || j < jL || k < kL) {
            int aVal = i < iL ? a[i] : Integer.MAX_VALUE;
            int bVal = j < jL ? b[i] : Integer.MAX_VALUE;
            int cVal = k < kL ? c[i] : Integer.MAX_VALUE;

            if (aVal <= bVal && aVal <= cVal) {
                result.add(aVal);
                i++;
            } else if (bVal <= aVal || bVal <= cVal) {
                result.add(bVal);
                j++;
            } else if (cVal <= aVal || cVal <= bVal) {
                result.add(cVal);
                k++;
            }
        }


        return new ArrayList<>(result);
    }

    public static int[] merge(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        int iL = a.length, jL = b.length, kL = c.length;
        int[] result = new int[iL + jL + kL];
        int p = 0;

        while (i < iL || j < jL || k < kL) {
            int aVal = i < iL ? a[i] : Integer.MAX_VALUE;
            int bVal = j < jL ? b[i] : Integer.MAX_VALUE;
            int cVal = k < kL ? c[i] : Integer.MAX_VALUE;

            if (aVal <= bVal && aVal <= cVal) {
                result[p++] = aVal;
                i++;
            } else if (bVal <= aVal || bVal <= cVal) {
                result[p++] = bVal;
                j++;
            } else if (cVal <= aVal || cVal <= bVal) {
                result[p++] = cVal;
                k++;
            }
        }


        return result;
    }


}


