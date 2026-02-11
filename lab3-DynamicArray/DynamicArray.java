import java.util.Scanner;
import java.util.Random;

public class DynamicArray<T> {
    private T[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public DynamicArray(){
        this.data =(T[]) new Object[INITIAL_CAPACITY];
    }
    //adds an element to the dynamic array
    public void add(T element){
        //if the size of the dynamic array reaches the current bound, resize it
        if(size==data.length){
            resize();
        }
        data[size]=element;
        size++;
    }
    //gets the element at the index, throws an exception if the index is invalid
    public T get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index invalid");
        }
        else{
            return data[index];
        }
    }
    //remove the element at the index, throws an exception if the index is invalid
    public T remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index invalid");
        }
        else{
            T removedElement = data[index];
            data[index] = null;
            for(int i = index+1;i<size;i++){
                data[i-1] = data[i];
            }
            size--;
            return removedElement;
        }
    }
    //returns the size of the dynamic array
    public int size(){
        return size;
    }
    //makes the dynamic array 2x bigger
    private void resize(){
        T[] newdata = (T[]) new Object[data.length*2];
        for(int i = 0;i<data.length;i++){
            newdata[i]=data[i];
        }
        data = newdata;
    }
    //method to print the dynamic array
    public void visualize(){
        for(int i = 0;i<size;i++){
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Random random = new Random();
        DynamicArray<Integer> nums = new DynamicArray<>();
        int n;
        for(int i = 0;i<10;i++){
            n = random.nextInt(10)+1;
            nums.add(n);
        }
        nums.visualize();
        System.out.println("Size: " + nums.size());
        nums.remove(3);
        nums.visualize();
        System.out.println("Size: " + nums.size());
        for(int i = 0;i<11;i++){
            n = random.nextInt(10)+1;
            nums.add(n);
        }
        nums.visualize();
        System.out.println("Size: " + nums.size());
        System.out.println("Value at index 12: " + nums.get(12));
    }

}
