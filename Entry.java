public class Entry implements Comparable<Entry>  {
    public String key;
    public String prev;
    public Double value;
    public Entry(String Key, double Value, String Prev){
        key = Key;
        prev = Prev;
        value = Value;
    }
    
    @Override
    public int compareTo(Entry other) {
        if (value > other.value) {
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
