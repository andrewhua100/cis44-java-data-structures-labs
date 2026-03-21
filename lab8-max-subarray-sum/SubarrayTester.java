import java.util.Random;

public class SubarrayTester {
    public class MaxSubarraySolver {

        /**
         * Finds the maximum subarray sum using a brute-force approach.
         * Theoretical Complexity: O(n^2)
         */
        public static int bruteForceMaxSum(int[] arr) {
            int currSum = 0; //1 op
            int maxSum = arr[0]; //2 op
            for(int i = 0;i<arr.length;i++){ //2n + 2 op
                currSum = 0; // n op
                for(int j = i; j<arr.length;j++){ //n(n+1) + n op
                    currSum+=arr[j];// n(n+1) ops
                    maxSum = Math.max(currSum, maxSum); // n(n+1) ops
                }
            }
            // TODO: Implement the O(n^2) brute-force algorithm.
            return maxSum; // 1 op
            // total ops : 3 + 2n+2 + n + n(n+1) + n + 2n(n+1) + 1 = 3n^2 + 7n + 6
            // time complexity O(n^2)
        }

        /**
         * Finds the maximum subarray sum using Kadane's Algorithm.
         * Theoretical Complexity: O(n)
         */
        public static int kadanesAlgorithmMaxSum(int[] arr) {
            // TODO: Implement the O(n) Kadane's Algorithm.
            int currSum = 0; // variable for current subarray sum
            int maxSum = arr[0]; // variable for maximum subarray sum
            //traverse array only once : O(n)
            for(int i = 0;i<arr.length;i++){
                //we decide  whether to continue the current subarray or to start a new one.
                //find the max between the current subarray sum + current value and the current value;
                currSum = Math.max(currSum + arr[i], arr[i]);
                //update maxSum
                maxSum = Math.max(currSum, maxSum);
            }
            return maxSum; // Placeholder
        }
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000, 100000};

        System.out.println("--- Maximum Subarray Sum Algorithm Comparison ---");

        for (int n : sizes) {
            System.out.println("\n--- Testing for array size n = " + n + " ---");
            int[] randomArray = generateRandomArrayWithNegatives(n);

            long start = System.nanoTime();
            int maxx = MaxSubarraySolver.bruteForceMaxSum(randomArray);
            long end = System.nanoTime();
            System.out.println("Max is " + maxx);
            System.out.println("Time for brute force is " + (end-start)/1000000.0 + "ms");

            start = System.nanoTime();
            maxx = MaxSubarraySolver.kadanesAlgorithmMaxSum(randomArray);
            end = System.nanoTime();
            System.out.println("Max is " + maxx);
            System.out.println("Time for kadane's algorithm is " + (end-start)/1000000.0 + "ms");

            // TODO: Generate a random array and time both algorithms.
        }
    }

    public static int[] generateRandomArrayWithNegatives(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for(int i = 0;i<size;i++){
            arr[i] = random.nextInt(10000)-5000;
        }
        // Implementation provided in previous response
        return arr;
    }
}