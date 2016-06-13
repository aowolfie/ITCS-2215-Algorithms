/**
 * Created by brandonbeckwith on 6/13/16.
 */
public class Edge implements Comparable<Edge>{
    private String node1, node2;

    private int weight;

    public Edge(String node1, int weight, String node2) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public String getNode1() {
        return node1;
    }

    public String getNode2() {
        return node2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return node1 + "--" + weight + "--" + node2;
    }


    @Override
    public int compareTo(Edge e) {
        if (e.getWeight() == this.getWeight()){
            return 0;
        } else if (this.getWeight() < e.getWeight()) {
            return -1;
        } else {
            return 0;
        }
    }
}
