import java.util.ArrayList;
import java.util.List;

public class CompanyTreeDriver {
    public static class GeneralTreeNode {
        String name; // Employee name or department title
        GeneralTreeNode parent;
        List<GeneralTreeNode> children;

        public GeneralTreeNode(String name) {
            this.name = name;
            this.parent = null;
            this.children = new ArrayList<>();
        }

        // Method to add a child to this node
        public void addChild(GeneralTreeNode child) {
            child.parent = this;
            this.children.add(child);
        }

        // --- TO BE COMPLETED BY STUDENT ---

        /**
         * Performs a preorder traversal starting from this node.
         * Prints the name of each node visited.
         * (Visit Parent, then visit children)
         */
        public void traversePreorder(GeneralTreeNode node) {
            if(node == null){
                return;
            }
            // 1. Print this node's name
            System.out.print(node.name + " ");
            // 2. Recursively call traversePreorder on each child
            for(GeneralTreeNode child : node.children){
                traversePreorder(child);
            }
        }

        /**
         * Performs a postorder traversal starting from this node.
         * Prints the name of each node visited.
         * (Visit children, then visit Parent)
         */
        public void traversePostorder(GeneralTreeNode node) {
            if(node==null){
                return;
            }
            // 1. Recursively call traversePostorder on each child
            for(GeneralTreeNode child : node.children){
                traversePostorder(child);
            }
            // 2. Print this node's name
            System.out.print(node.name + " ");
        }
    }
    public static void main(String[] args) {
        // 1. Build the Tree
        // Create the root node (e.g., "CEO")
        GeneralTreeNode root = new GeneralTreeNode("CEO");

        // Create department heads (children of root)
        GeneralTreeNode vpSales = new GeneralTreeNode("VP of Sales");
        GeneralTreeNode vpEngineering = new GeneralTreeNode("VP of Engineering");
        root.addChild(vpSales);
        root.addChild(vpEngineering);

        // --- TO BE COMPLETED BY STUDENT ---
        // Add more nodes to build the following hierarchy:
        //
        // CEO
        //  |-- VP of Sales
        //  |    |-- Sales Manager (NA)
        //  |    |-- Sales Manager (EU)
        //  |
        //  |-- VP of Engineering
        //       |-- Dev Team Lead
        //       |    |-- Developer 1
        //       |    |-- Developer 2
        //       |
        //       |-- QA Team Lead


        // Example for "VP of Sales" children:
        GeneralTreeNode salesNA = new GeneralTreeNode("Sales Manager (NA)");
        GeneralTreeNode salesEU = new GeneralTreeNode("Sales Manager (EU)");
        vpSales.addChild(salesNA);
        vpSales.addChild(salesEU);

        // ... add children for "VP of Engineering" ...
        GeneralTreeNode DevTeamLead = new GeneralTreeNode("Dev Team Lead");
        GeneralTreeNode Developer1 = new GeneralTreeNode("Developer 1");
        GeneralTreeNode Developer2 = new GeneralTreeNode("Developer 2");
        GeneralTreeNode QATeamLead = new GeneralTreeNode("QA Team Lead");
        vpEngineering.addChild(DevTeamLead);
        vpEngineering.addChild(QATeamLead);
        DevTeamLead.addChild(Developer1);
        DevTeamLead.addChild(Developer2);


        // 2. Perform Traversals
        System.out.println("--- Preorder Traversal (Company Hierarchy) ---");
        root.traversePreorder(root);

        System.out.println("\n--- Postorder Traversal (Staff Roll Call) ---");
        root.traversePostorder(root);
    }
}
