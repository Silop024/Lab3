/*README
**Author Jack Webb 2020-09-23
**Last updated
**
**
*/


public class Assignment2ST<Key extends Comparable<Key>, Value>
{
    private Key[] keys;
    private Value[] values;
    private int N;
    //Constructor to created ordered symbol table
    public Assignment2ST(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        N = 0;
    }
    //Returns number of key-value pairs
    public int size()
    {
        return N;
    }
    //Returns true if the table is empty, false if not
    public boolean isEmpty()
    {
        return N == 0;
    }
    //Returns true if there is a value paired with the given key, else false
    public boolean contains(Key key)
    {
        return get(key) != null;
    }

    /*Returns number of keys smaller than key. (To get index of key)
    **
    **Used to determine where a key fits in the order.
    */
    public int rank(Key key)
    {
        int low = 0;
        int high = N - 1;

        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            int compare = key.compareTo(keys[mid]);

            if(compare < 0)
                high = mid - 1;
            else if(compare > 0)
                low = mid + 1;
            else
                return mid;
        }
        return low;
    }
    //Returns the value paired with the given key, or null if key absent
    public Value get(Key key)
    {
        //If empty, there is nothing to get
        if(isEmpty())
            return null;
        //Tells us where the key is to be found or if it does not exist
        int i = rank(key);
        //If key is inside table (i < size) & if key in i is equal to given key
        //return its value. Else return null.
        if(i < N && keys[i].compareTo(key) == 0)
            return values[i];
        else
            return null;
    }
    //Inserts key-value pair or deletes if value == null
    public void put(Key key, Value value)
    {
        //Tells us where to update the value if key exists or where to put new if not
        int i = rank(key);

        //If key already exists in the table, update value and go out of method
        if(i < N && keys[i].compareTo(key) == 0)
        {
            values[i] = value;
            return;
        }
        //Move all larger keys one position to make room for new key-value pair
        for(int j = N; j > i; j--)
        {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        //Insert new key-value pair and increase size
        keys[i] = key;
        values[i] = value;
        N++;
    }
    //Returns keys in the table from low to high in sorted order
    public Iterable<Key> keys(Key low, Key high)
    {
        Fifo<Key> q = new Fifo<Key>();

        for(int i = rank(low); i < rank(high); i++)
            q.enQ(keys[i]);

        if(contains(high))
            q.enQ(keys[rank(high)]);
        return q;
    }
    //Returns all keys in the table in sorted order
    public Iterable<Key> keys()
    {
        return keys(0, N-1);
    }
}
