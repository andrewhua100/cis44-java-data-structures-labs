class Task implements Comparable<Task>{
    private final String description;
    private final int priority;
    public Task(String description, int priority){
        this.description = description;
        this.priority = priority;
    }
    @Override
    public int compareTo(Task other){
        return Integer.compare(this.priority, other.priority);
    }
    public String getDescription(){
        return description;
    }
    public int getPriority(){
        return priority;
    }
    @Override
    public String toString(){
        return "priority: " + priority + ", Task description: " + description;
    }
}

class SmartScheduler{
    Task[] heap;
    int capacity;
    int size = 0;
    public SmartScheduler(int capacity){
        this.heap = new Task[capacity];
        this.capacity = capacity;
    }
    public void insert(Task task){
        heap[size] = task;
        size++;
        bubbleUp(size-1);
    }
    private void bubbleUp(int index){
        int parent = (index-1)/2;
        while(index>0 && heap[index].compareTo(heap[parent])>0){
            swap(index,parent);
            index = parent;
            parent = (index-1)/2;
        }
    }
    private void bubbleDown(int index){
        while(true) {
            //left and right children
            int l = 2 * index + 1;
            int r = l + 1;
            int largest= index;

            // Find larger child, swap if child > parent, move down

            if (l < size && heap[l].compareTo(heap[largest])>0){
                largest = l;
            }
            if(r < size && heap[r].compareTo(heap[largest])>0){
                largest = r;
            }
            //if largest is still index
            if(largest == index){
                break;
            }
            swap(index,largest);
            index = largest;
        }
    }
    private void swap(int i, int j){
        Task temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public Task peek(){
        if(isEmpty()){
            return null;
        }
        return heap[0];
    }
    //implemented in phase 3
    public void remove(){}

}
