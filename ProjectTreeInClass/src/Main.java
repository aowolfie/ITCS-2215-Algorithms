import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by brandonbeckwith on 5/31/16.
 * A main class that uses the Node class to make and transverse a tree
 */
public class Main {

    /*
        Pre-order: Root Left Right
        Post-order: Left Right Root
        In-order: Left Root Right
        Level-Order:
     */

    /**
     * Main constructor
     * @param args command lines arguments
     */
    public static void main(String[] args){
        int[] listONumbers = {1, 5, 7, 13, 23, 9000, 2, 4, 3, 0};

        Node root = new Node(listONumbers[0]);

        for (int i=1; i < listONumbers.length; i++){
            addToBST(root, listONumbers[i]);
        }

        printLevelOrder(root);
    }

    /**
     * Prints Pre-Order, Root Left Right
     * @param root the root of the tree
     */
    public static void printPreOrder(Node root){
        if (root == null){
            return;
        }

        System.out.println(root.value);
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    /**
     * Prints Post-Order, Left Right Root
     * @param root the root of the tree
     */
    public static void printPostOrder(Node root){
        if (root == null){
            return;
        }

        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.println(root.value);
    }

    /**
     * Prints In-Order, Left Root Right
     * @param root the root of the tree
     */
    public static void printInOrder(Node root){
        if (root == null){
            return;
        }

        printInOrder(root.left);
        System.out.println(root.value);
        printInOrder(root.right);
    }

    /**
     * Prints the level order using a queue
     * @param root the root of the tree
     */
    public static void printLevelOrder(Node root){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()){
            Node tempNode = q.poll();
            System.out.println(tempNode.value);
            if (tempNode.left != null) {
                q.add(tempNode.left);
            }
            if (tempNode.right != null) {
                q.add(tempNode.right);
            }
        }
    }

    /**
     * Prints out each level by checking and printing each level
     * @param root the root of the tree
     */
    public static void printMyLevelOrder(Node root){
        int level = 1;
        while (checkLevel(root, level)){
            printLevel(root, level);
            System.out.println();
            level++;
        }
    }

    /**
     * Checks the level of the tree to see if nodes are there
     * @param root the root of the tree
     * @param level the level to check
     * @return if the level contains any nodes
     */
    public static boolean checkLevel(Node root, int level){
        boolean b;
        if (root == null) {
            b = false;
        } else if (level > 1){
            b = (checkLevel(root.left, level -1) ||  checkLevel(root.right, level-1));
        } else {
            b = true;
        }
        return b;
    }

    /**
     * Prints the specified level of the tree
     * @param root the root node
     * @param level the level to print
     */
    public static void printLevel(Node root, int level){

        if (root == null) {
            return;
        } else if (level > 1){
            printLevel(root.left, level -1);
            printLevel(root.right, level-1);
        } else {
            System.out.print(root.value + " ");
        }

    }

    /**
     * Adds a node to the binary search tree
     * @param root the root node
     * @param value the value to add
     */
    public static void addToBST(Node root, int value){

        if(value > root.value){
            if (root.right == null){
                root.right = new Node(value);
            } else {
                addToBST(root.right, value);
            }
        } else {
            if (root.left == null){
                root.left = new Node(value);
            } else {
                addToBST(root.left, value);
            }
        }

    }
}
