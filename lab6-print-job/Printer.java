// You will need a functioning Queue implementation (like LinkedQueue) for this to work.
interface Queue<E> {
    //queue's methods
    void enqueue(E e);
    E dequeue();
    int size();
}
//Node object for the LinkedQueue
class Node<E>{
    E element;
    Node<E> next;
    public Node(E element, Node<E> next){
        this.element = element;
        this.next = next;
    }
}
class LinkedQueue<E> implements Queue<E>{
    //front and back pointer
    Node<E> front;
    Node<E> back;
    //size of the linked queue
    int size;
    //constructor
    public LinkedQueue(){
        front = null;
        back = null;
    }
    //adds an element to the back of the queue
    @Override
    public void enqueue(E e){
        Node<E> newNode = new Node<>(e, null);
        //if size of the linked queue is null set back and front to newNode;
        if(size() == 0){
            back = newNode;
            front = newNode;
        }
        //otherwise update current back's next pointer, and set back to newNode
        else{
            back.next = newNode;
            back = newNode;
        }
        //add one to size
        size++;
    }
    //removes first element of queue
    @Override
    public E dequeue(){
        Node<E> first = front;
        //update front and size
        front = front.next;
        size--;
        return first.element;
    }
    //returns size of linked queue
    @Override
    public int size(){
        return size;
    }

}

/**
 * Represents a single document to be printed.
 */
class PrintJob {
    private String documentName;
    private int pageCount;

    // Implement the constructor
    public PrintJob(String documentName, int pageCount) {
        this.documentName = documentName;
        this.pageCount = pageCount;
    }

    //  Implement the toString method to return a descriptive string
    // e.g., "PrintJob[Document: report.docx, Pages: 15]"
    @Override
    public String toString() {
        return "PrintJob[Document: " + documentName + ", Page: " + pageCount + "]"; // Placeholder
    }
}

/**
 * Simulates a printer that manages a queue of print jobs.
 */
public class Printer {
    private Queue<PrintJob> jobQueue;

    public Printer() {
        // Initialize the jobQueue with a LinkedQueue
        jobQueue = new LinkedQueue<>(); // Placeholder
    }

    /**
     * Adds a new print job to the rear of the queue.
     * @param job The print job to add.
     */
    public void addJob(PrintJob job) {
        //add message
        System.out.println("Adding to queue: " + job);
        //enqueue the job
        jobQueue.enqueue(job);
    }

    /**
     * Processes the job at the front of the queue.
     */
    public void processNextJob() {
        // Check if the queue is empty. If so, print a message.
        // If not empty, dequeue the job and print a "Processing..." message.
        if(jobQueue.size()==0){
            System.out.println("Queue is empty");
        }
        else{
            PrintJob job = jobQueue.dequeue();
            System.out.println("Processing " + job.toString());
        }
    }

    public static void main(String[] args) {
        Printer officePrinter = new Printer();

        officePrinter.addJob(new PrintJob("Annual_Report.pdf", 25));
        officePrinter.addJob(new PrintJob("Meeting_Agenda.docx", 2));
        officePrinter.addJob(new PrintJob("Presentation_Slides.pptx", 30));

        System.out.println("\n--- Starting to Print ---");
        officePrinter.processNextJob(); // Should print Annual_Report.pdf
        officePrinter.processNextJob(); // Should print Meeting_Agenda.docx

        System.out.println("\nNew high-priority job arrives...");
        officePrinter.addJob(new PrintJob("Urgent_Memo.pdf", 1));

        officePrinter.processNextJob(); // Should print Presentation_Slides.pptx
        officePrinter.processNextJob(); // Should print Urgent_Memo.pdf
        officePrinter.processNextJob(); // Should say the queue is empty
    }
}
  