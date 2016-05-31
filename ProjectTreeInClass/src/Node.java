/**
 * Created by brandonbeckwith on 5/31/16.
 * A simple node class for use in a binary search tree.
 */
public class Node {

    Node left;
    Node right;

    int value;

    /**
     * Default constructor, takes the value of the node
     * @param value the value that is to be assigned to the node
     */
    public Node(int value){
        this.value = value;
    }
}