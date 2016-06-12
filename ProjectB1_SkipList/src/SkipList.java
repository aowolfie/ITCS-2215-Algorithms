import java.util.ArrayList;

/**
 * Created by Brandon on 6/11/2016.
 *
 */
public class SkipList {

    Node head, tail;

    public SkipList(int num1, int num2){
        if (num1 < num2){
            head = new Node(num1);
            tail = new Node(num2);
        } else {
            head = new Node(num2);
            tail = new Node(num1);
        }
        linkH(head, tail);
    }

    public void add(int num){

    }

    private void linkV(Node parent, Node child){
        parent.setChild(child);
        child.setParent(parent);
    }

    private void linkH(Node left, Node right){
        left.setRight(right);
        right.setLeft(left);
    }

}
