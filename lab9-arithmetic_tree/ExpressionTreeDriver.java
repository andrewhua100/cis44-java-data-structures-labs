public class ExpressionTreeDriver {

    public static class BinaryTreeNode {
        String value; // Can be an operator "+" or an operand "3"
        BinaryTreeNode parent;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(String value) {
            this.value = value;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        // --- TO BE COMPLETED BY STUDENT ---

        /**
         * Performs a preorder traversal starting from this node.
         * (Visit Parent, then Left, then Right)
         * This should output Prefix notation.
         */
        public void traversePreorder(BinaryTreeNode node) {
            // 1. Print this node's value
            // 2. Recursively call on left child (if not null)
            // 3. Recursively call on right child (if not null)
            if(node == null){
                return;
            }
            System.out.print(node.value);
            traversePreorder(node.left);
            traversePreorder(node.right);
        }

        /**
         * Performs an inorder traversal starting from this node.
         * (Visit Left, then Parent, then Right)
         * This should output Infix notation (you can add parentheses for clarity).
         */
        public void traverseInorder(BinaryTreeNode node) {
            // (Optional: Print "(" before recursing left)
            // 1. Recursively call on left child (if not null)
            // 2. Print this node's value
            // 3. Recursively call on right child (if not null)
            // (Optional: Print ")" after recursing right)
            if(node == null){
                return;
            }
            System.out.print("(");
            traverseInorder(node.left);
            System.out.print(node.value);
            traverseInorder(node.right);
            System.out.print(")");
        }

        /**
         * Performs a postorder traversal starting from this node.
         * (Visit Left, then Right, then Parent)
         * This should output Postfix (RPN) notation.
         */
        public void traversePostorder(BinaryTreeNode node) {
            // 1. Recursively call on left child (if not null)
            // 2. Recursively call on right child (if not null)
            // 3. Print this node's value
            if(node == null){
                return;
            }
            traversePostorder(node.left);
            traversePostorder(node.right);
            System.out.print(node.value);
        }
    }
    public static void main(String[] args) {
        // 1. Build the Tree
        // Manually build the tree for: ( (3 + 7) * (9 - 4) )
        //
        //         *
        //        / \
        //       +   -
        //      / \ / \
        //     3  7 9  4
        //

        BinaryTreeNode root = new BinaryTreeNode("*");

        BinaryTreeNode nodePlus = new BinaryTreeNode("+");
        BinaryTreeNode nodeMinus = new BinaryTreeNode("-");

        // --- TO BE COMPLETED BY STUDENT ---
        // Connect root, nodePlus, and nodeMinus
        root.left = nodePlus;
        root.right = nodeMinus;
        nodePlus.parent = root;
        nodeMinus.parent = root;

        // Create and connect the leaves (operands)
        BinaryTreeNode node3 = new BinaryTreeNode("3");
        BinaryTreeNode node7 = new BinaryTreeNode("7");
        BinaryTreeNode node9 = new BinaryTreeNode("9");
        BinaryTreeNode node4 = new BinaryTreeNode("4");

        // Connect leaves to their parents (nodePlus and nodeMinus)
        nodePlus.left = node3;
        nodePlus.right = node7;
        node3.parent = nodePlus;
        node7.parent = nodePlus;

        nodeMinus.left = node9;
        nodeMinus.right = node4;
        node9.parent = nodeMinus;
        node4.parent = nodeMinus;



        // 2. Perform Traversals
        System.out.println("--- Preorder Traversal (Prefix) ---");
        root.traversePreorder(root);

        System.out.println("\n\n--- Inorder Traversal (Infix) ---");
        root.traverseInorder(root);

        System.out.println("\n\n--- Postorder Traversal (Postfix) ---");
        root.traversePostorder(root);
    }
}