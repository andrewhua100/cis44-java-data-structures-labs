import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class AdvancedSortDriver {
    public static class AdvancedSorters {

        // --- MergeSort ---
        public static <K> void mergeSort(K[] S, Comparator<K> comp) {
            int n = S.length;
            if (n < 2) return; // Base case

            // TODO: Divide
            int mid = n / 2;
            K[] S1 = Arrays.copyOfRange(S, 0, mid);
            K[] S2 = Arrays.copyOfRange(S, mid, n);

            // TODO: Conquer (recursive calls)
            mergeSort(S1, comp);
            mergeSort(S2, comp);

            // TODO: Combine
            merge(S, S1, S2, comp);
        }

        private static <K> void merge(K[] S, K[] S1, K[] S2, Comparator<K> comp) {
            int i = 0, j = 0, index = 0;
            // TODO: Implement the merge logic from the lecture
            while (i < S1.length && j < S2.length){
                if(comp.compare(S1[i], S2[j])<0){
                    S[index] = S1[i];
                    i++;
                    index++;
                }
                else{
                    S[index] = S2[j];
                    j++;
                    index++;
                }
            }
            for(int a = i;a<S1.length;a++){
                S[index] = S1[a];
                index++;
            }
            for(int b = j;b<S2.length;b++){
                S[index] = S2[b];
                index++;
            }
            // ...
            // TODO: Copy remaining elements of S1 or S2
            // ...
        }

        // --- QuickSort ---
        public static <K> void quickSort(K[] S, Comparator<K> comp) {
            quickSort(S, comp, 0, S.length - 1);
        }

        private static <K> void quickSort(K[] S, Comparator<K> comp, int a, int b) {
            if (a >= b) return; // Base case

            // TODO: Divide
            int pivotIndex = partition(S, comp, a, b);

            // TODO: Conquer (recursive calls)
            quickSort(S, comp, a, pivotIndex - 1);
            quickSort(S, comp, pivotIndex + 1, b);
        }

        private static <K> int partition(K[] S, Comparator<K> comp, int a, int b) {
            // TODO: Implement partition logic (e.g., Hoare's from lecture)
            // 1. Choose a pivot (e.g., S[a])
            Random random = new Random();
            int pivoti = a + random.nextInt(b - a + 1);
            K pivot = S[pivoti];
            //place pivot at end of list
            K temp = S[b];
            S[b] = pivot;
            S[pivoti] = temp;

            // 2. Set up 'left' and 'right' pointers
            int left = a;
            int right = b - 1;


            // 3. Loop and swap elements
            while (left <= right) {
                //compare pivot and S[left]
                while (left <= right && comp.compare(S[left], pivot) < 0) {
                    left++;
                }
                //compare pivot and S[right]
                while (left <= right && comp.compare(S[right], pivot) > 0) {
                    right--;
                }
                //swap left and right
                if (left <= right) {
                    temp = S[left];
                    S[left] = S[right];
                    S[right] = temp;
                    left++;
                    right--;
                }
            }
            //put pivot in the correct place
            temp = S[left];
            S[left] = S[b];
            S[b] = temp;
            // 4. Return the final index of the pivot
            return left; // placeholder
        }
    }
    public static void main(String[] args) {
        Comparator<Integer> comp = Comparator.naturalOrder();
        int N = 20;
        Random rand = new Random();

        Integer[] arr1 = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = rand.nextInt(100);
        }
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        System.out.println("--- Test: Random Array (N=20) ---");
        System.out.println("Original: " + Arrays.toString(arr1));

        AdvancedSorters.mergeSort(arr1, comp);
        System.out.println("Merge Sort: " + Arrays.toString(arr1));

        System.out.println("Original: " + Arrays.toString(arr2));
        AdvancedSorters.quickSort(arr2, comp);
        System.out.println("Quick Sort: " + Arrays.toString(arr2));
    }
}