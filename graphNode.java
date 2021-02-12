import java.util.ArrayList;

public class graphNode {
    public String vertex; // vertex id keeping as string to use in hash map
    public int square; // square the vertex is in
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