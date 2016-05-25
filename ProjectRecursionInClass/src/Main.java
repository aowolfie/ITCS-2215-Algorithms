/**
 * Created by brandonbeckwith on 5/25/16.
 */
public class Main {
    public static void main(String[] args){
        System.out.println(fib(6));
    }

    /* This is an example of when to not use
        recursion, because of the sheer number of
        recursive calls that have to be made.
     */
    private static int fib(int number){
        if( number == 0 || number == 1){
            return 1;
        } else {
            return fib(number - 1) + fib(number-2);
        }
    }

    private static int fact(int number){
        if (number == 0){
            return 1;
        } else {
            return number * fact(number -1);
        }
    }

    private static void printHello(int number){
        if (number <= 0){
            return;
        }
        System.out.println("Hello: " + number);
        printHello(number-1);
    }
}
