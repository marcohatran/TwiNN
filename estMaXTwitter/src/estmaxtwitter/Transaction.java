/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estmaxtwitter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suha
 */
public class Transaction {
    private ArrayList<String> Qk;
    private ArrayList<String[]> QPS;
    private StringloxiComparator stringloxcom;
    private FileOutputStream fout;
   private PrintStream ps;
    public Transaction()
    {
    Qk= new ArrayList<String>();
    QPS= new  ArrayList<String[]>();
  stringloxcom= new StringloxiComparator();

  
    }
   private void creatfile() {
         String nowtime= DateUtils.nowfile();
        try {
            fout = new FileOutputStream(nowtime+ "qTopTmax.txt", true);
           ps = new PrintStream(fout);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrefixTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
void Uonion(String e)
{
 String set[] = e.split(",");
 
 for(int i=0;i<set.length;i++)
     Qk.add(set[i]);
 
  QPS=powerset(Qk);
}//uonion
void printTopTmaxSet()
{
    if(Qk.size()>0){
   
creatfile();
 ps.println("Top q Tmax: " + DateUtils.now());
    ps.println("********************************************");

    String tem;
   loxicographicallyString(Qk);
    int max=Qk.get(0).length();
    for(int i=0;i<Qk.size();i++)
    {
    tem=Qk.get(i);
    // if(max==tem.length())
         ps.println(tem);
     
    }
}//if size not equal to zero
}
 String []StringPS()
{
    String []qpsstr= new String[QPS.size()];
    List<String>mylist= new ArrayList<String>();
for(int i=0;i<QPS.size();i++)
{
qpsstr[i]="";
   String [] tem=QPS.get(i);
   for(int j=0;j<tem.length;j++)
   {
       qpsstr[i]+=(tem[j]);
       if(j!=(tem.length-1))
         qpsstr[i] +=",";
   }
  mylist.add(qpsstr[i]);
}
loxicographicallyString(mylist);
String[] y = mylist.toArray(new String[0]);
 qpsstr=null;
 mylist=null;
return y;
}
boolean BelongstoQkPS(String e)
{
     String set[] = e.split(",");
for(int i=0;i<QPS.size();i++)
{
if(Arrays.equals(set,QPS.get(i))==true)
    return true;
}
     return false;


}
private  ArrayList<String[]> powerset(ArrayList<String> set) {

       //create the empty power set
       ArrayList<String[]>power = new  ArrayList<String[]>();

       //get the number of elements in the set
       int elements = set.size();

       //the number of members of a power set is 2^n
       int powerElements = (int) Math.pow(2,elements);

       //run a binary counter for the number of power elements
       for (int i = 1; i < powerElements; i++) {

           //convert the binary number to a string containing n digits
           String binary = intToBinary(i, elements);

           //create a new set
        ArrayList<String> innerSet = new  ArrayList<String>();

           //convert each digit in the current binary number to the corresponding element
            //in the given set
           for (int j = 0; j < binary.length(); j++)
           {
               if (binary.charAt(j) == '1')
                   innerSet.add(set.get(j));
            }
       loxicographicallyOrdered(innerSet);
String [] instrset= new String[innerSet.size()];

            for(int j=0;j<innerSet.size();j++)
           instrset[j]=(String)innerSet.get(j);

           //add the new set to the power set
           power.add(instrset);

       }

       return power;
   }

   /**
     * Converts the given integer to a String representing a binary number
     * with the specified number of digits
     * For example when using 4 digits the binary 1 is 0001
     * @param binary int
     * @param digits int
     * @return String
     */
   private static String intToBinary(int binary, int digits) {

       String temp = Integer.toBinaryString(binary);
       int foundDigits = temp.length();
       String returner = temp;
       for (int i = foundDigits; i < digits; i++) {
           returner = "0" + returner;
       }

       return returner;
   }
private  void loxicographicallyOrdered(ArrayList<String> a)
{

		Collections.sort(a, String.CASE_INSENSITIVE_ORDER);

}//
private  void loxicographicallyString(List <String> astr)
{

		Collections.sort(astr,stringloxcom);

}//
}
class StringloxiComparator implements Comparator
{
	public int compare(Object arg0, Object arg1)
	{
		String str1 = (String)arg0;
		String str2 = (String)arg1;


        if(str1.length()>=str2.length())
            return -1;
        else if(str1.length()==str2.length())
        {
           return str1.compareToIgnoreCase(str2);
        }
        else
            return 1;
	}//compare
}
