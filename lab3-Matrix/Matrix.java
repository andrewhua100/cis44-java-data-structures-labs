import java.util.Random;
public class Matrix {
    private int[][] data;

    public Matrix(int rows, int cols){
        this.data = new int[rows][cols];
    }
    public Matrix(int[][] data){
        this.data = data;
    }
    public void populateRandom(){
        //fills the matrix with random values from 1 to 10
        Random random = new Random();
        for(int i = 0;i<data.length;i++){
            for(int j = 0;j<data[i].length;j++){
                data[i][j] = random.nextInt(10)+1;
            }
        }
    }
    public Matrix add(Matrix other){
        //if both matrices don't have the same dimensions throw an exception
        if((other.data.length != data.length) || (other.data[0].length!=data[0].length)){
            throw new IllegalArgumentException("Matrices don't have same dimensions");
        }
        else{
            Matrix newMatrix = new Matrix(data.length,data[0].length);
            for(int i = 0;i<data.length;i++){
                for(int j = 0;j<data[i].length;j++){
                    newMatrix.data[i][j] = data[i][j] + other.data[i][j];
                }
            }
            return newMatrix;
        }
    }
    public Matrix multiply(Matrix other){
        //if number of columns in the first matrix aren't equal to the number of rows in the second matrix throw an exception
        if(other.data.length != data[0].length){
            throw new IllegalArgumentException("Dimensions incompatible");
        }
        else{
            Matrix newMatrix = new Matrix(data.length,other.data[0].length);
            //temporary variable count to track matrix multiplication
            int count = 0;
            //first loop for the rows of the first matrix
            for(int i = 0;i<data.length;i++){
                //second loop for the columns of the second matrix
                for(int j = 0;j<other.data[i].length;j++){
                    //third loop for the rows of the second matrix
                    for(int k = 0;k<other.data.length;k++){
                        //since number of columns in the first matrix = number of rows in the second matrix
                        count+=(data[i][k]*other.data[k][j]);
                    }
                    //set the value into the new matrix and reset count to zero
                    newMatrix.data[i][j] = count;
                    count = 0;
                }
            }
            return newMatrix;
        }
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0;i<data.length;i++){
            for(int j = 0;j<data[i].length;j++){
                s.append(data[i][j]);
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    public static void main(String[] args){
        //first matrix constructor implementation
        Matrix matrix1 = new Matrix(4,5);
        matrix1.populateRandom();
        Matrix matrix2 = new Matrix(4,5);
        matrix2.populateRandom();

        //second matrix constructor implementation
        int[][] m = {{1, 2, 5}, {5, 6, 3}, {3, 7, 2}, {9, 6, 8}, {10, 4, 2}};
        Matrix matrix3 = new Matrix(m);

        System.out.println("Matrix 1");
        System.out.println(matrix1.toString());
        System.out.println("Matrix 2");
        System.out.println(matrix2.toString());
        System.out.println("Matrix 3");
        System.out.println(matrix3.toString());

        Matrix addMatrix = matrix1.add(matrix2);
        Matrix multiplyMatrix = matrix1.multiply(matrix3);
        //error because matrix2's number of rows do not equal matrix1's number of columns
        //Matrix errorMatrix = matrix3.multiply(matrix1);
        //Matrix errorMatrix1 = matrix3.add(matrix1);
        System.out.println("Result of the Matrix 1 and Matrix 2 added");
        System.out.println(addMatrix.toString());

        System.out.println("Result of the Matrix 1 and Matrix 3 multiplied");
        System.out.println(multiplyMatrix.toString());

    }


}
