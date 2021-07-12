import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created on:  Jul 12, 2021
 * Ref:
 * c = TTLCache()
 * c.set('foo', 'bar', 10)  # sets foo,bar key,value pair with 10 second ttl
 * c.get('foo')  # returns bar
 * # .. after 11 seconds
 * c.get('foo')  # returns None
 * <p>
 * c.set('foo','bar',10)
 * c.delete('foo')
 * c.get('foo') # returns none since foo was deleted
 * <p>
 * Assumptions:
 * 1. No limit
 * 2. In memory
 * 3. Key and vlaues are string, in number in sec
 * <p>
 * c.set('foo', 'bar', 10)
 * c.set('foo', 'bax', 5)
 * c.get('foo') -->bax
 * <p>
 * Object{
 * String key, value;
 * int end;
 * }
 */

public class TTLCacheImpl {

    static int addNumbers(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws InterruptedException {
        TTLCache TTLCache = new TTLCache();
        TTLCache.set("foo", "BAR", 10);
        System.out.println(TTLCache.get("foo") + " = BAR");
        System.out.println(TTLCache.get("foo1") + " = null");
        TTLCache.set("foo", "BAR2", 1);
        TTLCache.set("foo2", "BAR2", 5);
        TTLCache.set("foo3", "BAR3", 3);
        System.out.println(TTLCache.get("foo") + " = BAR2");
        System.out.println(TTLCache.get("foo3") + " = BAR3");
        System.out.println(TTLCache.get("foo2") + " = BAR2");
        TTLCache.delete("foo3");
        Thread.sleep(2000);
        System.out.println(TTLCache.get("foo") + " = null");
        System.out.println(TTLCache.get("foo3") + " = null");
        System.out.println(TTLCache.get("foo2") + " = BAR2");
    }
}

class TTLCache {
    Map<String, Node> map1 = new HashMap<>();
    Map<LocalDateTime, Map<String, Node>> map2 = new TreeMap<>();

    void set(String key, String value, int expiry) {
        if (map1.containsKey(key)) {
            Node node = map1.get(key);
            delete(node);
            node.value = value;
            node.expiry = LocalDateTime.now().plusSeconds(expiry);
            add(node);
        } else {
            Node node = new Node(key, value, LocalDateTime.now().plusSeconds(expiry));
            add(node);
        }
    }

    void clean() {
        for (LocalDateTime expiry : map2.keySet()) {
            if (expiry.isBefore(LocalDateTime.now())) {
//                map2.get(expiry).forEach(node -> delete(node));
            } else {
                break;
            }
        }
    }

    String get(String key) {
        Node node = map1.get(key);
        if (node == null) return null;
        if (node.expiry.isBefore(LocalDateTime.now())) {
            delete(node);
            return null;
        }
        return node.value;
    }

    void delete(String key) {
        if (map1.containsKey(key)) {
            delete(map1.get(key));
        }
    }

    private void add(Node node) {
        map1.put(node.key, node);
        map2.computeIfAbsent(node.expiry, val -> new HashMap<>()).put(node.key, node);
    }

    void delete(Node node) {
        map1.remove(node.key);
        Map<String, Node> entry = map2.get(node.expiry);
        if (entry != null) {
            entry.remove(node.key);
            if (entry.isEmpty()) map2.remove(node.expiry);
        }
    }

    static class Node {
        String key, value;
        LocalDateTime expiry;

        Node(String key, String val, LocalDateTime expiry) {
            this.key = key;
            this.value = val;
            this.expiry = expiry;
        }
    }
}
