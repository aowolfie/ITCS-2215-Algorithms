import java.awt.*;

/**
 * Created by brandonbeckwith on 6/20/16.
 *
 * Just extended Point and implemented the comparable interface
 */
public class PointC extends Point implements Comparable<PointC> {

    public int compareTo(PointC point) {
        if (this.getX() > point.getX() ){
            return 1;
        } else if (this.getX() < point.getX()){
            return -1;
        }
        return 0;
    }
}
