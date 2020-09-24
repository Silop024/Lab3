public class Assignment3ST<Key, Value>
{
    private int M ;
    private int N;
    private SequentialSearchST<Key, Value>[] st;
    //WTF
    public Assignment3ST<Key, Value>()
    {
        this(997);
    }

    public Assignment3ST(int M)
    {
        this.M = M;

        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];

        for(int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key)
    {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value)
    {
        st[hash(key)].put(key, value);
    }
    //WTF
    public Iterable<Key> keys()
    {

    }

}
