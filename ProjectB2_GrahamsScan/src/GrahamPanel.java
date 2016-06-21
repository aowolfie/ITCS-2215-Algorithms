import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by brandonbeckwith on 6/21/16.
 *
 */
public class GrahamPanel extends JPanel {

    private PointC[] allPoints;
    private PointC[] hull;

    public GrahamPanel(PointC[] allPoints, PointC[] hull){
        this.allPoints = allPoints;
        this.hull = hull;
    }

    private static final float RATIO = 2.25f;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setSize(500, 500);

        //Draw our axis
        g.setColor(Color.RED);
        g.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
        g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);

        g.setColor(Color.BLACK);
        int circleSize = (int) (((this.getHeight() + this.getHeight()) / 2) * .005);

        for (PointC p: allPoints){
            g.fillOval((int) ( RATIO * (p.getX() + 110)), (int) (RATIO * (p.getY() + 110)), circleSize, circleSize);
        }

        g.setColor(Color.green);
        for (int i=0; i < hull.length; i++){
            if (i == hull.length - 1){
                g.drawLine((int) ( RATIO * (hull[0].getX() + 110)) , (int) ( RATIO * (hull[0].getY() + 110)) , (int) ( RATIO * (hull[i].getX() + 110)) , (int)( RATIO * (hull[i].getY() + 110)) );
            } else {
                g.drawLine((int) ( RATIO * (hull[i].getX() + 110)) , (int) ( RATIO * (hull[i].getY() + 110)) , (int) ( RATIO * (hull[i+1].getX() + 110)) , (int)( RATIO * (hull[i+1].getY() + 110)) );
            }
        }
    }
}
