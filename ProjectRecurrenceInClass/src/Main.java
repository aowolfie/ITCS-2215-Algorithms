/**
 * Created by brandonbeckwith on 6/15/16.
 */
public class Main {

    public static void main(String[] args){
        System.out.println(goodFib(45));
    }

    public static int fib(int num){
        if (num ==1 || num == 2){
            return 1;
        }

        return fib(num-1) + fib(num -2);
    }

    public static int goodFib(int num){
        int num1 = 1;
        int num2 = 1;
        for (int i=2; i < num; i++){
            if (i%2 == 0){
                num1 = num1 + num2;
            } else {
                num2 = num1 + num2;
            }
        }
        return (num1 > num2? num1: num2);
    }
}
