/*Author Jack Webb
**Last updated 2020-09-26
**The code is able to insert/remove (any type of?) elements in a
**doubly-linked circular FIFO queue.
**The methods isEmpty, size, Fifo (constructor) and Node are based on lecture notes.
*/

package task3;
import java.util.*;

// interface Iterable<Item>
// {
//    void enQ(Item item);
//    Item deQ();
//    boolean isEmpty();
//    int size();
//    void printQ();
// }
/*Generic class Fifo which is my First in First out class queue which implements
//my interface Iterable.
*/
public class Fifo<Item> implements Iterable<Item>
{
   /*Generic class Node, creates a generic object Node with type parameter
   //Item. A value of generic type is stored as well as a next and previous node.
   */
   private class Node<Item>
   {
      Item item;
      Node<Item> next;
      Node<Item> prev;
   }

   private int i;    //nr of items in queue
   private Node<Item> first; //front of queue
   private Node<Item> last;   //back of queue

   //Temporary variable where I store nodes during reorganizing
   private Node<Item> temp;

   /*This was only used during testing but I keep it in case more testing is
   **needed. Used when printing the list to not override temp when in use.
   */
   private Node<Item> strTemp;

   //Default constructur creates an empty queue.
   public Fifo()
   {
      first = null;
      last = null;
      i = 0;
   }

   /*A method which adds a node with the item inputted to the linked list.
   **Here temp is used to store "last" which is our indicator to where the
   **back of the queue is. It then makes last point at the new node and makes
   **the new node point at the old last which is now temp. If no nodes are
   **currently in the linked list first is also last, else temp previous node
   **is the new node. Since it's circular last and first always point at
   **each other. Index i is incremented to keep track of the amount of nodes
   **and the list is printed before returning to main.
   */
   public void enQ(Item item)
   {
      temp = last;
      last = new Node<Item>();
      last.item = item;
      last.next = temp;
      if (isEmpty())
         first = last;
      else
         temp.prev = last;
      last.prev = first;
      first.next = last;
      i++;
      printQ();
   }

   /*A method which removes the last element in the list to be used elsewhere.
   **First a local variable "item" stores the desired element. Then we move
   **the indicator "first" to the previous node. We make the new first point
   **at last and last point at new first. Decrement i to keep track of the amount
   **of nodes, print the list and return the element.
   */
   public Item deQ()
   {
      Item item = first.item;
      first = first.prev;
      first.next = last;
      last.prev = first;
      i--;
      printQ();
      return item;
   }

   //A method which is called to check if the list is empty or not.
   public boolean isEmpty()
   {
      return first == null;
   }

   /*A method which is called to check what size the list is. ie the amount of
   **elements/nodes currently in the list.
   */
   public int size()
   {
      return i;
   }

   public Iterator<Item> iterator()
   {
       return new ListIterator();
   }

   private class ListIterator implements Iterator<Item>
   {
       private Node current = first;

       public boolean hasNext()
       {
           return current != null;
       }

       public void remove() {}

       public Item next()
       {
           Item item = current.item;
           current = current.next;
           return item;
       }
   }

   /*A method used to print the contents of the list as a string.
   **The string is initialized empty. Node strTemp is used to iterate through
   **the list. int j is used to count and make sure it doesnt loop around the
   **list. WHile true it adds the element in the node surrounded by brackets
   **to the string. To make it prettier to look at the if condition is used to
   **make sure it doesn't add a "," when there are no further elements to add.
   */
   public void printQ()
   {
      String s = "";
      strTemp = last;
      int j = 0;
      while(j < size())
      {
         s += "[" + strTemp.item + "]";
         if((j + 1) != i)
            s += ",";
         strTemp = strTemp.next;
         j++;
      }
      System.out.println("List: " + s);
      System.out.println();
   }
}
