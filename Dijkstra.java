import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;

public class Dijkstra {


    public String run(String start, String end,  HashMap<String, graphNode> graph) {
        HashMap<String, Boolean> vistedNodes = new HashMap<String,Boolean>();
        HashMap<String, Entry> distances = new HashMap<String, Entry>();
    
        Queue<Entry> pQ = new PriorityQueue<>();
        vistedNodes.clear();
        distances.clear();
        Set< String> keys = graph.keySet();
        for( String key : keys ) {
            distances.put(key, new Entry(key, Double.POSITIVE_INFINITY, key));
            vistedNodes.put(key,  false);
        }
        Entry startEntry = new Entry(start, 0, start);
        pQ.add(startEntry);

        int totalNodesChecked = 0;
        while(pQ.size() > 0){
            totalNodesChecked++;
            Entry currentEntry = pQ.poll();
            graphNode currentNode =  graph.get(currentEntry.key);
            ArrayList<edge> edges = currentNode.edges;
            for( edge currentEdge : edges ) {

                if(!vistedNodes.get(currentEdge.destination)){
                    double newDistance = currentEdge.distance + currentEntry.value;
                    double  nextNodeDistance = distances.get(currentEdge.destination).value;
                    double  destinationDistance = distances.get(end).value;
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
