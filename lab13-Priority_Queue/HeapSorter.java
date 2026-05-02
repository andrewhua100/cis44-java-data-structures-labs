import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
// --- HeapSort Driver ---
public class HeapSorter {
    // --- Heap Implementation ---
    static class HeapPriorityQueue<K extends Comparable<K>> {
        private ArrayList<K> heap = new ArrayList<>();

        // Helper methods
        protected int parent(int j) { return (j - 1) / 2; }
        protected int left(int j) { return 2 * j + 1; }
        protected int right(int j) { return 2 * j + 2; }

        public int size() { return heap.size(); }
        public boolean isEmpty() { return heap.isEmpty(); }

        private void swap(int i, int j) {
            K temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        public void insert(K key) {
            heap.add(key);
            upheap(heap.size() - 1);
        }

        public K removeMin() {
            if (isEmpty()) return null;
            K answer = heap.getFirst();
            // Move last element to root
            K last = heap.removeLast();
            if (!heap.isEmpty()) {
                heap.set(0, last);
                downheap(0);
            }
            return answer;
        }

        public K min() {
            return isEmpty() ? null : heap.getFirst();
        }

        private void upheap(int j) {
            // TODO: While j > 0 and parent > child, swap and move up
            int parent = (j-1)/2;
            while(j>0 && heap.get(j).compareTo(heap.get(parent))<=0){
                swap(j,parent);
                j = parent;
                parent = (j-1)/2;
            }
        }

        private void downheap(int j) {
            // TODO: While j has left child...
            while(true) {
                //left and right children
                int l = 2 * j + 1;
                int r = l+ 1;
                int smallest = j;

                // Find smaller child, swap if child < parent, move down

                if (l < size() && heap.get(l).compareTo(heap.get(smallest))<=0){
                    smallest = l;
                }
                if(r < size() && heap.get(r).compareTo(heap.get(smallest))<=0){
                    smallest = r;
                }
                //if smallest is still j
                if(smallest == j){
                    break;
                }
                swap(j,smallest);
                j = smallest;
            }
        }
    }
    public static void heapSort(Integer[] arr) {

        // Assuming we are sorting the array elements (Keys) themselves
        HeapPriorityQueue<Integer> pq = new HeapPriorityQueue<>();

        // Phase 1: Insert (Construction)
        for (Integer x : arr) {
            pq.insert(x);
        }

        // Phase 2: Remove (Sorting)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.removeMin();
        }
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[10];
        Random rand = new Random();
        for (int i=0; i<data.length; i++) data[i] = rand.nextInt(100);

        System.out.println("Before Sorting: " + Arrays.toString(data));
        heapSort(data);
        System.out.println("After Sorting:  " + Arrays.toString(data));
    }
}