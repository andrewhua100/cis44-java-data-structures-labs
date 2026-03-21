import java.util.Arrays;
import java.util.Random;

public class SortingTester {
    public static class SortingAlgorithms {

        /**
         * Implements the Selection Sort algorithm.
         * Theoretical Complexity: O(n^2)
         */
        public static void selectionSort(int[] arr){
            //variable to store index of minimum value and variable for moving minimum value to the front
            int minindex;
            int temp;
            //first loop to iterate the array
            for(int i = 0;i<arr.length-1;i++){
                //set minimum index to the value at the current index first
                minindex = i;
                //second loop that iterates from i+1 to the end of list to find minimum value
                for(int j = i+1;j<arr.length;j++){
                    if(arr[minindex]>arr[j]){
                        minindex = j;
                    }
                }
                //switch places of minimum value and the value at i
                temp = arr[i];
                arr[i] = arr[minindex];
                arr[minindex] = temp;
            }
        }

        /**
         * Implements the Insertion Sort algorithm.
         * Theoretical Complexity: O(n^2) / Best-Case: O(n)
         */
        public static void insertionSort(int[] arr) {
            // TODO: Implement the Insertion Sort algorithm.
            for(int i = 1;i<arr.length;i++){
                //set key as the value at the current index
                int key = arr[i];
                //set index j at i-1
                int j = i-1;
                //if index is in bounds and the key is smaller than the value before it, switch them, and decrement j
                while(j>=0 && arr[j]>key){
                    int temp = arr[j];
                    arr[j] = key;
                    arr[j+1] = temp;
                    j--;
                }
            }
        }

        /**
         * Implements the Merge Sort algorithm. Public-facing method.
         * Theoretical Complexity: O(n log n)
         */
        public static void mergeSort(int[] arr) {
            if (arr == null || arr.length <= 1) {
                return; // Already sorted
            }
            int[] temp = new int[arr.length];
            mergeSortRecursive(arr, temp, 0, arr.length - 1);
        }

        private static void mergeSortRecursive(int[] arr, int[] temp, int left, int right) {
            // TODO: Implement the recursive logic for Merge Sort.
            if(left<right){
                //find mid index
                int mid = (left+right)/2;
                //recursive logic on both halves
                mergeSortRecursive(arr, temp, left, mid);
                mergeSortRecursive(arr, temp, mid+1, right);
                //merge them back together
                merge(arr, temp, left, mid, right);
            }
        }

        private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
            // TODO: Implement the merge logic for Merge Sort.
            int l1 = left; //left index
            int r1 = mid+1; //right index
            int tempindex = left; //index for the temporary array
            while(l1<=mid && r1<=right){ //if index is in bounds for each half
                if(arr[l1]<arr[r1]){ //if value on the right side is bigger than value on the left side, add the left value in the temporary array
                    temp[tempindex] = arr[l1];
                    tempindex++;
                    l1++;
                }
                else{ //otherwise add right value;
                    temp[tempindex] = arr[r1];
                    tempindex++;
                    r1++;
                }
            }
            //add all remaining elements in the array
            for(int i = l1;i<=mid;i++){
                temp[tempindex] = arr[i];
                tempindex++;
            }
            for(int j = r1;j<=right;j++){
                temp[tempindex] = arr[j];
                tempindex++;
            }
            //copy the temporary array into arr
            if (right + 1 - left >= 0) System.arraycopy(temp, left, arr, left, right + 1 - left);
        }
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 25000, 50000, 100000};

        System.out.println("--- The Sorting Race ---");

        for (int n : sizes) {
            System.out.println("\n--- Testing for array size n = " + n + " ---");

            //arrays for the worst, best, and average case
            int[] worstCase = generateReverseSortedArray(n);
            int[] bestCase = generateSortedArray(n);
            int[] AverageCase = generateRandomArray(n);

            //Worst Case
            System.out.println("Timing worst case");
            runAndTimAllSorts(worstCase);

            //Best Case
            System.out.println("Timing best case");
            runAndTimAllSorts(bestCase);

            //Average Case
            System.out.println("Timing average case");
            runAndTimAllSorts(AverageCase);


            // TODO: Call your test methods for Average, Best, and Worst cases.
        }
    }

    // TODO: Implement the runAndTimAllSorts helper method.
    public static void runAndTimAllSorts(int[] arr){
        //create clones of arrays to perform sorting algorithms on
        int[] selectionarray = arr.clone();
        int[] insertionarray = arr.clone();
        int[] mergearray = arr.clone();

        //calculate time for selection sort, and convert nanoseconds to milliseconds
        long start = System.nanoTime();
        SortingAlgorithms.selectionSort(selectionarray);
        long end = System.nanoTime();
        System.out.println("Time for selection sort: " + (end-start)/1000000.0 + "ms");

        //calculate time for insertion sort, and convert nanoseconds to milliseconds
        start = System.nanoTime();
        SortingAlgorithms.insertionSort(insertionarray);
        end = System.nanoTime();
        System.out.println("Time for insertion sort: " + (end-start)/1000000.0 + "ms");

        //calculate time for merge sort, and convert nanoseconds to milliseconds
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(mergearray);
        end = System.nanoTime();
        System.out.println("Time for merge sort: " + (end-start)/1000000.0 + "ms");

    }

    public static int[] generateRandomArray(int size) {
        // generates an array with random numbers
        Random random = new Random();
        int[] arr = new int[size];
        for(int i = 0;i<size;i++){
            arr[i] = random.nextInt(size) + 1;
        }
        return arr;
    }

    public static int[] generateSortedArray(int size) {
        // generates a sorted array from 1 to size
        int[] arr = new int[size];
        for(int i = 0;i<size;i++){
            arr[i] = i+1;
        }
        return arr;
    }

    public static int[] generateReverseSortedArray(int size) {
        // generates a reverse sorted array from size to 1
        int[] arr = new int[size];
        int temp = size;
        for(int i = 0;i<size;i++){
            arr[i] = temp;
            temp--;
        }
        return arr;
    }
}