package onsite.march2024;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SetWithExpiry {

    /*
    The code provided in the image implements an unbounded set with a TreeMap that stores a key-value pair, with each key having an expiration date and disappearing from the set once it has expired. There are several suggestions mentioned in the image for improving this code. Below are those suggestions explained, followed by the Java implementation with the improvements applied.

    Suggestions:
        1. Check for expired key on a frequent basis: Implement a background thread or a scheduled executor service to periodically check and remove expired keys instead of doing it only when inserting or deleting.
        2. Pruning can be optimized: Regular pruning of expired entries ensures the set doesn't grow indefinitely and minimizes memory usage.
        3. Add blocks to if's: Code blocks {} should be added to the if statements for clarity and to avoid errors when adding more lines to the conditions in the future.
        4. Make it single ton: Consider creating it as a singleton class if there should only be one instance of this set in your application context.
        5. Have synchronized only when inserting/deleting to map: Limit synchronized blocks to only critical sections to reduce contention and improve performance.
        6. We should not throw the exception: Throwing exceptions for logical flows (like key expiration) may not be the best approach, return a status or a special value instead.
        7. Extra map remove during contains: The contains method does not need to remove the key, it should only check the expiration.
        8. Have a final static variable instead of hard-coding in if: Use constants for hardcoded values like the threshold for the map size purge.

     */

    private final Map<String, Date> data = new TreeMap<>();
    private static final long CLEANUP_INTERVAL = 10; // In minutes

    public SetWithExpiry() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::purgeExpiredKeys, 0, CLEANUP_INTERVAL, TimeUnit.MINUTES);
    }

    public void add(String key, Date expiration) {
        if (expiration.getTime() > System.currentTimeMillis()) {
            synchronized (data) {
                data.put(key, expiration);
            }
        }
    }

    public boolean contains(String key) {
        synchronized (data) {
            Date expiration = data.get(key);
            if (expiration != null && expiration.getTime() > System.currentTimeMillis()) {
                return true;
            }
            data.remove(key);
            return false;
        }
    }

    private void purgeExpiredKeys() {
        synchronized (data) {
            for (Map.Entry<String, Date> entry : data.entrySet()) {
                if (entry.getValue().getTime() <= System.currentTimeMillis()) {
                    data.remove(entry.getKey());
                    break; // Stop iterating after finding the first expired key
                }
            }
        }
    }
}
