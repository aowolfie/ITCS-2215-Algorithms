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
            Node temp = head;
            while (temp.getChild() != null) {
                visited.add(temp);
                temp = temp.getChild();
            }

            while (!visited.isEmpty()) {
                temp = new Node(num);
                linkV(temp, lastChild);
                linkH(temp, visited.remove(visited.size() - 1));
            }

            while (Math.random() > .5D) {
                temp = new Node(num);
                linkV(temp, lastChild);
                lastChild = temp;
                temp = new Node(head.getValue());
                linkV(temp, head);
                linkH(lastChild, temp);
                head = temp;
                temp = new Node(tail.getValue());
                linkV(temp, tail);
                linkH(head, temp);
                tail = temp;
                levels++;
            }


        } else if(true){


        } else {

            while (level > 0) {

                while (current.getRight() != null && current.getRight().getValue() < num) {
                    if (current.getValue() == tail.getValue()){
                        break;
                    }
                    current = current.getRight();
                }

                visited.add(current);
                current = current.getChild();

                level--;
            }
            level++;


            while (!visited.isEmpty()) {
                if (level == 1 || Math.random() > .5D) {
                    current = visited.remove(visited.size() - 1);
                    Node temp = new Node(num);
                    linkV(temp, lastChild);
                    linkH(temp, current.getRight());
                    linkH(current, temp);
                    lastChild = temp;
                } else {
                    break;
                }
            }

            if (visited.isEmpty()) {
                while (Math.random() > 0.5D) {
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
            }
        }
    }

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
        System.out.println("=========================================");
    }

    private void linkV(Node parent, Node child){
        if (parent== null || child == null){
            return;
        }
        parent.setChild(child);
        child.setParent(parent);
    }

    private void linkH(Node left, Node right){

        if (left == null || right == null){
            return;
        }
        left.setRight(right);
        right.setLeft(left);
    }

}
