import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoFourTreeDriver {
    // Simplified node structure
    static class TwoFourNode {
        List<Integer> keys;
        List<TwoFourNode> children;
        TwoFourNode parent;

        public TwoFourNode() {
            keys = new ArrayList<>();
            children = new ArrayList<>();
            parent = null;
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }

        // Check if node is full (3 keys)
        public boolean isFull() {
            return keys.size() == 3;
        }

        // Find correct child to descend for a given key
        public TwoFourNode getNextChild(int key) {
            // TODO: Implement traversal logic
            int i = 0;
            while (i < keys.size() && key > keys.get(i)) {
                i++;
            }
            return children.get(i);
        }

        // Insert a key into this node (assume node not full)
        public void insertKey(int key) {
            // TODO: Add key and sort
            keys.add(key);
            Collections.sort(keys);
        }
    }

    public static class TwoFourTree {

        private TwoFourNode root;

        public TwoFourTree() {
            root = new TwoFourNode();
        }

        public void insert(int key) {
            TwoFourNode node = root;

            // 1. Descend to the leaf node
            while (!node.isLeaf()) {
                node = node.getNextChild(key);
            }

            // 2. Insert key in leaf
            node.insertKey(key);

            // 3. Handle overflow by splitting
            while (node != null && node.keys.size() > 3) {
                split(node);
                node = node.parent;
            }
        }

        private void split(TwoFourNode node) {
            System.out.println("Splitting node with keys: " + node.keys);
            // TODO: Implement split logic
            //[N1,N2,N3,N4]
            //remove and store middle key N3
            int mid = node.keys.remove(2);
            //create a new right node, and parent node that stores current node's parent
            TwoFourNode right = new TwoFourNode();
            TwoFourNode parent = node.parent;

            //add key to the right of middle key
            right.insertKey(node.keys.remove(2));
            //check if node is a leaf as leafs don't have children
            if(!node.isLeaf()){
                //add nodes to the right of middle node, index 3 and 4
                //after removing 3, 4 becomes 3
                right.children.add(node.children.remove(3));
                right.children.add(node.children.remove(3));
                //update parent of each child
                for(TwoFourNode child: right.children){
                    child.parent = right;
                }
            }
            //if the node is the root, create a new root and add keys and children
            if(node.parent == null){
                TwoFourNode newParent = new TwoFourNode();
                newParent.insertKey(mid);
                newParent.children.add(node);
                newParent.children.add(right);
                node.parent = newParent;
                right.parent = newParent;
                this.root = newParent;
            }
            //otherwise add keys and children to node's parents
            else{
                right.parent = parent;
                // Find where the node was in the parent's children list
                int indexInParent = parent.children.indexOf(node);

                // Insert the promoted key at the correct index
                parent.insertKey(mid);

                // Insert the new right sibling immediately after the original node
                parent.children.add(indexInParent + 1, right);

            }

            // 1. Create a new right node
            // 2. Promote middle key to parent
            // 3. Move keys and children appropriately
            // 4. Update parent pointers
        }

        // Inorder traversal
        public void inorder() {
            System.out.print("Inorder: ");
            inorder(root);
            System.out.println();
        }

        private void inorder(TwoFourNode node) {
            if (node == null) return;

            if (node.isLeaf()) {
                for (int key : node.keys) {
                    System.out.print(key + " ");
                }
            } else {
                int i;
                for (i = 0; i < node.keys.size(); i++) {
                    if (i < node.children.size()) {
                        inorder(node.children.get(i));
                    }
                    System.out.print(node.keys.get(i) + " ");
                }
                if (i < node.children.size()) {
                    inorder(node.children.get(i));
                }
            }
        }
    }
    public static void main(String[] args) {
        TwoFourTree tree = new TwoFourTree();

        // Test sequence for splits
        int[] keys = {10, 20, 30, 40, 50, 60, 70, 80, 90, 5, 15, 25, 35};

        System.out.println("Inserting keys into 2-4 Tree...");
        for (int key : keys) {
            System.out.println("Inserting: " + key);
            tree.insert(key);
        }

        System.out.println("\nFinal Tree Traversals:");
        tree.inorder();

        // TODO: Students can also implement preorder/postorder traversal for practice

        System.out.println("\nExpected Inorder: 5 10 15 20 25 30 35 40 50 60 70 80 90");
    }
}