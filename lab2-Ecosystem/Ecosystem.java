import java.util.Random;
import java.util.Scanner;

// Step 1: Create the abstract parent class
abstract class Animal {
    // You can add shared attributes or methods here if needed
    // An abstract method for toString() can be helpful for visualization
    public abstract String toString();
}

// Step 2: Create the concrete animal classes
class Bear extends Animal {
    @Override
    public String toString() {
        return "B";
    }
}

class Fish extends Animal {
    @Override
    public String toString() {
        return "F";
    }
}

// Main class to run the simulation
public class Ecosystem {
    private Animal[] river;
    private Random random;

    public Ecosystem(int riverSize) {
        this.river = new Animal[riverSize];
        this.random = new Random();
        int n;
        for(int i = 0;i<riverSize;i++){
            n = this.random.nextInt(3);
            switch(n){
                case 0:
                    break;
                case 1:
                    this.river[i] = new Bear();
                    break;
                case 2:
                    this.river[i] = new Fish();
                    break;
            }
        }
    }

    public void runStep(int riverSize) {
        Animal[] newE = new Animal[riverSize];
        int n;
        Animal current;
        int bcount = 0;
        int fcount = 0;
        for(int i = 0;i<riverSize;i++){
            current = river[i];
            if(current==null){
                continue;
            }
            if(i == 0){
                n = random.nextInt(2);
            }
            else if(i == riverSize-1){
                n = random.nextInt(2)-1;
            }
            else{
                n = random.nextInt(3)-1;
            }
            if(current instanceof Bear) {
                if (newE[i + n] instanceof Fish) {
                    newE[i + n] = current;
                }
                else if (newE[i + n] instanceof Bear) {
                    bcount++;
                }
                else {
                    newE[i + n] = current;
                }
            }
            else if(current instanceof Fish) {
                if (newE[i + n] instanceof Fish) {
                    fcount++;
                }
                else if (newE[i + n] instanceof Bear) {
                    continue;
                }
                else {
                    newE[i + n] = current;
                }
            }
        }
        int emptycount = 0;
        for(int i = 0;i<riverSize;i++){
            if(newE[i]==null){
                emptycount++;
            }
        }
        int randomm = 0;
        int empty = 0;
        for(int i = 0; i < fcount; i++) {
            randomm = random.nextInt(emptycount)+1;
            for(int j = 0;j<riverSize;j++){
                if (newE[j] == null) {
                    empty++;
                }
                if(empty==randomm){
                    newE[j]=new Fish();
                    emptycount--;
                    empty = 0;
                    break;
                }
            }
        }
        empty = 0;
        for(int i = 0; i < bcount; i++) {
            randomm = random.nextInt(emptycount)+1;
            for(int j = 0;j<riverSize;j++){
                if (newE[j] == null) {
                    empty++;
                }
                if(empty==randomm){
                    newE[j]=new Bear();
                    emptycount--;
                    empty = 0;
                    break;
                }
            }
        }
        river = newE;

    }


    public void visualize() {
        for (Animal animal : river) {
            System.out.print(animal == null ? "-" : animal.toString());
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the size of your river");
        int rSize = scan.nextInt();
        Ecosystem eco = new Ecosystem(rSize);
        System.out.println("Input number of steps");
        int times = scan.nextInt();
        System.out.print("Initial River: ");
        eco.visualize();
        for(int i = 1;i<=times;i++){
            eco.runStep(eco.river.length);
            System.out.print("Step " + i + ": ");
            eco.visualize();
        }
    }
}