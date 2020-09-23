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

    public Assignment2ST(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];

        for(int i = 0; i < N; i++)
        {
            keys[i] = this.keys[i];
            values[i] = this.values[i];
        }
        this.keys = keys;
        this.values = values;
    }

    public int size()
    {
        return N;
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public boolean contains(Key key)
    {
        return get(key) != null;
    }

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

    /*Computes the number of keys in the table smaller than the input key
    **
    **
    **
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

    public Iterable<Key> keys(Key low, Key high)
    {
        Queue<Key> q = new Queue<Key>();

        for(int i = rank(low); i < rank(high); i++)
        {
            q.enqueue(keys[i]);
        }
        if(contains(high))
            q.enqueue(keys[rank(high)]);
        return q;
    }
}
