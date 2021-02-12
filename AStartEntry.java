public class AStartEntry implements Comparable<AStartEntry>  {
    public String key;
    public String prev;
    public Double value;
    public Double gridDistance;

    //value for debugging
    private Double sortValue;
    public AStartEntry(String Key, double Value, String Prev, Double GridDistance){
        key = Key;
        prev = Prev;
        gridDistance = GridDistance;
        value = Value;
        sortValue =gridDistance + value;
    }
    
    @Override
    public int compareTo(AStartEntry other) {
        if (sortValue > other.sortValue) {
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public String toString(){
        return String.format("{ key : %s , value: %s , prev: %s}", key, value, prev);
    }

}
