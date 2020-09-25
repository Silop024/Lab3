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
        if(isEmpty())
            return null;

        int i = rank(key);

        if(i < N && keys[i].compareTo(key) == 0)
            return values[i];
        else
            return null;
    }
    //Inserts key-value pair or deletes if value == null
    public void put(Key key, Value value)
    {
        //Variable i is number of keys smaller than key.
        int i = rank(key);

        //If key already exists, update value
        if(i < N && keys[i].compareTo(key) == 0)
        {
            values[i] = value;
            return;
        }
        //For all keys bigger than i, move one position to the right
        for(int j = N; j > i; j--)
        {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        //Put new key and its value in table and grow it
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
        Fifo<Key> q = new Fifo<Key>();

        for(int i = 0; i < N; i++)
            q.enQ(keys[i]);
        return q;
    }
}
