import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by brandonbeckwith on 6/28/16.
 *
 */
public class Main {

    private static final String FILE_NAME = "inputMatrix.txt";

    public static void main(String[] args){

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

        /*
            Since each adjacent matrix has to share an edge we can stick them in
            an array next to each other.
            EX: 5x5 5x10 10x5 5x15 -> [5][5][10][5][15]
         */

        int[] m = new int[fileLines.length + 1];

        //Populate our array
        for (int i = 0; i < fileLines.length; i++){
            String[] sep = fileLines[i].split(" ");
            m[i] = Integer.parseInt(sep[0]);
            if (i == fileLines.length - 1){
                m[i + 1] = Integer.parseInt(sep[1]);
            }
        }

        int[][] cost = new int[fileLines.length][fileLines.length];

        for (int i=0; i < cost.length; i++){
            cost[i][i] = 0;
        }

        printOut(cost);


    }


    public static void printOut(int[][] cost){
        for (int[] a: cost){
            for (int i: a){
                System.out.print(String.format("%6d", i) + "\t");
            }
            System.out.println();
        }
        System.out.println("========================================================");
    }
}
