import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by brandonbeckwith on 6/3/16.
 *
 */
public class HEncode {

    private String inputString;
    private char[] inputArray;

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

        generateTree(generateFrequencies());

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
    private HashMap<Character, Integer> generateFrequencies(){
        System.out.println("Generating frequencies!");
        HashMap<Character, Integer> cMap = new HashMap<Character, Integer>();

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
        return cMap;
    }

    //Generate the tree required for huffman encoding!
    private Node generateTree(HashMap<Character, Integer> map){

        PriorityQueue<Node> pQueue = new PriorityQueue<Node>();

        for (Character c: map.keySet()){
            pQueue.add(new Node(map.get(c),c));
        }

        while (pQueue.size() > 1){
            Node l = pQueue.poll();
            Node r = pQueue.poll();
            Node temp = new Node(l.getValue() + r.getValue());
            temp.setLeft(l);
            temp.setRight(r);
            pQueue.add(temp);
        }

        printLevelOrder(pQueue.poll());

        return pQueue.poll();
    }



    /**
     * Prints the level order using a queue
     * @param root the root of the tree
     */
    public void printLevelOrder(Node root){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        while (!q.isEmpty()){
            Node tempNode = q.poll();
            System.out.println(tempNode);
            if (tempNode.getLeft() != null) {
                q.add(tempNode.getLeft());
            }
            if (tempNode.getRight() != null) {
                q.add(tempNode.getRight());
            }
        }
    }
}
