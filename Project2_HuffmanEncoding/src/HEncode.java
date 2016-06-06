import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by brandonbeckwith on 6/3/16.
 *
 */
public class HEncode {

    private String inputString;
    private HashMap<Character, Integer> cMap = new HashMap<Character, Integer>();
    private char[] inputArray;
    private Node[] nodes;

    private final String HEAD_ID =  "--blueHuffmanEncode--";
    private final String OPEN_ID =  "--[[";
    private final String CLOSE_ID = "]]--";

    private boolean decode = false;

    public HEncode (String string){
        inputString = string;
        inputArray = string.toCharArray();
        decode = checkFile();
        if (decode){
            return;
        }
        generateFrequencies();
        nodes = generateNodeArray();
        generateTree();
        System.out.println("Done!");
    }

    /**
     * Checks if the file contains our header, this means to unencode;
     * @return
     */
    private boolean checkFile(){
        return inputString.contains(HEAD_ID);
    }

    /**
     * Calculates the frequencies used in our Huffman Encoding
     */
    private void generateFrequencies(){
        System.out.println("Generating frequencies!");

        for (int i=0; i < inputArray.length; i++){
            if (cMap.containsKey(inputArray[i])) {
                cMap.put(inputArray[i], cMap.get(inputArray[i]) + 1);
            } else {
                cMap.put(inputArray[i], 1);
            }
        }

        System.out.println("Done, printing frequencies!");

        for (char c: cMap.keySet()){
            int i = cMap.get(c);
            System.out.println("[" + c + "]" + "...." + i);
        }
    }

    /**
     * Generates a node arraylist, then converts it to an array
     * @return a node array;
     */
    private Node[] generateNodeArray(){
        System.out.println("Generating the node array");
        LinkedList<Node> nodeList = new LinkedList<Node>();
        for (char c: cMap.keySet()){
            nodeList.add(new Node(cMap.get(c),c));
        }
        System.out.println("Done!");

        for (Node n: nodeList){
            System.out.println(n);
        }
        return nodeList.toArray(new Node[nodeList.size()]);
    }

    //Generate the tree required for huffman encoding!
    private void generateTree(){

        //record the initial length
        int size = nodes.length;

        while (size > 1) {
            int index1, index2;
            index1 = 0;
            index2 = 1;

            for (int i=0; i < size; i++){
                if (nodes[index1].getValue() > nodes[index2].getValue() && nodes[index1].getValue() > nodes[i].getValue()){
                    index1 = i;
                } else if (nodes[index2].getValue() > nodes[index2].getValue() && nodes[index1].getValue() > nodes[i].getValue()){
                    index2 = i;
                }
            }

            //Create a new node with both values, and assign its children
            Node temp = new Node(nodes[index1].getValue() + nodes[index2].getValue());
            temp.setLeft(nodes[index1]);
            temp.setRight(nodes[index2]);


             if (index1 > index2){
                nodes[index2] = temp;
                exchange(index1, size-1);
                nodes[size-1] = null;
            } else {
                nodes[index1] = temp;
                exchange(index2, size-1);
                nodes[size-1] = null;
            }
            size--;
        }
    }

    private void exchange(int f,int s){
        Node temp = nodes[f];
        nodes[f] = nodes[s];
        nodes[s] = temp;
    }
}
