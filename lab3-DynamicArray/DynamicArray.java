import java.util.Scanner;
import java.util.Random;

public class DynamicArray<T> {
    private T[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public DynamicArray(){
        this.data =(T[]) new Object[INITIAL_CAPACITY];
    }

    public void add(T element){
        if(size==data.length){
            resize();
        }
        data[size]=element;
        size++;
    }

    public T get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index invalid");
        }
        else{
            return data[index];
        }
    }
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

    public int size(){
        return size;
    }
    private void resize(){
        T[] newdata = (T[]) new Object[data.length*2];
        for(int i = 0;i<data.length;i++){
            newdata[i]=data[i];
        }
        data = newdata;
    }
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
