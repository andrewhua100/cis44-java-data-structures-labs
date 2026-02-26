import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomDynamicArray {
    public static class Item {
        String name;
        //constructor that initializes name
        public Item(String name){
            this.name = name;
        }
        //returns the name of the Item
        public String getName(){
            return name;
        }

        // Constructor, getter, toString...
    }
    public static class Inventory {
        private List<Item> items;
        //inventory constructor
        public Inventory() {
            this.items = new ArrayList<>();
        }
        //method that adds an item to the end of the list
        public void addItem(Item item) {
            items.add(item);
        }

        //method that displays the names of the items in the inventory
        public void display() {
            //iterator to iterate through the list and print it
            Iterator<Item> it = items.iterator();
            while(it.hasNext()){
                System.out.print(it.next().name + " ");
            }
            System.out.println();
        }
        //method to combine 2 items and remove them
        public void combineItems(String name1, String name2) {
            //boolean variables to show if each item has been found or not
            boolean found1 = false;
            boolean found2 = false;
            //temp arraylist that stores the current items list so that if both items weren't found it can be restored
            List<Item> temp = new ArrayList<>(items);
            //iterator to iterate the list
            Iterator<Item> iter = items.iterator();
            while (iter.hasNext()) {
                Item current = iter.next();
                //if name of current item is the same as name1, set found1 to true and remove it
                if (current.getName().equals(name1)){
                    if(!found1){
                        found1=true;
                    }
                    iter.remove();
                }
                //if name of current item is the same as name2, set found2 to true and remove it
                else if(current.getName().equals(name2)) {
                    if(!found2) {
                        found2 = true;
                    }
                    iter.remove();
                }
            }
            //if both are found then combine and add back
            if(found1 && found2){
                Item it = new Item(name1+name2);
                items.add(it);
            }
            //if not, restore list back to how it was before the loop
            else{
                items.clear();
                items.addAll(temp);
            }
        }
    }
    public static void main(){
        Inventory inv = new Inventory();
        //initializing the items in the inventory
        Item book = new Item("book");
        Item note = new Item("note");
        Item apple = new Item("apple");
        Item cat = new Item("cat");
        //adding items to the inventory
        inv.addItem(book);
        inv.addItem(note);
        inv.addItem(apple);
        inv.addItem(cat);
        inv.display();

        inv.combineItems("note","book"); //combines note and book
        inv.display(); //the inventory should remove note and book and add a combined version
        inv.combineItems("apple","ct"); //failed combine attempt
        inv.display(); //the inventory should remain the same as before the failed combine
        inv.combineItems("apple","cat"); //combines apple and cat
        inv.display(); //the inventory should remove apple and cat and add a combine version
    }
}
