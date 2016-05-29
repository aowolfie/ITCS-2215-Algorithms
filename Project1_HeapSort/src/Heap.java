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

    /**
     * When given a file name this constructor populates and builds
     * a heap based on the contents of the file. The file must be in
     * the same directory of the class or else it will throw an error.
     * @param filename The name of the file to be inputted
     */
    public Heap(String filename) {
        //Use an arrayList to read in data, this makes it easier
        //because we don't know the number of elements yet
        ArrayList<Integer> temp = new ArrayList<Integer>();

        //Read the file and populate the arrayList
        try {
            /*
                NOTE: This is relative, the file has to be in the same directory!
             */
            Scanner scan = new Scanner(Heap.class.getResourceAsStream(filename));
            while(scan.hasNextInt()) {
                temp.add(scan.nextInt());
            }
        } catch (Exception e) {
            System.out.println("The file wasn't found, it probably wasn't in the same directory as the class!");
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
     * @param filename The file to output to
     */
    public void outputResult(String filename) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(filename));

            for (int i=0; i <heap.length; i++){
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
     * @return the heap array
     */
    public int[] getHeap(){
        return heap;
    }

    /**
     * Preforms a heap sort on the array, then
     * marks it as no longer valid
     */
    public void heapSort() {
        int[] rA = new int[heap[0]];
        for (int i=rA.length-1; i >= 0; i--){
            rA[i] = removeMaxElement();
        }
        heap = rA;
    }

    /**
     * Builds the heap by calling heapify for every parent
     */
    public void buildHeap() {
        for (int i=heap[0]/2; i >= 1; i--){
            heapify(i);
        }
    }

    /**
     * Recursive method that is used to construct a heap
     * @param parent the parent node
     */
    private void heapify(int parent) {

        int child = parent * 2;
        int right = child + 1;

        int swap = parent;

        if (child < heap[0] && heap[child] > heap[swap]){
            swap = child;
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
