import java.util.Iterator;
public class ItineraryManager {
    // You'll need a Position interface
    public interface Position<E> {
        E getElement();
    }

    public static class LinkedPositionalList<E> implements Iterable<E> {
        // --- Nested Node Class (implements Position) ---
        private static class Node<E> implements Position<E> {
            E current;
            Node<E> prev;
            Node<E> next;
            //constructor for Node
            public Node(E current, Node<E> prev, Node<E> next){
                this.current = current;
                this.prev = prev;
                this.next = next;
            }
            //implementing Position getElement() method;
            public E getElement(){
                return current;
            }
        }

        private Node<E> header;
        private Node<E> trailer;
        private int size = 0;

        //constructor creating sentinel nodes
        public LinkedPositionalList() {
            header = new Node<E>(null, null, null);
            trailer = new Node<E>(null, header, null);
            header.next = trailer;
            // ... constructor to create sentinel nodes ...
        }
        // method to make sure the position is valid and converting the position into a node
        private Node<E> validate(Position<E> p){
            //if p isn't an instance of a node, it's invalid
            if(!(p instanceof Node<E>)){
                throw new IllegalArgumentException("Position invalid");
            }
            //casting position to node
            Node<E> n = (Node<E>)p;
            //if the next pointer of the node is null, then the position isn't in the list anymore
            if(n.next == null){
                throw new IllegalArgumentException("Position not in list");
            }
            return n;
        }

        //returns first element
        public Position<E> first(){
            //checking if list is empty
            if(size==0){
                return null;
            }
            return header.next;
        }
        //returns last element;
        public Position<E> last(){
            //checking if list is empty
            if(size==0){
                return null;
            }
            return trailer.prev;
        }
        //returns the element before position p
        public Position<E> before(Position<E> p){
            //validating the position
            Node<E> n = validate(p);
            //if the previous node is header return null
            if(n.prev == header){
                return null;
            }
            return n.prev;
        }
        //returns the element after position p
        public Position<E> after(Position<E> p){
            //validating the position
            Node<E> n = validate(p);
            //if the previous node is header return null to avoid error in the iterator
            if(n.next == trailer){
                return null;
            }
            return n.next;
        }
        //adds an element at the beginning of the list
        public Position<E> addFirst(E e){
            //creating a new node between the header and the previous first node
            Node<E> newNode = new Node<E>(e,header,header.next);
            //updating pointers of the other nodes
            header.next.prev = newNode;
            header.next = newNode;
            //add one to size;
            size++;
            return newNode;
        }
        //adds an element at the end of the list
        public Position<E> addLast(E e){
            //creating a new node between the trailer and the previous last node
            Node<E> newNode = new Node<E>(e,trailer.prev,trailer);
            //updating pointers of the other nodes
            trailer.prev.next = newNode;
            trailer.prev = newNode;
            //add one to size
            size++;
            return newNode;
        }
        //adds an element before position p
        public Position<E> addBefore(Position<E> p, E e){
            //validating position
            Node<E> n = validate(p);
            //creating a new node between node's previous pointer and the node
            Node<E> newNode = new Node<E>(e, n.prev , n);
            //updating pointers of the other nodes
            n.prev.next = newNode;
            n.prev = newNode;
            //add one to size
            size++;
            return newNode;
        }
        //adds an element after position p
        public Position<E> addAfter(Position<E> p, E e){
            //validating position
            Node<E> n = validate(p);
            //creating a new node between node and the node's next pointer
            Node<E> newNode = new Node<E>(e, n , n.next);
            //updating pointers of other nodes
            n.next.prev = newNode;
            n.next = newNode;
            //add one to size
            size++;
            return newNode;
        }
        //sets the element at position p to e
        public E set(Position<E> p, E e){
            //validate position
            Node<E> n = validate(p);
            //element before change
            E returnValue = n.current;
            //changing the element
            n.current = e;
            return returnValue;
        }
        //removes the element at position p
        public E remove(Position<E> p){
            //validating position
            Node<E> n = validate(p);
            //updating the neighbors pointers
            n.next.prev = n.prev;
            n.prev.next = n.next;

            E curr = n.current;
            //setting the node to null
            n.next = null;
            n.prev = null;
            n.current = null;
            size--;

            return curr;

        }

        // --- Nested Iterator Class ---
        private class ElementIterator implements Iterator<E> {
            Position<E> cursor = first(); // Start at the first element

            //if the list has elements left
            public boolean hasNext() {
                return cursor != null;
            }

            //method returning the next element
            public E next() {
                //validating the cursor position
                Node<E> curr = validate(cursor);
                E element = curr.current;
                cursor = after(cursor);
                return element;
                // Store the element at the current cursor
                // Advance the cursor to the next position using after()
                // Return the stored element
            }
        }

        @Override
        public Iterator<E> iterator() {
            return new ElementIterator();
        }
    }
    public static void main(String[] args){
        LinkedPositionalList<String> itinerary = new LinkedPositionalList<String>();
        //locations of the stops
        Position<String> location1 = itinerary.addFirst("California");
        Position<String> location2 = itinerary.addAfter(location1, "Oregon");
        Position<String> location3 = itinerary.addLast("Nevada");
        Position<String> location4 = itinerary.addBefore(location3, "New York");

        itinerary.set(location2, "Idaho");
        itinerary.remove(location1);
        for(String it: itinerary){
            System.out.print(it + " ");
        }

    }
}
