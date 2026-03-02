import java.util.EmptyStackException;

// You will need a functioning Stack implementation (like ArrayStack) for this to work.
interface Stack<E>{
    void push(E element);
    E pop();
    E top();
    int size();
    boolean isEmpty();
}
class ArrayStack<E> implements Stack<E> {
    //array of objects, size, and initial capacity
    Object[] array;
    int size;
    final static int INITIAL_CAPACITY = 100;

    //constructor without set size
    public ArrayStack(){
        this(INITIAL_CAPACITY);
    }
    //constructor with set size
    public ArrayStack(int size){
       array = new Object[size];
    }
    //returns current size of the stack
    @Override
    public int size(){
        return size;
    }
    //pushes an element to the front of the stack
    @Override
    public void push(E element) throws IllegalStateException{
        //if stack is full throw an exception
        if(size() == array.length){
            throw new IllegalStateException("Stack is full");
        }
        //sets the next element in the stack to element
        array[size] = element;
        //add one to size
        size++;
    }
    //removes the element at the top of the stack
    //suppress warning of unchecked cast
    @Override
    @SuppressWarnings("unchecked")
    public E pop() throws EmptyStackException {
        //if stack is empty throw an exception
        if(isEmpty()){
            throw new EmptyStackException();
        }
        //cast object to E
        E curr = (E) array[size-1];
        //set last element to null and update size
        array[size-1] = null;
        size--;
        return curr;
    }
    //returns the element at the top of the stack
    @Override
    @SuppressWarnings("unchecked")
    public E top(){
        return (E)array[size];
    }
    //returns true if size is zero
    @Override
    public boolean isEmpty(){
        return size() == 0;
    }
}

public class SyntaxChecker {

    /**
     * Uses a stack to check if a line of code has balanced symbols.
     * @param line The string of code to check.
     * @return true if symbols are balanced, false otherwise.
     */
    public static boolean isBalanced(String line) {
        // TODO: Implement this method using a Stack.
        Stack<Character> buffer = new ArrayStack<Character>(line.length());

        char temp; //temporary char variable to track the char at each index

        //for loop to iterate through the string
        for(int i = 0;i<line.length();i++){
            temp = line.charAt(i);
            //if character is an opening symbol push to the stack
            if(temp == '(' || temp == '{' || temp == '[' ){
                buffer.push(temp);
            }
            //if character is a closing symbol, check if the top of the stack matches with the closing symbol. If not, the string is unbalanced
            else if(temp == ')' || temp == '}' || temp == ']' ){
                //if the stack is empty, the string is unbalanced
                if(buffer.isEmpty()){
                    return false;
                }
                //pops the top of the stack and stores it
                char c = buffer.pop();
                //if the symbols don't match the string is unbalanced
                if((temp == ')' && c != '(') || (temp == ']' && c != '[') || (temp == '}' && c != '{')){
                    return false;
                }
            }
        }
        //after the loop check if the stack is empty, if not the string is unbalanced
        return buffer.isEmpty();
    }

    public static void main(String[] args) {
        String line1 = "public static void main(String[] args) { ... }"; // Should be true
        String line2 = "int x = (5 + [a * 2]);"; // Should be true
        String line3 = "System.out.println('Hello');)"; // Should be false (extra closing parenthesis)
        String line4 = "List list = new ArrayList<{String>();"; // Should be false (mismatched)
        String line5 = "if (x > 0) {"; // Should be false (unmatched opening brace)

        System.out.println("Line 1 is balanced: " + isBalanced(line1));
        System.out.println("Line 2 is balanced: " + isBalanced(line2));
        System.out.println("Line 3 is balanced: " + isBalanced(line3));
        System.out.println("Line 4 is balanced: " + isBalanced(line4));
        System.out.println("Line 5 is balanced: " + isBalanced(line5));
    }
}