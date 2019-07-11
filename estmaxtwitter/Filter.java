/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estmaxtwitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ptstemmer.exceptions.PTStemmerException;
import ptstemmer.*;
/**
 *
 * @author Suha
 */
public class Filter {
    Hashtable h;

    public Filter() throws IOException
    {
    doLoad();
    }
    String RemoveStopwords(String tweet) throws PTStemmerException
    {
        Stemmer st = Stemmer.StemmerFactory(Stemmer.StemmerType.ORENGO);
		st.enableCaching(1000);
		st.ignore("a","e");
   
        tweet=isAlpha(tweet);

        String []tweetbit=tweet.split(" ");
        String cleanstr="";
     for(int i=0;i<tweetbit.length;i++)
     if(tweetbit[i].length()>3 && tweetbit[i].length()<20 )
        if( repeatingChars(tweetbit[i])==false)
         if(h.containsKey(tweetbit[i])==false)
                 cleanstr+=(st.getWordStem(tweetbit[i])+",");

cleanstr.replace(" ", "");


return cleanstr;
    
    }
    boolean repeatingChars(String stringToMatch)
    {

Pattern p = Pattern.compile("(\\w)\\1+");
Matcher m = p.matcher(stringToMatch);
if (m.find()==true && m.group().length()>3)
{
    return true;
}
else
    return false;

    }
   String isAlpha(String s)
{

  s = s.toLowerCase();
char[] charstr=s.toCharArray();
s=null;
  for (int i = 0; i < charstr.length; i ++)
  {
    int c = (int) charstr[i];

    if (c < 97 || c > 122)
      charstr[i]=' ';
  }
String ooo= new String(charstr);
  return ooo;
}


  private  void doLoad() throws IOException {

        h = null;


        try {

            FileInputStream fileIn = new FileInputStream("StopWords.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

             h = (Hashtable)in.readObject();

             in.close();
            fileIn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
