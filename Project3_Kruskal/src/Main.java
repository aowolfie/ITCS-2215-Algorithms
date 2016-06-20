import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by brandonbeckwith on 6/13/16.
 *
 */
public class Main {

    private final static String FILE_NAME = "kruskalInput.txt";

    public static void main(String[] args){

        String[] fileLines = null;
        int totalWeight = 0;

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



        ArrayList<Edge> edges = new ArrayList<>();
        HashMap<String, Boolean> visited = new HashMap<>();

        /*
            This is used to keep track of the each node's parent.
            The child is the key.
         */
        HashMap<String, String> parents = new HashMap<>();

        for (String s: fileLines){
            String[] seperated = s.split(",");

            String first = seperated[0];
            parents.put(first, first);

            for (int i=2; i < seperated.length; i+=2){
                if (!visited.containsKey(seperated[i])) {
                    edges.add(new Edge(first, Integer.parseInt(seperated[i-1]), seperated[i]));
                }
            }

            visited.put(first, true);
        }

        Collections.sort(edges);

        for (Edge e: edges){
            System.out.println(e);
        }

        System.out.println("[==========================]");

        //Loop through the sorted edges and begin populating the minimum spanning tree.
        //Make sure no cycles form.
        for (int i=0; i < edges.size(); i++){
            Edge currentEdge = edges.get(i);
            ParentInfo pI1 = findLastParent(currentEdge.getNode1(), parents);
            ParentInfo pI2 = findLastParent(currentEdge.getNode2(), parents);
            if (!pI1.parent.equals(pI2.parent)){
                //Add edge to MST
                //Update the hashmap (parent array)

                System.out.println(currentEdge + "\n --------------------------");
                totalWeight += currentEdge.getWeight();

                //Join the smaller node chain to the larger one
                if (pI1.count < pI2.count) {
                    parents.replace(currentEdge.getNode1(), currentEdge.getNode2());
                } else {
                    parents.replace(currentEdge.getNode2(), currentEdge.getNode1());
                }
            }
        }

        System.out.println("Total Weight: " + totalWeight);

        System.out.println("[==========================]");

        /*
        for (String e: parents.keySet()){
            System.out.println(e + "-" + parents.get(e));
        }
        */

    }

    /**
     * Find the last parent
     * @param node the node you wish to find the last parent for
     * @param parents the hashmap containing the parents
     * @return ParentInfo, which contains the number of nodes to the last parent and the last parent
     */
    public static ParentInfo findLastParent(String node, HashMap<String, String> parents){
        ParentInfo pI = new ParentInfo();
        while (!node.equals(parents.get(node))){
            node = parents.get(node);
            pI.count++;
        }
        pI.parent = node;
        return pI;
    }
}
