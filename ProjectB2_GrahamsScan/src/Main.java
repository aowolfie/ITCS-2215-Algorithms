import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by brandonbeckwith on 6/16/16.
 *
 */
public class Main {

    //The file to read from
    private final static String FILE_NAME = "grahamsInput.txt";

    public static void main(String[] args){

        String[] fileLines = null;

        //Read in the file
        try {
            FileReader fReader = new FileReader(FILE_NAME);
            BufferedReader bReader = new BufferedReader(fReader);
            ArrayList<String> lines = new ArrayList<>();
            String line = null;
            while ((line = bReader.readLine()) != null){
                lines.add(line);
            }
            fileLines = lines.toArray(new String[lines.size()]);
        } catch (Exception e){

        }

        ArrayList<PointC> points = new ArrayList<PointC>();

        for (String s: fileLines){
            String[] sep = s.split(",");
            points.add(new PointC(Integer.parseInt(sep[0]), Integer.parseInt(sep[1])));
        }

        ArrayList<PointC> pointsExtra = points;

        Stack<PointC> s = scan(pointsExtra);

        while(!s.isEmpty()){
            System.out.println(s.pop());
        }

    }

    public static Stack<PointC> scan(ArrayList<PointC> list){
        int index = 0;
        for (int i=0; i < list.size(); i++){
            if (list.get(i).getX() < list.get(index).getX()){
                index = i;
            } else if (list.get(i).getX() <= list.get(index).getX()){
                if (list.get(i).getY() < list.get(index).getY()){
                    index = i;
                }
            }
        }

        PointC bL = list.remove(index);

        for (PointC pC: list){
            pC.calculateSlope(bL);
        }


        Stack<PointC> hull = new Stack<>();
        Collections.sort(list);

        hull.add(bL);
        hull.add(list.remove(0));

        while (list.size() > 0){
            PointC currentPoint = list.remove(0);
            while (true){
                PointC previousPoint = hull.pop();
                int rot = hull.peek().orientation(currentPoint, previousPoint);

                if (rot <= 0) {
                    hull.add(previousPoint);
                    hull.add(currentPoint);
                    break;
                }
            }
        }
        return hull;
    }
}
