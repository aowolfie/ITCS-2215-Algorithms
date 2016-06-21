import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by brandonbeckwith on 6/16/16.
 *
 */
public class Main {



    public static Stack<PointC> scan(ArrayList<PointC> list){
        Stack<PointC> hull = new Stack<>();
        Collections.sort(list);

        while (list.size() > 0){
            PointC currentPoint = list.remove(0);
            while (true){
                PointC previousPoint = hull.pop();
            }
        }
        return hull;
    }

    public static int orientation(PointC p1, PointC p2){
        double val1 = p1.getY() * p2.getX();
        double val2 = p1.getX() * p2.getY();

        if (val1 < val2){
            return -1; //Counter clockwise
        } else if (val1 > val2){
            return 1; //Clockwise
        } else {
            return 0; // Parallel
        }
    }
}
