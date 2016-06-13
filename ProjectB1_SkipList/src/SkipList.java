import java.util.ArrayList;

/**
 * Created by Brandon on 6/11/2016.
 *
 */
public class SkipList {

    private Node head, tail;
    private int levels = 1; //By default the skip list starts with only one level

    //Use the constructor to deal with the first two additions
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

    public void add(int num) {
        int level = levels;

        ArrayList<Node> visited = new ArrayList<>();
        Node current = head;
        Node lastChild = null;

        if (num < head.getValue()) {
            //If the node comes before the rest of the numbers handle it in a special case
            Node temp = head;

            //Add all the head's child nodes
            do {
                visited.add(temp);
                temp = temp.getChild();
            } while (temp != null);

            //Use this to calculate the chance on whether or not to increase the height
            double chance = Math.pow(.5, visited.size());

            //Create and place the new node before each of the head's nodes
            while (!visited.isEmpty()) {
                temp = new Node(num);
                linkV(temp, lastChild);
                linkH(temp, visited.remove(visited.size() - 1));
                if (visited.isEmpty()) {
                    head = temp;
                }
                lastChild=temp;
            }

            //using the chance calculated above generate new layers
            while (Math.random() < chance) {
                temp = new Node(num);
                linkV(temp, head);
                head = temp;
                temp = new Node(tail.getValue());
                linkV(temp, tail);
                tail = temp;
                linkH(head, tail);
                levels++;
            }
        } else {
            //If the node doesn't come before the head we treat it normal

            //While there are still levels find and add the node that comes before our new number
            while (level > 0) {

                while (current.getRight() != null && current.getRight().getValue() < num) {
                    current = current.getRight();
                }

                //Add the node to the array
                visited.add(current);
                current = current.getChild();

                level--;
            }
            level++;

            double chance = Math.pow(.5, visited.size());

            //Once all the nodes have been found, begin populating up the tree
            while (!visited.isEmpty()) {
                if (level == 1 || Math.random() < .50D || num >= tail.getValue()) {
                    current = visited.remove(visited.size() - 1);
                    Node temp = new Node(num);
                    linkV(temp, lastChild);
                    linkH(temp, current.getRight());
                    linkH(current, temp);
                    lastChild = temp;
                    if (tail.getRight() != null){
                        tail = tail.getRight();
                    }
                    level++;
                } else {
                    break;
                }
            }

            if (visited.isEmpty() && num < tail.getValue()) {
                while (Math.random() < chance) {
                    Node temp = new Node(head.getValue());
                    linkV(temp, head);
                    head = temp;
                    temp = new Node(num);
                    linkV(temp, lastChild);
                    linkH(head, temp);
                    lastChild = temp;
                    temp = new Node(tail.getValue());
                    linkV(temp, tail);
                    linkH(lastChild, temp);
                    tail = temp;
                    levels++;
                }
            } else if (visited.isEmpty()){
                while (Math.random() < chance) {
                    Node temp = new Node(head.getValue());
                    linkV(temp, head);
                    head = temp;
                    temp = new Node(tail.getValue());
                    linkV(temp, tail);
                    linkH(head, temp);
                    tail = temp;
                    temp = new Node(num);
                    linkV(temp, lastChild);
                    linkH(tail, temp);
                    lastChild = temp;
                    levels++;
                }
            }
        }
    }

    /**
     * Goes through and prints each level
     */
    public void printLevels(){
        Node temp = head;
        while (temp != null){
            Node temp2 = temp;
            while (temp2 != null){
                System.out.print(temp2 + "-");
                temp2 = temp2.getRight();
            }
            System.out.println();
            temp = temp.getChild();
        }
    }

    private void linkV(Node parent, Node child){
        if (parent== null || child == null){
            return;
        }
        parent.setChild(child);
        child.setParent(parent);
    }

    /**
     * Helper method used to link two horizontal nodes
     * @param left the left node
     * @param right the right node
     */
    private void linkH(Node left, Node right){

        if (left == null || right == null){
            return;
        }
        left.setRight(right);
        right.setLeft(left);
    }

}
