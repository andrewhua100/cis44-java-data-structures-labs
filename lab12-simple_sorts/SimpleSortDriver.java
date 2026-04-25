import java.util.Arrays;
import java.util.Comparator;

public class SimpleSortDriver {
    public static class SimpleSorters {

        /**
         * Sorts an array using the optimized Bubble Sort algorithm.
         */
        public static <K> void bubbleSort(K[] S, Comparator<K> comp) {
            int n = S.length;
            for (int i = 0; i < n - 1; i++) {
                boolean swapped = false;
                // TODO: Implement the inner loop j
                // ...
                for(int j = 0;j<n-i-1;j++) {
                    if (comp.compare(S[j], S[j + 1]) > 0) {
                        K temp = S[j];
                        S[j] = S[j+1];
                        S[j+1] = temp;
                        // TODO: Swap S[j] and S[j+1]
                        swapped = true;
                    }
                }
                // ... end inner loop ...

                // TODO: Add check for early termination
                if (!swapped) {
                    break;
                }
            }
        }

        /**
         * Sorts an array using the Insertion Sort algorithm.
         */
        public static <K> void insertionSort(K[] S, Comparator<K> comp) {
            int n = S.length;
            for (int i = 1; i < n; i++) {
                K cur = S[i];
                int j = i - 1;

                // TODO: Implement the while loop to shift elements
                while (j >= 0 && comp.compare(S[j], cur) > 0) {
                    S[j + 1] = S[j];
                    j--;
                }

                // TODO: Insert 'cur' into its correct position
                S[j + 1] = cur;
            }
        }
    }
    public static void main(String[] args) {
        // Use a standard Integer comparator
        Comparator<Integer> comp = Comparator.naturalOrder();

        // Test 1: Unsorted Array
        Integer[] arr1 = {5, 1, 9, 3, 7, 6};
        Integer[] arr1_copy = Arrays.copyOf(arr1, arr1.length);

        //Test 2: Sorted Array
        Integer[] arr2 = {1, 3, 5, 6, 7, 9};
        Integer[] arr2_copy = Arrays.copyOf(arr2, arr2.length);

        //Test 3: Reverse Sorted Array
        Integer[] arr3 = {9, 7, 6, 5, 3, 1};
        Integer[] arr3_copy = Arrays.copyOf(arr3, arr3.length);

        System.out.println("--- Test 1: Unsorted Array ---");
        System.out.println("Original: " + Arrays.toString(arr1));
        SimpleSorters.bubbleSort(arr1, comp);
        System.out.println("Bubble Sort: " + Arrays.toString(arr1));

        System.out.println("Original: " + Arrays.toString(arr1_copy));
        SimpleSorters.insertionSort(arr1_copy, comp);
        System.out.println("Insertion Sort: " + Arrays.toString(arr1_copy));

        System.out.println("--- Test 2: Sorted Array ---");
        System.out.println("Original: " + Arrays.toString(arr2));
        SimpleSorters.bubbleSort(arr2, comp);
        System.out.println("Bubble Sort: " + Arrays.toString(arr2));

        System.out.println("Original: " + Arrays.toString(arr2_copy));
        SimpleSorters.insertionSort(arr2_copy, comp);
        System.out.println("Insertion Sort: " + Arrays.toString(arr2_copy));

        System.out.println("--- Test 3: Reverse Sorted Array ---");
        System.out.println("Original: " + Arrays.toString(arr3));
        SimpleSorters.bubbleSort(arr3, comp);
        System.out.println("Bubble Sort: " + Arrays.toString(arr3));

        System.out.println("Original: " + Arrays.toString(arr3_copy));
        SimpleSorters.insertionSort(arr3_copy, comp);
        System.out.println("Insertion Sort: " + Arrays.toString(arr3_copy));


        // TODO: Add Test 2 (Reverse-Sorted) and Test 3 (Already-Sorted)
    }
}