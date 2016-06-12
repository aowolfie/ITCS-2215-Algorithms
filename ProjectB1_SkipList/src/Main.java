/**
 * Created by Brandon on 6/11/2016.
 */
public class Main {

    public static void main(String args[]){
        SkipList skippy = new SkipList(1,50);

        skippy.add(13);
        skippy.printLevels();
        skippy.add(34);
        skippy.printLevels();
        skippy.add(35);
        skippy.printLevels();
        skippy.add(25);
        skippy.printLevels();
        skippy.add(43);
        skippy.printLevels();
        skippy.add(12);
        skippy.printLevels();
        skippy.add(30);
        skippy.printLevels();
        skippy.add(51);

        skippy.printLevels();


    }
}
