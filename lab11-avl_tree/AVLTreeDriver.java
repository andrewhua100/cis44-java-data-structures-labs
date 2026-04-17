public class AVLTreeDriver {
    // TODO: Implement the AVLNode class
    static class AVLNode {
        int key, height;
        AVLNode left, right;

        // Constructor
        AVLNode(int key) {
            this.key = key;
            this.height = 1;
            this.left = null;
            this.right = null;
            // TODO: Initialize key, height, left, and right
        }
    }

    public static class AVLTree {

        AVLNode root;

        // TODO: Return height of a node
        int height(AVLNode N) {
            if(N!=null) {
                return N.height;
            }
            return 0;
        }

        // TODO: Return maximum of two integers
        int max(int a, int b) {
            return Math.max(a,b); // placeholder
        }

        // TODO: Compute balance factor
        int getBalance(AVLNode N) {
            if(N!=null) {
                return height(N.left) - height(N.right);
            }
            return 0;
        }

        // TODO: Right rotation
        AVLNode rightRotate(AVLNode y) {
            //y is the root, x is y's left child, xr is x's right child
            AVLNode x = y.left;
            AVLNode xr = x.right;

            //x becomes the root, y becomes x's right child, xr becomes y's left child
            x.right = y;
            y.left = xr;

            //update height
            y.height = 1 + max(height(y.left), height(y.right));
            x.height = 1 + max(height(x.left), height(x.right));
            return x;
        }

        // TODO: Left rotation
        AVLNode leftRotate(AVLNode x) {
            //x is the root, y is x's right child, yl is y's left child
            AVLNode y = x.right;
            AVLNode yl = y.left;

            //y becomes the root, x becomes y's left child, yl becomes x's right child
            y.left = x;
            x.right = yl;

            //update height
            x.height = 1 + max(height(x.left), height(x.right));
            y.height = 1 + max(height(y.left), height(y.right));
            return y;
        }

        // TODO: Left-Right rotation
        AVLNode leftRightRotate(AVLNode z) {
            //rotate z's left child left first to make it a LL imbalance
            z.left = leftRotate(z.left);
            //right rotate z
            return rightRotate(z); // placeholder
        }

        // TODO: Right-Left rotation
        AVLNode rightLeftRotate(AVLNode y) {
            //rotate y's right child right to make it a RR imbalance
            y.right = rightRotate(y.right);
            //left rotate y
            return leftRotate(y); // placeholder
        }

        // Public insert method
        public void insert(int key) {
            root = insert(root, key);
        }

        // TODO: Recursive insertion with rebalancing
        private AVLNode insert(AVLNode node, int key) {
            //base case
            if(node == null){
                return new AVLNode(key);
            }
            //if key is greater than current node's key traverse right
            if(node.key<key){
                node.right = insert(node.right,key);
            }
            //if key is less than current node's key traverse left
            else if(node.key>key){
                node.left = insert(node.left,key);
            }
            //duplicate node so we don't insert
            else{
                return node;
            }
            //update height of the avl tree
            node.height = 1 + max(height(node.left), height(node.right));
            //find the balance of the avl tree
            int balance = getBalance(node);

            //LL case, so we right rotate
            if(balance>1 && node.left.key>key){
                node = rightRotate(node);
            }
            //LR case, left rotate the left child of the node and right rotate the node
            if(balance>1 && node.left.key<key){
                node = leftRightRotate(node);
            }
            //RR case, so we left rotate
            if(balance<-1 && node.right.key<key) {
                node = leftRotate(node);
            }
            //RL case, so we right rotate the right child of the node and left rotate the node
            if(balance<-1 && node.right.key>key){
                node = rightLeftRotate(node);
            }
            return node; // return balanced node;
        }

        // TODO: Traversal methods
        public void inorder() {
            inorder(root);
            System.out.println();
        }

        private void inorder(AVLNode node) {
            if(node!=null){
                inorder(node.left);
                System.out.print(node.key + " ");
                inorder(node.right);
            }
        }

        public void preorder() {
            preorder(root);
            System.out.println();
        }

        private void preorder(AVLNode node) {
            if(node!=null){
                System.out.print(node.key + " ");
                preorder(node.left);
                preorder(node.right);
            }
        }

        public void postorder() {
            postorder(root);
            System.out.println();
        }

        private void postorder(AVLNode node) {
            if(node!=null){
                postorder(node.left);
                postorder(node.right);
                System.out.print(node.key + " ");
            }
        }
    }
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // TODO: Insert test values
        tree.insert(10);
        tree.insert(20);
        tree.insert(30); //RR imbalance
        tree.insert(8);
        tree.insert(7); //LL imbalance
        tree.insert(9); //LR imbalance
        tree.insert(25);//RL imbalance

        // TODO: Add more insertions to test all 4 rotation cases

        // TODO: Print traversals
        System.out.print("Inorder: "); // 7 8 9 10 20 25 30
        tree.inorder();

        System.out.print("Preorder: "); // 10 8 7 9 25 20 30
        tree.preorder();

        System.out.print("Postorder: "); // 7 9 8 20 30 25 10
        tree.postorder();

        // TODO: Add expected results as comments for verification
    }
}