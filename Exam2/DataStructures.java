import java.util.*;

public class DataStructures {
    // ==========================================
    // 1. RECURSION
    // ==========================================
    /**
     * TODO: Calculate the nth Fibonacci number recursively.
     * sequence: 0, 1, 1, 2, 3, 5, 8, 13...
     * Example: fib(6) -> 8
     */
    public static int recursiveFibonacci(int n) {
        // 1. Write the Base Cases (for n=0 and n=1)
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        // 2. Write the Recursive Step
        return recursiveFibonacci(n-1) + recursiveFibonacci(n-2);
    }

    // ==========================================
    // 2. ANALYSIS OF ALGORITHMS
    // ==========================================
    /**
     * TODO: Find and return the SECOND LARGEST value in an array.
     * Constraint: Try to do this with O(n) time complexity (single pass).
     */
    public static int findSecondMax(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements");
        }
        // TODO: Implement the logic to find the second maximum
        int max = arr[0];
        int secondmax = 0;
        for(int i = 1;i<arr.length;i++){
            if(max<arr[i]){
                secondmax = max;
                max = arr[i];
            }
            else{
                if(arr[i]>secondmax){
                    secondmax = arr[i];
                }
            }
        }
        return secondmax;
    }

    // ==========================================
    // 3. TREES
    // ==========================================
    static class Node {
        int value;
        Node left, right;
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * TODO: Return the sum of values of ONLY the leaf nodes.
     * A leaf is a node with no children (left and right are both null).
     */
    public static int sumLeafNodes(Node root) {
        // TODO: Write the recursive logic here
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null ){
            return root.value;
        }
        return sumLeafNodes(root.left) + sumLeafNodes(root.right);
    }

    // ==========================================
    // 4. SEARCH ALGORITHMS
    // ==========================================
    /**
     * TODO: Implement Binary Search on a SORTED array.
     * Return the index of the target if found, otherwise return -1.
     */
    public static int binarySearch(int[] arr, int target) {
        // TODO: Implement the while loop with low, high, and mid logic
        int low = 0;
        int high = arr.length-1;
        int mid = (low+high)/2;
        while(low<high){
            if(arr[mid]>target){
                high = mid-1;
            }
            if(arr[mid]<target){
                low = mid+1;
            }
            else{
                return mid;
            }
            mid = (low+high)/2;
        }
        return -1; // Placeholder return
    }

    // ==========================================
    // 5. SORTING ALGORITHMS
    // ==========================================
    /**
     * TODO: Implement Selection Sort to sort the array in ascending order.
     * Hint: Find the minimum in the unsorted part and swap once per pass.
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        // TODO: Write the outer and inner loops
        for(int i = 0;i<n;i++){
            int minindex = i;
            for(int j = i;j<n;j++){
                // TODO: Implement the finding of the minimum and the swap
                if(arr[minindex]>arr[j]){
                    minindex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minindex];
            arr[minindex] = temp;
        }
    }

    // ==========================================
    // TEST DRIVER (Do not modify this part)
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== Coding Advanced Data Structures ===\n");

        // Test 1: Recursion (Fibonacci)
        int fibN = 6;
        int expectedFib = 8;
        int actualFib = recursiveFibonacci(fibN);
        printTestResult("1. Recursion (Fibonacci)", expectedFib, actualFib);

        // Test 2: Analysis (Second Max)
        int[] numbers = {10, 5, 20, 8, 15};
        int expectedSecondMax = 15;
        int actualSecondMax = findSecondMax(numbers);
        printTestResult("2. Analysis (Second Max)", expectedSecondMax, actualSecondMax);

        // Test 3: Trees (Leaf Sum)
        // Constructing tree:
        //       1
        //      / \
        //     2   3 (Leaf)
        //    /
        //   4 (Leaf)
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        int expectedLeafSum = 7; // Nodes 3 and 4 are leaves
        int actualLeafSum = sumLeafNodes(root);
        printTestResult("3. Trees (Leaf Sum)", expectedLeafSum, actualLeafSum);

        // Test 4: Search (Binary Search)
        int[] sortedData = {1, 2, 4, 7, 9};
        int target = 7;
        int expectedIndex = 3;
        int actualIndex = binarySearch(sortedData, target);
        printTestResult("4. Search (Binary)", expectedIndex, actualIndex);

        // Test 5: Sorting (Selection Sort)
        int[] sortData = {64, 25, 12, 22, 11};
        String expectedSort = "[11, 12, 22, 25, 64]";
        selectionSort(sortData);
        String actualSort = Arrays.toString(sortData);
        System.out.println("[Test 5] Sorting (Selection Sort)");
        System.out.println(" Expected: " + expectedSort);
        System.out.println(" Actual: " + actualSort);
        if (expectedSort.equals(actualSort)) {
            System.out.println(" Result: [PASS]");
        } else {
            System.out.println(" Result: [FAIL]");
        }
        System.out.println();
    }

    // Helper to print results
    private static void printTestResult(String testName, int expected, int actual) {
        System.out.println("[Test] " + testName);
        System.out.println(" Expected: " + expected);
        System.out.println(" Actual: " + actual);
        if (expected == actual) {
            System.out.println(" Result: [PASS]");
        } else {
            System.out.println(" Result: [FAIL]");
        }
        System.out.println();
    }
}