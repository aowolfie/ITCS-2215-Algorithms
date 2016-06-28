import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by brandonbeckwith on 6/27/16.
 *
 * A simple implementation of the Floyd-Warshall Algorithm.
 * Most of the class is just used to print out the results and parse the input.
 */
public class Main {

    private static final String FILE_NAME = "inputFW.txt";

    public static void main (String[] args){
        String[] fileLines = null;

        //Read in the file
        try {
            FileReader fReader = new FileReader(FILE_NAME);
            BufferedReader bReader = new BufferedReader(fReader);
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = bReader.readLine()) != null){
                lines.add(line);
            }
            fileLines = lines.toArray(new String[lines.size()]);
        } catch (Exception e){

        }

        //Hashmaps used to store the index of the node and the node based on the index
        HashMap<String, Integer> charToIndex = new HashMap<>();
        HashMap<Integer, String> indexToChar = new HashMap<>();

        int numNodes = 0;

        Arrays.sort(fileLines);
        //Populate the hashtables, these are used to index the Nodes
        for (int i = 0; i < fileLines.length; i++){
            //Check for duplicate entries and adjust accordingly
            if (!charToIndex.containsKey(fileLines[i].substring(0,1))) {
                charToIndex.put(fileLines[i].substring(0, 1), numNodes);
                indexToChar.put(i, fileLines[i].substring(0, 1));
                numNodes++;
            }
        }

        //Check each line for extra characters
        for (String s : fileLines){
            String[] sep = s.split(",");
            for (int i=2; i < sep.length; i+=2){
                if (!charToIndex.containsKey(sep[i])){
                    charToIndex.put(sep[i], numNodes);
                    indexToChar.put(i, sep[i]);
                    numNodes++;
                }
            }
        }


        double[][] distances = new double[numNodes][numNodes];

        //Set the grid to positive infinity
        for (int y=0; y < numNodes; y++) {
            for (int x = 0; x < numNodes; x++) {
                distances[y][x] = Double.POSITIVE_INFINITY;
            }
        }

        //Parse the file and turn it into an adjacency matrix
        for (String s : fileLines){
            String[] sep = s.split(",");
            for (int i=2; i < sep.length; i+=2){
                distances[charToIndex.get(sep[0])][charToIndex.get(sep[i])] = Double.parseDouble(sep[i-1]);
            }
        }

        //Set the node's distance to themselves to 0
        for (int i = 0; i < numNodes; i++){
            distances[i][i]=0;
        }


        System.out.println("Initial Matrix:");

        //This just prints out the grid
        System.out.print("    ");
        for (int i=0; i < numNodes; i++){
            System.out.print(indexToChar.get(i) + "\t");
        }
        System.out.println();
        for (int y=0; y < numNodes; y++) {
            System.out.print(indexToChar.get(y) + ": ");
            for (int x = 0; x < numNodes; x++) {
                double temp = distances[y][x];
                if (temp == Double.POSITIVE_INFINITY){
                    System.out.print("INF" + "\t");
                } else {

                    System.out.print(String.format("%3d", (int) temp) + "\t");
                }
            }
            System.out.println();
        }


        //Divider print
        for (int i=0; i < numNodes + 4; i++){
            System.out.print("===");
        }
        System.out.println();


        //The actual Floyd-Warshall Algorithm
        for (int i=0; i < numNodes; i++){
            for (int y=0; y < numNodes; y++){
                for (int x=0; x< numNodes; x++){
                    if (distances[y][x] > distances[y][i] + distances[i][x]){
                        distances[y][x] = distances[y][i] + distances[i][x];
                    }
                }
            }
        }

        System.out.println("Final Matrix:");

        //This just prints out the grid
        System.out.print("    ");
        for (int i=0; i < numNodes; i++){
            System.out.print(indexToChar.get(i) + "\t");
        }
        System.out.println();
        for (int y=0; y < numNodes; y++) {
            System.out.print(indexToChar.get(y) + ": ");
            for (int x = 0; x < numNodes; x++) {
                double temp = distances[y][x];
                if (temp == Double.POSITIVE_INFINITY){
                    System.out.print("INF" + "\t");
                } else {

                    System.out.print(String.format("%3d", (int) temp) + "\t");
                }
            }
            System.out.println();
        }
    }
}
