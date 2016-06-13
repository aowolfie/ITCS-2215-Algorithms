import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Brandon on 6/11/2016.
 */
public class Main {

    public static void main(String args[]){

        ArrayList<Integer> temp = new ArrayList<Integer>();

        //Read the file and populate the arrayList
        try {
            /*
                NOTE: This is a relative path
             */
            Scanner scan = new Scanner(new File("numInput.txt"));
            while(scan.hasNextInt()) {
                temp.add(scan.nextInt());
            }
        } catch (Exception e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        SkipList skippy = new SkipList(temp.remove(1), temp.remove(1));

        for (int i: temp){
            skippy.add(i);
        }

        skippy.printLevels();


    }
}
