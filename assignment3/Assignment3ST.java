public class Assignment3ST<Key, Value>
{
    private int M;
    private int N;

    private Node[] st;

    private class Node<Key, Value>
    {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // public Assignment3ST()
    // {
    //     this(997);
    // }

    public Assignment3ST(int m)
    {
        //this(997);
        this.M = m;
        this.st = new Node[m];

    }

    public int size()
    {
        return N;
    }

    public void delete(Key key)
    {
        put(key, null);
    }

    public boolean contains(Key key)
    {
        return get(key) != null;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value)
    {
        int i = hash(key);
        for(Node n = st[i]; n != null; n = n.next)
        {
            if(n.key.equals(key))
            {
                n.value = value;
                return;
            }
        }
        st[i] = new Node(key, value, st[i]);
        N++;
    }

    public Value get(Key key)
    {
        int i = hash(key);
        for(Node n = st[i]; n != null; n = n.next)
        {
            if(n.key.equals(key))
            {
                return (Value) n.value;
            }
        }
        return null;
    }

    public void print()
    {
        for(Node n : st)
        {
            int size = 0;

            while(n != null && n.key != null)
            {
                n = n.next;
                size++;
            }
        System.out.println(" size " +  size);
        }
    }

    public Iterable<Key> keys()
    {
        Fifo<Key> q = new Fifo<Key>();

        for(int i = 0; i < M; i++)
            for(Node n = st[i]; n != null; n = n.next)
                q.enQ((Key) n.key);
        return q;
    }
}
