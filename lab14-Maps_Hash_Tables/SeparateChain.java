import java.util.ArrayList;
import java.util.LinkedList;
public class SeparateChain {

    // Use the same Entry<K, V> and MapADT<K, V> as in Project 1
    // --- 1. Entry ADT ---
    static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }

    // --- 2. Common Map Interface ---
    interface MapADT<K, V> {
        V get(K key);

        V put(K key, V value);

        V remove(K key);

        int size();

        boolean isEmpty();
    }

    // --- 4. Implementation: Separate Chaining Hash Map ---
    // Time Complexity: get/put/remove are O(1) expected (Amortized)
    static class SeparateChainingMap<K, V> implements MapADT<K, V> {
        private ArrayList<LinkedList<Entry<K, V>>> table;
        private int size = 0;
        private final int N = 11; // Use a prime number for table capacity

        public SeparateChainingMap() {
            table = new ArrayList<>(N);
            for (int i = 0; i < N; i++) {
                table.add(new LinkedList<Entry<K, V>>());
            }
        }

        private int hash(K key) {
            return Math.abs(key.hashCode() % N);
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // TODO: Complete this method (O(1) expected time)
        public V get(K key) {
            // 1. Calculate the hash index (bucket).
            int h = hash(key);
            System.out.println("Key " + key.toString() + " hash index: " + h);
            LinkedList<Entry<K, V>> bucket = table.get(h);
            // 2. Search linearly within the bucket's linked list for the key.
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            // 3. If key is not found in the bucket, return null.
            return null;

        }


        public V put(K key, V value) {
            int h = hash(key);
            LinkedList<Entry<K, V>> bucket = table.get(h);

            // Check if key already exists in the bucket
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V val = entry.getValue();
                    entry.value = value;
                    return val;
                }
            }

            // Key is new: add to the front of the list
            Entry<K, V> newEntry = new Entry<>(key, value);
            bucket.addFirst(newEntry);
            return null;

        }

        public V remove(K key) {
            int h = hash(key);
            LinkedList<Entry<K, V>> bucket = table.get(h);

            Entry<K, V> toRemove = null;
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    toRemove = entry;
                    break;
                }
            }

            if (toRemove != null) {
                V oldValue = toRemove.getValue();
                bucket.remove(toRemove);
                size--;
                return oldValue;
            }
            return null;
        }
    }
    public static void main(String[] args){
        SeparateChainingMap<String, Integer> map = new SeparateChainingMap<>();
        System.out.println(map.put("apple", 10));
        System.out.println(map.put("cat", 20));
        System.out.println(map.put("blue", 30));
        System.out.println("Value is " + map.get("apple"));
        System.out.println("Value is " + map.get("cat"));
        System.out.println("Value is " + map.get("blue"));
        System.out.println(map.put("blue",40));
        System.out.println(map.remove("cat"));
        System.out.println("Value is " + map.get("cat"));
    }
}
