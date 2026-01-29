import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class DotProduct{
  public static int[] randomArray(int n){
    int[] numbers = new int[n];
    Random random = new Random();
    for(int i = 0;i<n;i++){
      numbers[i]=random.nextInt(10) + 1;
    }
    return numbers;
  }
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    int[] array1 = new int[n];
    int[] array2 = new int[n];
    int[] array3 = new int[n];
    array1 = randomArray(n);
    array2 = randomArray(n);
    for(int i = 0;i<n;i++){
      array3[i] = array1[i]*array2[i];
    }
    System.out.println(Arrays.toString(array1));
    System.out.println(Arrays.toString(array2));
    System.out.println(Arrays.toString(array3));
  }
}



