import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;

public class Dijkstra {


    public String run(String start, String end,  HashMap<String, graphNode> graph) {
        // data sturctures for state
        HashMap<String, Boolean> vistedNodes = new HashMap<String,Boolean>();
        HashMap<String, Entry> distances = new HashMap<String, Entry>();
        Queue<Entry> pQ = new PriorityQueue<>();
        // entry object contians vertext id and distance
        vistedNodes.clear();
        distances.clear();
        Set< String> keys = graph.keySet();
        for( String key : keys ) {
            distances.put(key, new Entry(key, Double.POSITIVE_INFINITY, key));
            vistedNodes.put(key,  false);
        }

        //adding start node to priority queue
        Entry startEntry = new Entry(start, 0, start);
        pQ.add(startEntry);

        //counter to test efficency 
        int totalNodesChecked = 0;
        while(pQ.size() > 0){
            totalNodesChecked++;
            //remove top value of priority queue
            Entry currentEntry = pQ.poll();

            //get edges and loops throught 
            graphNode currentNode =  graph.get(currentEntry.key);
            ArrayList<edge> edges = currentNode.edges;
            for( edge currentEdge : edges ) {

                // only checking not visited nodes
                if(!vistedNodes.get(currentEdge.destination)){
                    double newDistance = currentEdge.distance + currentEntry.value;
                    double  nextNodeDistance = distances.get(currentEdge.destination).value;
                    double  destinationDistance = distances.get(end).value;
                    // only update distance and queue is distance is smaller and less than current distance to destination
                    if( newDistance <= nextNodeDistance && newDistance < destinationDistance ){
                        Entry newEntry = new Entry(currentEdge.destination, newDistance, currentEntry.key);
                        distances.put(currentEdge.destination, newEntry);

                        // stop adding to queue after reach destination
                        if(distances.get(end).value == Double.POSITIVE_INFINITY){
                            pQ.add(new Entry(currentEdge.destination, newDistance, currentEntry.key));
                        }
                    }
                }
            }   

            vistedNodes.put(currentEntry.key, true);

        }
        
        // contructs string to show path
        Entry currentEntry = distances.get(end);
        String Path = currentEntry.key;
        while(!currentEntry.key.equals(currentEntry.prev) ) {
            Path =  currentEntry.prev+ "->" + Path;
            currentEntry = distances.get(currentEntry.prev);
        }
        Path = Path + " distance of " + distances.get(end).value.toString();
        System.out.println( "nodes traversed:" + totalNodesChecked);
        return Path;
    }


}
