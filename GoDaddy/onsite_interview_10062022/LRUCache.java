package onsite_interview_10062022;

import java.util.*;

/**
 * Created on:  Oct 07, 2022
 * Ref:
 */

public class LRUCache {

    public static void main(String[] args){
        Cache<Integer, Integer> cache = new Cache<>(3);
        cache.put(1,1);
        System.out.println(cache.get(2)); // null
        System.out.println(cache.get(1)); // 1
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        System.out.println(cache.get(1)); // Null
        cache.put(4,1);
        System.out.println(cache.get(4)); // 1
        cache.put(4,4);
        // Cache = (2,3,4)
        cache.put(1,1);
        // Cache = (1,4,3)
        System.out.println(cache.get(1)); // 1
        System.out.println(cache.get(2)); // Null
    }

    static class Cache<K,V>{

        class Node{
            Node pre, next;
            K key;
            V val;

            public Node(K key, V val){
                this.key = key;
                this.val = val;
            }

            public Node(){
            }
        }
        int size;
        Node head, tail;
        Map<K,Node> map;

        private void addNode(Node node){
            node.pre = head;
            node.next = head.next;

            head.next.pre = node;
            head.next = node;
        }

        private void removeNode(Node node){
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
        }

        private void bringToStart(Node node){
            removeNode(node);
            addNode(node);
        }

        public Cache(int size){
            this.size = size;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
            map = new HashMap<>();
        }

        public synchronized V get(K key){
            Node node = map.get(key);
            if(node != null) {
                bringToStart(node);
                return node.val;
            }
            return null;
        }
        /*
            hd -> td

            hd -> new = Node(1,2) -> td
              <- td

            map = {1 -> n(1,2)}
        */
        public synchronized void put(K key, V value){
            if(map.containsKey(key)){
                Node node = map.get(key);
                node.val = value;
                bringToStart(node);
            }else{
                Node newNode = new Node(key, value);
                addNode(newNode);
                map.put(key, newNode);
                if(map.size() > size){
                    Node removeNode = tail.pre;
                    map.remove(removeNode.key);
                    removeNode(removeNode);
                }
            }
        }
    }

}
