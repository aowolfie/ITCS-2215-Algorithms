import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by brandonbeckwith on 5/28/16.
 *
 * A Heap Class that can heap sort
 */
public class Heap {

    private int[] heap;
    private boolean valid = false; //Used to exclude the size stored at heap[0]

    /**
     * When given a file name this constructor populates and builds
     * a heap based on the contents of the file. The file must be in
     * the same directory, or subdirectory with a relative path,
     * of the class or else it will throw an error.
     *
     * @param filename the name of the file to be inputted
     */
    public Heap(String filename) {
        /*
          Use an arrayList to read in data, this makes it easier
          because we don't know the number of elements yet
        */
        ArrayList<Integer> temp = new ArrayList<Integer>();

        //Read the file and populate the arrayList
        try {
            /*
                NOTE: This is a relative path
             */
            Scanner scan = new Scanner(Heap.class.getResourceAsStream(filename));
            while(scan.hasNextInt()) {
                temp.add(scan.nextInt());
            }
        } catch (Exception e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        //Take the arrayList and build our heap array
        heap = new int[temp.size() + 1];
        heap[0] = temp.size();
        for (int i=0; i < temp.size(); i++){
            heap[i+1] = temp.get(i);
        }

        //Finally make the heap valid by building it
        buildHeap();
    }

    /**
     * Outputs the contents of the array starting at index 0
     * @param filename the file to output to
     */
    public void outputResult(String filename) {
        try {

            PrintStream out = new PrintStream(new FileOutputStream(filename));

            //If the heap is valid, exclude the size stored at 0
            for (int i=(valid? 1: 0); i <heap.length; i++){
                out.println(heap[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Recursive function, switches a child with a parent if the child
     * is larger. Used by the add method
     * @param indexOfNode the index of the child node
     */
    private void siftUp(int indexOfNode) {
        int parent = indexOfNode/2;

        if (parent > 0 && heap[indexOfNode] > heap[parent]){
            exchange(indexOfNode, parent);
            siftUp(parent);
        }
    }

    /**
     * Removes the maximum element and preserves the heap
     * @return the maximum element
     */
    public int removeMaxElement() {
        int rV = heap[1];
        heap[1] = heap[heap[0]];
        heap[0]--;
        heapify(1);
        return rV;
    }

    /**
     * Adds an element to the heap
     * @param value the value to add to the heap
     */
    public void addElement(int value) {
        heap[0]++;
        heap[heap[0]] = value;
        siftUp(heap[0]);
    }

    /**
     * Performs a heap sort on the array
     */
    public void heapSort() {
        int[] rA = new int[heap[0]];
        for (int i=rA.length-1; i >= 0; i--){
            rA[i] = removeMaxElement();
        }
        heap = rA;
        valid = false;
    }

    /**
     * Builds the heap by calling heapify for every parent
     */
    public void buildHeap() {
        for (int i=heap[0]/2; i >= 1; i--){
            heapify(i);
        }
        valid = true;
    }

    /**
     * Recursive method that is used to construct a heap
     * @param parent the parent node
     */
    private void heapify(int parent) {

        int left = parent * 2;
        int right = left + 1;

        int swap = parent;

        if (left < heap[0] && heap[left] > heap[swap]){
            swap = left;
        }

        if (right < heap[0] && heap[right] > heap[swap]){
            swap = right;
        }

        if (swap != parent){
            exchange(parent, swap);
            heapify(swap);
        }
    }

    /**
     * Takes two indices and swaps their values
     * @param f the first index
     * @param s the second index
     */
    private void exchange(int f,int s){
        int temp = heap[f];
        heap[f] = heap[s];
        heap[s] = temp;
    }
}
