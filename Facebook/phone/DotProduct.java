package phone;

import java.util.ArrayList;
import java.util.List;

public class DotProduct {

    public int[] encode(int[] array) {
        int n = array.length;
        int count = 1;
        List<Integer> encoded_array = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (array[i] == array[i + 1]) {
                count++;
            } else {
                encoded_array.add(count);
                encoded_array.add(array[i]);
                count = 1;
            }
        }
        encoded_array.add(count);
        encoded_array.add(array[n - 1]);
        return encoded_array.stream().mapToInt(i -> i).toArray();
    }

    public int[] decode(int[] encoded_array) {
        int n = encoded_array.length;
        int[] decoded_array = new int[n / 2];
        int idx = 0;
        for (int i = 0; i < n; i += 2) {
            for (int j = 0; j < encoded_array[i]; j++) {
                decoded_array[idx++] = encoded_array[i + 1];
            }
        }
        return decoded_array;
    }

    public int findDotProduct(int[] a, int[] b) {
        a = encode(a);
        b = encode(b);
        int na = a.length, nb = b.length, adotb = 0;
        int i = 0, j = 0;
        while (i < na && j < nb) {
            if (a[i + 1] == b[j + 1]) {
                adotb += a[i] * a[i + 1] * b[j + 1];
                i += 2;
                j += 2;
            } else if (a[i + 1] < b[j + 1]) {
                i += 2;
            } else {
                j += 2;
            }
        }
        return adotb;
    }
}
