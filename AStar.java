import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;

public class AStar {

    public String run(String start, String end,  HashMap<String, graphNode> graph) {
        HashMap<String, Boolean> vistedNodes = new HashMap<String,Boolean>();
        HashMap<String, Entry> distances = new HashMap<String, Entry>();
    
        Queue<AStartEntry> pQ = new PriorityQueue<>();
        // using new Entry object to contain more info for informed search 
        vistedNodes.clear();
        distances.clear();
        Set< String> keys = graph.keySet();
        for( String key : keys ) {
            
            distances.put(key, new Entry(key, Double.POSITIVE_INFINITY, key));
            vistedNodes.put(key,  false);
        }
        //adding start node to priority queue
        AStartEntry startEntry = new AStartEntry(start, 0.0, start, 0.0);
        pQ.add(startEntry);

        //counter to test efficency 
        int totalNodesChecked = 0;
        while(pQ.size() > 0){
           //remove top value of priority queue
            totalNodesChecked++;
            AStartEntry currentEntry = pQ.poll();
            graphNode currentNode =  graph.get(currentEntry.key);
            ArrayList<edge> edges = currentNode.edges;
            for( edge currentEdge : edges ) {
                // only checking not visited nodes
                if(!vistedNodes.get(currentEdge.destination)){
                    double newDistance = currentEdge.distance + currentEntry.value;
                    double  nextNodeDistance = distances.get(currentEdge.destination).value;
                    double  destinationDistance = distances.get(end).value;
                    // only update distance and queue is distance is smaller and less than current distance to destination
                    if( newDistance <= nextNodeDistance && newDistance <destinationDistance ){

                        // get new grid distance value for inform prioritization
                        double squareDist = gridDistanceToEnd(graph.get(currentEdge.destination).square, graph.get(end).square);
                        distances.put(currentEdge.destination, new Entry(currentEdge.destination, newDistance, currentEntry.key) );
                        AStartEntry newEntry = new AStartEntry(currentEdge.destination, newDistance, currentEntry.key, squareDist);
                        // stop adding to queue after reach destination
                        if(distances.get(end).value == Double.POSITIVE_INFINITY){
                            pQ.add(newEntry);
                        }
                    }
                }
            }   

            vistedNodes.put(currentEntry.key, true);

        }
        
        Entry currentEntry = distances.get(end);
        String Path = currentEntry.key;
        while(!currentEntry.key.equals(currentEntry.prev) ) {
            Path =  currentEntry.prev+ "->" + Path;
            currentEntry = distances.get(currentEntry.prev);
        }
        Path = Path + " distance of " + distances.get(end).value.toString();
        System.out.println( "nodes visited:" + totalNodesChecked);
        return Path;
    }

    //convert square number to x and y coords 
    // then taking euclidean distance
    public double gridDistanceToEnd(double currentSquare, double EndSquare){
        double x1 = (currentSquare % 10) * 100;
        double y1 = Math.floor(currentSquare / 10) * 100;

        double x2 = (EndSquare % 10) * 100;
        double y2 = Math.floor(EndSquare / 10) * 100;
        return Math.sqrt( Math.pow( x2 - x1 , 2) + Math.pow( y2 - y1 , 2));
    }

}
