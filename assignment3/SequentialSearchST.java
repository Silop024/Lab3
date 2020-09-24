public class SequentialSearchST<Key, Value>
{
    public Node first;

    private class Node
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

    public Value get(Key key)
    {
        for(Node x = first; x != null; x = x.next)
            if(key.equals(x.key))
            {
                x.value = value
                return;
            }
            first = new Node(key, value, first);
    }
}
