import java.util.ArrayList;

public class FrequencyHashTable
{
    private int count;//count
    private int size;//size
    private final int PRIME_NUM = 10099;//prime factor
    private final int TABLE_CAPACITY = 9973; //capacity
    private final double notfound = 0.001;//return number if word isn't present in the hashtable
    private ArrayList<Bucket> buckets ;

    public FrequencyHashTable()//constructor
    {
        buckets = new ArrayList<>(TABLE_CAPACITY);
        for (int i = 0; i < TABLE_CAPACITY; i++)
        {
            buckets.add(new Bucket());
        }
        size = 0;
        count = 0;
    }

    // increment the current count for this key or insert key
    // with count 1 if it is a new key
    public void increment(String key)
    {
        int getIndex = compressFunc(key);
        buckets.get(getIndex).set(key);
        count++;
    }

    // remove a given word from the table
    public void remove(String key)
    {
        int getIndex = compressFunc(key);
        buckets.get(getIndex).remove(key);
        count--;
    }

    // return the frequency (count / (double) totalCount())
    // for a given Key
    public double get(String key)
    {
        int getIndex = compressFunc(key);
        int get = buckets.get(getIndex).get(key);
        if (totalCount() <= 0) return notfound;
        else return (get / (double)totalCount());
    }

    // are there any keys in the table?
    public boolean isEmpty()
    {
        return size() == 0;
    }

    // how many keys are in the table
    private int size()
    {
        return size;
    }

    // total sum of all the counts
    private int totalCount()
    {
        return count;
    }

    // compression function to adjust for table size
    private int compressFunc(String key)
    {
        return Math.abs(key.hashCode() % PRIME_NUM % buckets.size());
    }


}