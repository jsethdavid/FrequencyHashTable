import java.util.ArrayList;

public class Bucket
{
    private ArrayList<HashEntry> mapBucket ;

    public Bucket()// constructor
    {
        mapBucket = new ArrayList<>();
    }

    public int get(String key)//get value from index
    {
        for (int i = 0; i<mapBucket.size(); i++) //goes through bucket size
        {
            HashEntry tableEntry = mapBucket.get(i); //get the entries
            if (tableEntry.getKey().equals(key))// if key equals key search
            {
                return tableEntry.getValue(); // return value
            }
        }
        return 1; // otherwise return 0
    }


    public void set(String key)// put extra value if key matches
    {
        boolean notFound = false;
        for (int i = 0; i<mapBucket.size(); i++)// goes through bucket size
        {
            HashEntry tableEntry = mapBucket.get(i);//get entries
            if (tableEntry.getKey().equals(key))//if key equals key search
            {
                int put = tableEntry.getValue();
                tableEntry.setValue(put + 1);
                notFound = true; //return true if found
            }
        }
        if (!notFound) //if false
        {
            mapBucket.add(new HashEntry(key,1)); // add 1 to key
        }
    }

    public void remove(String key)// remove key from index
    {
        for (int i = 0; i<mapBucket.size(); i++)// goes through bucket siz
        {
            HashEntry tableEntry = mapBucket.get(i);//gets the entries
            if (tableEntry.getKey().equals(key))//if key equals key search
            {
                mapBucket.remove(tableEntry);//remove key
            }
        }
    }

}

