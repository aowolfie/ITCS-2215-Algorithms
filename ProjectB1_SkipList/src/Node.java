/**
 * Created by Brandon on 6/11/2016.
 *
 * Simple node class used by the skiplist.
 */
public class Node {

    private Node right, child, parent, left;
    private int value;

    public Node (int value){
        this.value = value;
    }

    public Node (int value, Node right){
        this.value = value;
        this.right = right;
    }

    public Node (int value, Node right, Node child){
        this.value = value;
        this.right = right;
        this.child = child;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + this.getValue() + "]";
    }
}
