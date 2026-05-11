// removes the highest priority element
public Task remove(){
    if (isEmpty()) return null;
    Task root = heap[0];
    // Move last element to root
    heap[0] = heap[size-1];
    heap[size-1] = null;
    size--;
    //put the new root in the correct place
    if (size>0) {
        bubbleDown(0);
    }
    return root;
}
