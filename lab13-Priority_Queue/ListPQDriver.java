import java.util.LinkedList;
public class ListPQDriver {
    // --- 1. Entry ADT ---
    static class MyEntry<K extends Comparable<K>, V> implements Comparable<MyEntry<K, V>> {
        private K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }

        @Override
        public int compareTo(MyEntry<K, V> other) {
            return this.key.compareTo(other.key);
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    // --- 2. Common Interface ---
    interface PriorityQueue<K extends Comparable<K>, V> {
        void insert(K key, V value);
        MyEntry<K, V> removeMin();
        MyEntry<K, V> min();
        boolean isEmpty();
    }

    // --- 3. Implementation 1: Unsorted List (LinkedList) ---
// Insert: O(1) | RemoveMin: O(n)
    static class UnsortedListPQ<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
        private LinkedList<MyEntry<K, V>> list = new LinkedList<>();

        public boolean isEmpty() { return list.isEmpty(); }

        public void insert(K key, V value) {
            list.addLast(new MyEntry<>(key, value)); // O(1)
        }

        public MyEntry<K, V> min() {
            if (isEmpty()) return null;
            MyEntry<K, V> minEntry = list.getFirst();
            for (MyEntry<K, V> entry : list) {
                if (entry.compareTo(minEntry) < 0) {
                    minEntry = entry;
                }
            }
            return minEntry;
        }

        public MyEntry<K, V> removeMin() {
            if (isEmpty()) return null;
            // TODO: Find the minimum Entry by key
            MyEntry<K, V> minEntry = min();

            // TODO: Remove the min Entry from the list (This is also O(n) for LinkedList)
            list.remove(minEntry);
            return minEntry;
        }
    }

    // --- 4. Implementation 2: Sorted List (LinkedList) ---
// Insert: O(n) | RemoveMin: O(1)
    static class SortedListPQ<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
        private LinkedList<MyEntry<K, V>> list = new LinkedList<>();

        public boolean isEmpty() { return list.isEmpty(); }

        public void insert(K key, V value) {
            MyEntry<K, V> newEntry = new MyEntry<>(key, value);
            // TODO: Find the correct insertion point to maintain sorted order by key (O(n))
            // Hint: Iterate and use list.add(index, newEntry) when list.get(index) > newEntry
            int i = 0;
            while(i<list.size() && list.get(i).compareTo(newEntry)<=0){
                i++;
            }
            //add newEntry to list
            list.add(i,newEntry);
        }

        public MyEntry<K, V> min() {
            return isEmpty() ? null : list.getFirst(); // O(1)
        }

        public MyEntry<K, V> removeMin() {
            return isEmpty() ? null : list.removeFirst(); // O(1)
        }
    }
    public static void main(String[] args) {
        System.out.println("--- UnsortedListPQ (O(n) removal) ---");
        PriorityQueue<Integer, String> pq1 = new UnsortedListPQ<>();
        pq1.insert(5, "Task E"); pq1.insert(1, "Task A"); pq1.insert(10, "Task G"); pq1.insert(3, "Task C");
        while(!pq1.isEmpty()) System.out.println("Removed: " + pq1.removeMin());

        System.out.println("\n--- SortedListPQ (O(n) insertion) ---");
        PriorityQueue<Integer, String> pq2 = new SortedListPQ<>();
        pq2.insert(5, "Task E"); pq2.insert(1, "Task A"); pq2.insert(10, "Task G"); pq2.insert(3, "Task C");
        while(!pq2.isEmpty()) System.out.println("Removed: " + pq2.removeMin());
    }
}