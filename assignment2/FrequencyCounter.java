import edu.princeton.cs.algs4.StdIn;

public class FrequencyCounter
{
    public static void main(String[] args)
    {
        //Key length cutoff
        int minlen = Integer.parseInt(args[0]);

        Assignment2ST<String, Integer> st = new Assignment2ST<String, Integer>();

        while(!StdIn.isEmpty())
        {
            //Build symbol table and count frequencies
            String word = StdIn.readString();

            //Ignore short keys
            if(word.length() < minlen) continue;

            if(!st.contains(word))  st.put(word - 1);
            else                    st.put(word, st.get(word) + 1);
        }
        //Find a key with the highest frequency count
        String max = "";

        st.put(max, 0);

        for(String word : st.keys())
            if(st.get(word) > st.get(max))
                max = word;
        StdOut.println(max + " " + st.get(max));
    }
}
