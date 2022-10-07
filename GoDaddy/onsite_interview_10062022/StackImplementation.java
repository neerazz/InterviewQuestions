package onsite_interview_10062022;

import java.util.*;

public class StackImplementation {
    static class Stack<T> {
        int size = 8;
        Object[] array = new Object[size];
        int index = -1;

        public synchronized void push(T data) {
            if (index == size - 1) {
                doubleArray();
            }
            array[++index] = data;
        }

        private void doubleArray() {
            size *= 2;
            Object[] newArray = new Object[size];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }

        public synchronized T pop() {
            if (index >= 0) {
                return (T) array[index--];
            }
            return null;
        }
    }

}