public class TextEditor {
    private static class Node {
        //previous node, next node, and the text state
        String textState;
        Node prev;
        Node next;
        //Node constructor
        public Node(String textState, Node prev, Node next){
            this.textState = textState;
            this.prev = prev;
            this.next = next;
        }
    }
    //pointer to the current Node
    private Node currentNode;

    public TextEditor() {
        // Start with an initial empty string state.
        Node initialNode = new Node("", null, null);
        this.currentNode = initialNode;
    }

    public void add(String newText) {
        // Create a new node with the updated text.
        // Set its 'prev' to the current node.
        Node newNode = new Node(currentNode.textState + newText, currentNode, null);
        currentNode.next = newNode; //Set the current node's 'next' to this new node.
        currentNode = newNode; // Finally, update currentNode to point to the new node.
    }

    public String undo() {
        if(currentNode.prev != null){// Check if currentNode.prev is not null.
            currentNode = currentNode.prev;// Move currentNode back
            System.out.println("Undo done");
            return currentNode.textState; // return text state
        }
        // Otherwise, you can't undo.
        System.out.println("Can't undo");
        return currentNode.textState;
    }

    public String redo() {
        if(currentNode.next != null){ //Check if currentNode.next is not null.
            currentNode = currentNode.next; // Move currentNode forward and return the text
            System.out.println("Redo done");
            return currentNode.textState; //return text state
        }
        //Otherwise you can't redo
        System.out.println("Can't redo");
        return currentNode.textState;
    }

    public void printCurrent() {// prints current text state
        System.out.println("Current text state: " + currentNode.textState);
    }

    public static void main(String[] args) {
        //new blank text editor
        TextEditor text = new TextEditor();
        //adding apple and orange
        text.add("apple");
        text.add(" orange");
        text.printCurrent();
        //undo orange
        System.out.println(text.undo());
        //adding banana
        text.add(" banana");
        text.printCurrent();
        //redo after adding so redo history is cleared
        System.out.println(text.redo());
        //adding mango
        text.add(" mango");
        text.printCurrent();
        //mango gets undo'd and redo'd
        System.out.println(text.undo());
        System.out.println(text.redo());
    }
}