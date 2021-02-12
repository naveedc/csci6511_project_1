import java.util.ArrayList;

public class graphNode {
    public String vertex;
    public int square;
    public ArrayList<edge> edges = new ArrayList<edge>();

    public graphNode(String Vertex, int Square){
        vertex = Vertex;
        square= Square;
    }

    public void addEdge(String destination, double distance) {
        edge newEdge = new edge(destination, distance);
        edges.add(newEdge);
    }
}