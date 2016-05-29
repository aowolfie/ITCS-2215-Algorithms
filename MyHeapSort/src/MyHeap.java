import java.lang.reflect.Array;

/**
 * A generic type min heap class
 * Created by brandonbeckwith on 5/24/16.
 */
public class MyHeap<T extends Comparable> {

    private T[] heap;
    private int size;

    public MyHeap(Class<T> t, T[] array){
        heap = (T[]) Array.newInstance(t, array.length + 1);
        size = 0;

        for (T a: array){
            add(a);
        }
    }

    /**
     * Adds a value to the heap
     * @param value
     */
    public void add(T value){
        int index = size + 1;
        heap[index] = value;

        while (index > 1){
            int nIndex = index / 2;
            if (heap[nIndex].compareTo(value) > 0){
                heap[index] = heap[nIndex];
                heap[nIndex] = value;
                index = nIndex;
            } else {
                break;
            }
        }
        size++;
    }

    /**
     * Removes the value from the top of the heap, the reorders
     * the heap to preserve its integrity
     * @return the next value
     */
    public T remove(){
        T returnValue = heap[1];
        T moveValue = heap[size];
        heap[1] = moveValue;
        heap[size] = null;

        int index = 1;

        while (index * 2 < size){

            int lIndex = index * 2;
            int rIndex = lIndex +1;

            T lValue = heap[lIndex];
            T rValue = null;

            //Check for and assign the right value
            if (rIndex < size) {
                rValue = heap[rIndex];
            }

            //The value that will be compared with the parent
            int mIndex;
            T mValue;

            //Determine if the right or left value is smaller.
            if (rValue == null || lValue.compareTo(rValue) <= 0){
                mValue = lValue;
                mIndex = lIndex;
            } else {
                mValue = rValue;
                mIndex = rIndex;
            }

            //Compare with the parent, if its smaller move it up, if not stop.
            if (moveValue.compareTo(mValue) > 0){
                heap[index] = mValue;
                heap[mIndex] = moveValue;
                index = mIndex;
            } else {
                break;
            }
        }
        size--;
        return returnValue;
    }
}
