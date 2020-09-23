/*README
**Author Jack Webb 2020-09-23
**Last updated
**
**
*/

public class Assignment2BST<Key extends Comparable<Key>, Value>
{
    //Root of the BST, has all the keys and values in the symbol tree
    private Node root;

    private class Node
    {
        //Key
        private Key key;
        //The value associated with the key
        private Value value;
        //Left subtree, points to smaller keys
        private Node left;
        //Right subtree, points to larger keys
        private Node right;
        //Size of the rooted subtree
        private int N;

        //Constructor for root of a subtree
        public Node(Key key, Value value, int N)
        {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size()
    {
        return size(root);
    }

    //If root is empty return 0, else return N
    private int size(Node x)
    {
        if(x == null)
            return 0;
        else
            return x.N;
    }

    public Value get(Key key)
    {
        return get(root, key);
    }

    public Value get(Node x, Key key)
    {
        if(x == null)
            return null;

        int compare = key.compareTo(x.key);

        if(compare < 0)
            return get(x.left, key);
        else if(compare > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public void put(Key key, Value value)
    {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value)
    {
        //If there exists no nodes, make a new root with the key and value with
        //size 1.
        if(x == null)
            return new Node(key, value, 1);

        //Compare < 0 if key < x key; compare > 0 if key > x key; 0 if equal
        int compare = key.compareTo(x.key);

        //If input key is less than the node's key do a recursive call
        //pointing at the left subtree (containing smaller keys)

        //If input key is greater than the node's key, do a recursive call
        //pointing at the right subtree (containing larger keys)

        //If equal simply update the value of x.
        if(compare < 0)
            x.left = put(x.left, key, value);
        else if(compare > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;

        //Update size of root to the combined sizes of subtrees + 1
        x.N = size(x.left); + size(x.right) + 1;

        //return root
        return x;
    }
}
