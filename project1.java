import java.util.HashMap;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;

public class project1 {

  public static String Start;
  public static String Destination;
  public static HashMap<String, graphNode> graph;

  public static void main(String[] args) {

    // will model our graph as hash map with graphNode and edge objects
    System.out.println("Ingesting file!");
    fileIngest();
    System.out.print("our graph has a size of ");
    System.out.println(graph.size());
    System.out.println(String.format("finding shortest path from %s tp %s", Start, Destination));

    System.out.println("running Dijkstra");
    Dijkstra dijkstra = new Dijkstra();
    String path = dijkstra.run(Start, Destination, graph);
    System.out.println(path);
    System.out.println("running A*");
    AStar astar = new AStar();
    String path2 = astar.run(Start, Destination, graph);
    System.out.println(path2);
  }

  public static void fileIngest() {
    graph = new HashMap<String, graphNode>();
    try {
      boolean Vertices = true;
      boolean SourceDest = false;
      File myObj = new File("p1_graph.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        
        if (line.length() != 0) {
          if (!SourceDest) {
            if (line.equals("# Edges")) {
              Vertices = false;
            }
            if (line.equals("# Source and Dest")) {
              SourceDest = true;
            }
            if (line.charAt(0) != '#') {
              if (Vertices) {
                String[] values = line.split(",");
                
                graphNode node = new graphNode(values[0], Integer.parseInt(values[1]));
                graph.put(values[0], node);
              } else {
                
                String[] values = line.split(",");
                graph.get(values[0]).addEdge(values[1], Double.parseDouble(values[2]));
                graph.get(values[1]).addEdge(values[0], Double.parseDouble(values[2]));
              }
            }
          } else {
            if (line.charAt(0) != '#') {
              String[] values = line.split(",");
              if (values[0].equals("S")) {
                Start = values[1];
              } else if (values[0].equals("D")) {
                Destination = values[1];
              }
            }
          }
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }

}