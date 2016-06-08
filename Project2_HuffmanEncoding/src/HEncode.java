import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by brandonbeckwith on 6/3/16.
 *
 */
public class HEncode {

    private char[] inputArray;
    private HashMap<Character, String> codes = new HashMap<Character, String>();

    private final String HEAD_ID =  "--blueHuffmanEncode--";
    private final String OPEN_ID =  "--[[";
    private final String CLOSE_ID = "]]--";

    private boolean decode = false;

    public HEncode (String input, String fileName){
        inputArray = input.toCharArray();

        Node root = generateTree(generateFrequencies());

        generateCodes(root, codes, "");

        byte[] temp = encode();
        /*
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
            for (char c: tC) {
                out.writeBoolean(c == '1');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        System.out.println(temp);
        System.out.println("Done");
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

        while (pQueue.size() > 1) {
            Node l = pQueue.poll();
            Node r = pQueue.poll();
            Node temp = new Node(l.getValue() + r.getValue());
            temp.setLeft(l);
            temp.setRight(r);
            pQueue.add(temp);
        }

        return pQueue.poll();
    }


    private void generateCodes(Node node, HashMap<Character, String> map, String string){
        System.out.println();
        if (node.getRight() == null && node.getLeft() == null){
            map.put(node.getCharacter(), string);
            return;
        }

        generateCodes(node.getLeft(), map, string + "0");
        generateCodes(node.getRight(), map, string + "1");
    }

    private byte[] encode(){
        String s = "";

        for (char c: inputArray){
            s += codes.get(c);
        }

        int size = s.length();
        int index = 0;

        byte[] bytes = new byte[(size + 7)];

        while ((index+1) *8 < size){

            String temp = s.substring(index * 8, (index+1) * 8);
            bytes[index] = (byte) (Integer.parseInt(temp,2) - 128);
            System.out.println(bytes[index]);
            index++;

        }

        if (size%7 != 0){
            String temp = s.substring(index * 8, size);
            bytes[index] = (Byte.parseByte(temp, 2));
        }

        byte b1 = bytes[index];
        String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
        System.out.println(s1);

        return bytes;
    }
}
