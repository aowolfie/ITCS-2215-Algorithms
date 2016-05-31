/**
 * Created by brandonbeckwith on 5/28/16.
 *
 * The main class used to test the heap and heap sort
 */
public class Main {

    //Generic main code for the project from Moodle
    public static void main (String[] args){
        Heap myHeap = new Heap("input.txt");

        myHeap.buildHeap();

        myHeap.outputResult("buildheapResult.txt");

        myHeap.removeMaxElement();
        myHeap.removeMaxElement();

        myHeap.addElement(30);

        myHeap.heapSort();

        myHeap.outputResult("afterHeapsort.txt");
    }

}
