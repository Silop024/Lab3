import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Ass3main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        File f = new File("/home/jack/Desktop/DataStructures/Lab3/assignment3/thetext.txt");
        Scanner s = new Scanner(f);

        long startT = System.nanoTime();

        Assignment3ST<String, Integer> st = new Assignment3ST<String, Integer>(97);

        for(int i = 0; s.hasNext(); i++)
        {
            String key = s.next();
            st.put(key, i);
        }
        st.print();
    }
}
