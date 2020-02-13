public class HashEntry
{
    private String key;
    private int value; //declarations
    public HashEntry(String k, int v)//constructor
    {
        key = k;
        value = v;
    }
    public String getKey() { return key; }//get key
    public int getValue(){ return value; }//get value
    public int setValue(int val) { int val1 = value; value = val; return val1; }//set value
}
