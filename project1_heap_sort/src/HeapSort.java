import java.util.Random;

/**
 * Created by brandonbeckwith on 5/24/16.
 */
public final class HeapSort {

    private static int size = 100000;


    public static void main (String[] args) {
        Integer[] ints = new Integer[size];
        Random rand = new Random();
        for (int i=0; i < ints.length; i++){
            ints[i] = rand.nextInt(size);
        }

        HeapSort.sortInteger(ints);
        for (Integer val: ints){
            System.out.println(val);
        }

    }

    public static Integer[] sortInteger(Integer[] array){
        MyHeap heap = new MyHeap(Integer.class, array);

        for (int i = 0; i < array.length; i++) {
            array[i] = (Integer) heap.remove();
        }

        return array;
    }
}
