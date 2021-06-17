package phone;

import java.util.*;
import java.io.*;

/**
 * Created on:  Jun 16, 2021
 * Ref:
 */

public class MultiValueHashMapImplementation {

    public static void main(String[] args) {

    }
}

class MultiValueHashMap {

    static class Node {
        String key;
        List<Integer> values = new ArrayList<>();
        Node next;

        public Node(String key, int val) {
            this.key = key;
            add(val);
        }

        void add(int val) {
            values.add(val);
        }
    }

    final int INITIAL_SIZE = 1000;
    Node[] nodes = new Node[INITIAL_SIZE];

    List<Integer> put(String key, int value) {
        int hash = hash(key);
        Node cur = nodes[hash];
        if (cur == null) {
            Node newNode = new Node(key, value);
            nodes[hash] = newNode;
            return newNode.values;
        } else {
            while (true) {
                if (cur.key.equals(key)) {
                    cur.add(value);
                    return cur.values;
                } else if (cur.next != null) {
                    cur = cur.next;
                } else {
                    Node newNode = new Node(key, value);
                    cur.next = newNode;
                    return newNode.values;
                }
            }
        }
    }

    List<Integer> get(String key) {
        int hash = hash(key);
        Node cur = nodes[hash];
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur.values;
            } else {
                cur = cur.next;
            }
        }
        return null;
    }

    private int hash(String key) {
        int hash = key.hashCode();
        return hash % nodes.length;
    }
}