/**
 * Created by brandonbeckwith on 6/3/16.
 *
 * Simple node class required to build the Huffman tree
 */
public class Node implements Comparable {


    private Node left, right;

    private int value;

    private char character;


    public Node(int value){
        this.value = value;
    }

    public Node(int value, Node left, Node right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(int value, char c){
        this.value = value;
        this.character = c;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public boolean hasCharacter(){
        return !(character == 0);
    }

    @Override
    public int compareTo(Object o) {

        if (o.getClass() != this.getClass()){
            return 0;
        }

        Node nodie = (Node) o;

        if (this.getValue() > nodie.getValue()){
            return 1;
        } else if (this.getValue() < nodie.getValue()){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "[" + getCharacter() + "]" + "..." + getValue();
    }
}
