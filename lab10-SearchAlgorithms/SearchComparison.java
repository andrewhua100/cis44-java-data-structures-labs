public class SearchComparison {

    /**
     * Lab 1a: Iterative Linear Search
     * Searches for 'key' in the array 'arr' one element at a time.
     * @param arr The array to search (can be unsorted).
     * @param key The value to find.
     * @return The index of the key if found, otherwise -1.
     */
    public static int linearSearch(int[] arr, int key) {
        // TODO: Implement the iterative linear search algorithm.
        // Loop through the array from index 0 to the end.
        int comparisonCounter = 0;
        for(int i = 0;i<arr.length;i++) {
            comparisonCounter++;
            if (arr[i] == key) {
                //System.out.println("Key is " + key + ", number of comparisons = " + comparisonCounter);
                return i;
            }
        }

        // If the element at the current index matches the key, return the index.

        return -1; // Return -1 if the loop finishes without finding the key.
    }

    /**
     * Lab 1b: Iterative Binary Search
     * Searches for 'key' in a sorted array 'arr' using the divide-and-conquer method.
     * @param arr The array to search (MUST be sorted).
     * @param key The value to find.
     * @return The index of the key if found, otherwise -1.
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int comparisonCounter = 0;

        // TODO: Implement the iterative binary search algorithm.
        // While low is less than or equal to high:
        // 1. Calculate the middle index: mid = (low + high) / 2
        // 2. If arr[mid] matches the key, return mid.
        // 3. If the key is less than arr[mid], update 'high'.
        // 4. If the key is greater than arr[mid], update 'low'.
        while(low<=high){
            int mid = (low+high)/2;

            if(arr[mid] == key){
                //System.out.println("Key is " + key + ", number of comparisons = " + comparisonCounter);
                return mid;
            }
            else if(arr[mid]<key){
                low = mid+1;
                comparisonCounter++;
            }
            else{
                high = mid-1;
                comparisonCounter++;
            }
        }

        return -1; // Return -1 if the loop finishes (low > high) without finding the key.
    }

    public static void main(String[] args) {
        int[] unsortedData = {22, 8, 12, 1, 9, 30, 4, 15};
        int[] sortedData =   {1, 4, 8, 9, 12, 15, 22, 30};

        System.out.println("--- Lab 1: Search Algorithm Implementation ---");

        // Test Linear Search
        System.out.println("Linear Search (Unsorted):");
        System.out.println("Find 9: Index " + linearSearch(unsortedData, 9)); // Expected: 4
        System.out.println("Find 3: Index " + linearSearch(unsortedData, 3)); // Expected: -1

        // Test Binary Search
        System.out.println("\nBinary Search (Sorted):");
        System.out.println("Find 9: Index " + binarySearch(sortedData, 9)); // Expected: 3
        System.out.println("Find 3: Index " + binarySearch(sortedData, 3)); // Expected: -1
        System.out.println("Find 30: Index " + binarySearch(sortedData, 30)); // Expected: 7

        //System.out.println("Find 30: Index " + linearSearch(sortedData, 30)); // Expected: 7
    }
}