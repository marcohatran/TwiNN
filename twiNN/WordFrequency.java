package trends;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class WordFrequency
{
	private static WordFrequencyComparator
             m_wordFrequencyComparator =
                 new WordFrequencyComparator();

	public String word;
	public int frequency;
	public WordFrequency(String inWord, int inFrequency)
	{
		word = inWord;
		frequency = inFrequency;
	}
	public String toString()
	{
		StringBuffer sbuf = new StringBuffer();
		return frequency + ":" + word;
	}
        public String toStringWordOnly()
	{
		StringBuffer sbuf = new StringBuffer();
		return  word;
	}
	public static String toString(Iterator itr)
	{
		StringBuffer sbuf = new StringBuffer();
		while(itr.hasNext())
		{
			WordFrequency  wf = (WordFrequency)itr.next();
			sbuf.append(wf.toString());
			sbuf.append("\n");
		}
		return sbuf.toString();
	}

	public static void
        sortByDescendingFrequency(List listOfWordFrequencies)
	{
		Collections.sort(listOfWordFrequencies,
                              m_wordFrequencyComparator);
	}
}
class WordFrequencyComparator implements Comparator
{
	public int compare(Object arg0, Object arg1)
	{
		WordFrequency wf1 = (WordFrequency)arg0;
		WordFrequency wf2 = (WordFrequency)arg1;


        if(wf1.frequency> wf2.frequency)

            return -1;

        else if(wf1.frequency < wf2.frequency)

            return 1;

        else

            return 0;
	}//compare
}
