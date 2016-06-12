import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by brandonbeckwith on 6/3/16.
 * This class handles text file and encodes or decodes
 * them based on their file extention.
 *
 * If the file is a normal .txt file it will be encoded
 * using Huffman Encoding. Then the encoding will be written to a
 * .hec file as bytes. A .data file is used to store the codes
 * required to decode.
 *
 * If a file ending in .hec or .data is given it will try to locate
 * the file's partner and decode the file. The unencoded file will
 * be saved as a .txt file.
 */
public class HEncode {

    /**
     * Decodes or encodes the file based on its extension.
     * @param fileName
     */
    public static void handleFile(String fileName){
        if (fileName.contains(".data") || fileName.contains(".hec")) {
            decodeFile(fileName.substring(0, fileName.indexOf('.')));
        } else if (fileName.contains(".txt")){
            encodeFile(fileName);
        }
    }


    /**
     * Given a file name, produces the huffman encoded file with
     * an extra file used to store the huffman codes
     *
     * @param name The name of the file to encode with the appropriate endings
     */
    private static void encodeFile(String name){
        char[] inputArray = null;

        System.out.println("Beginning encoding!");
        try {
            inputArray = readUnencodedFile(name).toCharArray();
        } catch (Exception e) {
            System.out.println("File not readable, stopping encoding process.");
            return;
        }

        Node root = generateTree(generateFrequencies(inputArray));
        HashMap<Character, String> codes = new HashMap<Character, String>();

        generateCodes(root, codes, "");
        byte[] encodedString = encodeArray(inputArray,codes);
        writeEncodedToFile(name.substring(0,name.length()-4), codes, encodedString);

        System.out.println("Done");
    }

    /**
     * Decodes a given file, used the .data to build a hashmap of huffman codes
     * and parses the .hec file to a binary string, which is then decoded.
     * @param name The name of the file to decode without endings
     */
    private static void decodeFile(String name){
        System.out.println("Beginning Decoding!");
        byte[] bytes;
        String rawCodes;
        try{
            bytes = readEncodedFile(name + ".hec");
            rawCodes = readUnencodedFile(name + ".data");
        } catch (Exception e){
            System.out.println("Error reading in file");
            return;
        }
        HashMap<String, Character> codes = buildCodex(rawCodes);
        writeUnencodedToFile(name, decodeString(byteArrayToBinaryString(bytes), codes));
        System.out.println("Done!");
    }


    /**
     * Calculates the frequencies used in our Huffman Encoding
     */
    private static HashMap<Character, Integer> generateFrequencies(char[] inputArray){
        System.out.println("Generating frequencies!");
        HashMap<Character, Integer> cMap = new HashMap<Character, Integer>();

        for (int i=0; i < inputArray.length; i++){
            if (cMap.containsKey(inputArray[i])) {
                cMap.put(inputArray[i], cMap.get(inputArray[i]) + 1);
            } else {
                cMap.put(inputArray[i], 1);
            }
        }

        System.out.println("Done!");

        return cMap;
    }


    /**
     * Generates the Huffman Tree
     * @param map The hashmap that contains the characters
     * @return the top of the tree
     */
    private static Node generateTree(HashMap<Character, Integer> map){
        PriorityQueue<Node> pQueue = new PriorityQueue<Node>();

        for (Character c: map.keySet()){
            pQueue.add(new Node(map.get(c),c));
        }

        while (pQueue.size() > 1) {
            Node l = pQueue.poll();
            Node r = pQueue.poll();
            Node temp = new Node(l.getValue() + r.getValue(), l, r);
            pQueue.add(temp);
        }

        return pQueue.poll();
    }


    /**
     * Uses recursion to generate the Huffman Codes
     * @param node The node which yields the next two children
     * @param map The map used to store the proper character with its string code
     * @param string The current string code
     */
    private static void generateCodes(Node node, HashMap<Character, String> map, String string){
        if (node.getRight() == null && node.getLeft() == null){
            System.out.println(node + "       <" + string + ">");
            map.put(node.getCharacter(), string);
            return;
        }

        generateCodes(node.getLeft(), map, string + "0");
        generateCodes(node.getRight(), map, string + "1");
    }


    /**
     * Encodes a character array to a byte array using huffman encoding
     * @param inputArray A character array
     * @param codes The huffman codes
     * @return a byte array containing the encoded string
     */
    private static byte[] encodeArray(char[] inputArray, HashMap<Character, String> codes){
        String s = "";

        for (char c: inputArray){
            s += codes.get(c);
        }

        int saved = inputArray.length - ((s.length() + 8)/7);
        System.out.println("By encoding we saved " + saved + " bytes without including the codes required to decode!");

        int size = s.length();
        int index = 0;

        byte[] bytes = new byte[(size + 15)/8];

        while ((index+1) * 8 < size){
            String temp = s.substring(index * 8, (index+1) * 8);
            bytes[index] = (byte) (Integer.parseInt(temp,2) - 128);
            index++;
        }

        if (size%8 != 0){
            String temp = s.substring(index * 8, size);
            bytes[index] = (byte) ((Integer.parseInt(temp,2) << (8 - (size%8))) - 128);
            bytes[index+1] = (byte) (8 - (size%8));
        } else {
            bytes[index] = 0;
            bytes[index + 1] = 0;
        }

        return bytes;
    }

    /**
     * Used to write the encoded byte array and codes to two files.
     * Codes are written to a .data file and the byte array is written
     * to a .hec file
     *
     * @param name The name of the file to write too
     * @param codes The huffman codes
     * @param bytes The encoded byte array
     */
    private static void writeEncodedToFile(String name, HashMap<Character, String> codes, byte[] bytes){
        try {

            //Write the characters to the .data file
            PrintStream print = new PrintStream(name + ".data");
            print.print('^');
            for (char c: codes.keySet()){
                print.print(c + codes.get(c) + "^");
            }
            print.flush();
            print.close();

            //Write the byte array to a .hec file
            DataOutputStream out = new DataOutputStream(new FileOutputStream(name + ".hec"));
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds the huffman hashmap from the .data file contents
     * @param rawCodes The raw string of huffman codes
     * @return A hashmap of codes and characters
     */
    private static HashMap<String, Character> buildCodex(String rawCodes){
        HashMap<String, Character>  codes = new HashMap<String, Character>();
        int index = 0;
        int size = rawCodes.length();
        while (index < size - 1){
            char c = rawCodes.charAt(index + 1);
            String code = rawCodes.substring(index + 2, rawCodes.indexOf('^', index + 1));
            index = rawCodes.indexOf('^', index + 1);
            System.out.println(c + ".....<" + code + ">");
            codes.put(code, c);
        }
        return codes;
    }

    /**
     * Converts a byte array to the binary string representation of it
     * @param bytes the byte array to convert
     * @return a binary string
     */
    private static String byteArrayToBinaryString(byte[] bytes){
        String s = "";

        for (int i=0; i < bytes.length - 1; i++){
            String temp = String.format("%8s", Integer.toBinaryString((bytes[i] + 128) & 0xFF)).replace(' ', '0');

            if (i == bytes.length - 2){
                temp = temp.substring(0, bytes[i + 1]);
            }

            s += temp;
        }
        return s;
    }

    /**
     * Decodes a binary string given a hasmap of codes
     * @param string the string to decode
     * @param codes The hashmap of codes
     * @return unencoded text
     */
    private static String decodeString(String string, HashMap<String, Character> codes){
        String decoded = "";
        int index = 0;
        int size = string.length();
        String temp = "";

        while (index < size-1){
            temp += string.charAt(index);

            if (codes.containsKey(temp)){
                decoded += codes.get(temp);
                temp = "";
            }
            index++;
        }

        return decoded;
    }

    /**
     * Writes an unencoded string to a file.
     * @param name the name of the file
     * @param string the string to write
     */
    private static void writeUnencodedToFile(String name, String string){
        try{
            PrintStream stream = new PrintStream("unencoded_"+ name + ".txt");
            stream.print(string);
        } catch (Exception e){

        }
    }

    /**
     * Reads unencoded files
     * @param name the name of the file
     * @return the contents of the file as a string
     * @throws Exception if the file cannot be opened or read
     */
    private static String readUnencodedFile(String name) throws Exception {
        File file = new File(name);
        FileInputStream stream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        stream.read(data);
        stream.close();
        return new String(data, "UTF-8");
    }

    /**
     * Reads a file as a byte array
     * @param name the name of the file
     * @return the contents of the file as a byte array
     * @throws Exception if the file cannot be opened or read
     */
    private static byte[] readEncodedFile(String name) throws Exception {
        return Files.readAllBytes(Paths.get(name));
    }
}
