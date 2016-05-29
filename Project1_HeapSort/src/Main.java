/**
 * Created by brandonbeckwith on 5/28/16.
 *
 * The main class used to create and execute the Heap
 */
public class Main {

    //Generic main code for the prject from moodle
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
