import java.util.Objects;
import java.util.Scanner;
interface Polygon{
    double area();
    double perimeter();
}

class Triangle implements Polygon{

    @Override
    public double area(){
        return 0;
    }
    @Override
    public double perimeter(){
        return 0;
    }
}
class IsoscelesTriangle extends Triangle{
    protected double leg;
    protected double base;
    public IsoscelesTriangle(double leg, double base){
        this.leg = leg;
        this.base= base;
    }
    @Override
    public double area(){
        double s = (leg*2 + base) / 2.0;
        return Math.sqrt(s * (s - leg) * (s - leg) * (s - base));
    }
    @Override
    public double perimeter(){
        return 2*leg + base;
    }
}
class EquilateralTriangle extends Triangle{
    protected double length;
    public EquilateralTriangle(double length){
        this.length = length;
    }
    @Override
    public double area(){
        return (Math.sqrt(3) / 4) * Math.pow(length, 2);
    }
    @Override
    public double perimeter(){
        return 3*length;
    }
}
class Quadrilateral implements Polygon{

    @Override
    public double area(){
        return 0;
    }
    @Override
    public double perimeter(){
        return 0;
    }
}
class Pentagon implements Polygon{
    protected double length;
    public Pentagon(double length){
        this.length = length;
    }
    @Override
    public double area(){
        return (5 * Math.pow(length, 2)) / (4 * Math.tan(Math.PI / 5));
    }
    @Override
    public double perimeter(){
        return 5*length;
    }
}
class Hexagon implements Polygon{
    protected double length;
    public Hexagon(double length){
        this.length = length;
    }
    @Override
    public double area(){
        return (3 * Math.sqrt(3) * (length * length)) / 2;
    }
    @Override
    public double perimeter(){
        return 6*length;
    }
}
class Octagon implements Polygon{
    protected double length;
    public Octagon(double length){
        this.length = length;
    }
    @Override
    public double area(){
        return 2 * (1 + Math.sqrt(2)) * length * length;
    }
    @Override
    public double perimeter(){
        return 8*length;
    }
}
class Square extends Rectangle{
    public Square(double length){
        super(length,length);
    }
}
class Rectangle extends Quadrilateral{
    protected double length;
    protected double width;
    public Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }
    @Override
    public double area(){
        return length*width;
    }
    @Override
    public double perimeter(){
        return 2*(length+width);
    }
}



public class PolygonC {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Which polygon would you like to create?(Triangle, Quadrilateral, Pentagon, Hexagon, Octagon)");
        String s = scan.nextLine();
        if(s.equals("Triangle")){
           System.out.println("What type of triangle would you like to create?(Isosceles, Equilateral)");
           s = scan.nextLine();
           if(s.equals("Isosceles")){
               System.out.println("Enter the length of the identical leg");
               s = scan.nextLine();
               double l = Double.parseDouble(s);
               System.out.println("Enter the length of the base");
               s = scan.nextLine();
               double b = Double.parseDouble(s);
               IsoscelesTriangle i = new IsoscelesTriangle(l,b);
               System.out.println("The perimeter of the isosceles triangle is " + i.perimeter() + " and the area of the isosceles triangle is " + i.area());
           }
           else{
               System.out.println("Enter the length of the side");
               s = scan.nextLine();
               double l = Double.parseDouble(s);
               EquilateralTriangle e = new EquilateralTriangle(l);
               System.out.println("The perimeter of the equilateral triangle is " + e.perimeter() + " and the area of the equilateral triangle is " + e.area());
           }
        }
        else if(s.equals("Quadrilateral")){
            System.out.println("What type of quadrilateral would you like to create?(Square, Rectangle)");
            s = scan.nextLine();
            if(s.equals("Square")){
                System.out.println("Enter the length of the side");
                s = scan.nextLine();
                double l = Double.parseDouble(s);
                Square sq = new Square(l);
                System.out.println("The perimeter of the square is " + sq.perimeter() + " and the area of the square is " + sq.area());
            }
            else{
                System.out.println("Enter the width of the rectangle");
                s = scan.nextLine();
                double w = Double.parseDouble(s);
                System.out.println("Enter the length of the rectangle");
                s = scan.nextLine();
                double l = Double.parseDouble(s);
                Rectangle sq = new Rectangle(l,w);
                System.out.println("The perimeter of the rectangle is " + sq.perimeter() + " and the area of the rectangle is " + sq.area());
            }
        }
        else if(s.equals("Pentagon")){
            System.out.println("Enter the length of the side");
            s = scan.nextLine();
            double l = Double.parseDouble(s);
            Pentagon p = new Pentagon(l);
            System.out.println("The perimeter of the pentagon is " + p.perimeter() + " and the area of the pentagon is " + p.area());
        }
        else if(s.equals("Hexagon")){
            System.out.println("Enter the length of the side");
            s = scan.nextLine();
            double l = Double.parseDouble(s);
            Hexagon h = new Hexagon(l);
            System.out.println("The perimeter of the hexagon is " + h.perimeter() + " and the area of the hexagon is " + h.area());
        }
        else if(s.equals("Octagon")){
            System.out.println("Enter the length of the side");
            s = scan.nextLine();
            double l = Double.parseDouble(s);
            Octagon o = new Octagon(l);
            System.out.println("The perimeter of the octagon is " + o.perimeter() + " and the area of the octagon is " + o.area());
        }
    }
}
