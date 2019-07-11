/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estmaxtwitter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Suha
 */
public class PrefixTree {

int size;
private Node root;
ArrayList<Node> Pnods= new ArrayList<Node> ();
private Stack nodes;
 FileOutputStream fout;
 PrintStream ps;
public PrefixTree()
{
    nodes= new Stack();
    root= new Node(null);
    root.setWord("");
size=0;
 
}
Node insert(String words)
{
    return insert(words, root);
     
}
Node insertfather(String word)
{
 Node n= new Node(root);
    n.setWord(word);
    root.addChild(n);
     size++;
     return n;
}
private Node insert(String words, Node n)
{

 String [] wordapart=words.split(",", 2);
 Node nextNode=null;
int  index=find (n,wordapart[0]);
 
  if(index==-1)
 {
    
    nextNode = new Node(n);
    nextNode.setWord(wordapart[0]);
    n.addChild(nextNode) ;
     size++;

 }
 else
 {
    nextNode=n.childern.get(index);
    
 }


 if(wordapart.length>1)
 {
    
     nextNode=insert(wordapart[1],nextNode);
 }
return nextNode;
}
Node retBelong(String e)
{
  return retBelong ( e,root);
}
private Node retBelong (String e,Node n)
{


 String [] wordapart=e.split(",", 2);
 Node nextNode=null;

int  index=find (n,wordapart[0]);


 if(index==-1)
 {
   return null;
 }
 else if(wordapart.length<2)
 {
     nextNode=n.childern.get(index);
     return nextNode;
 }
 else
 {
       nextNode=n.childern.get(index);

         return retBelong (wordapart[1],nextNode);


 }//else


}
String Belongs(String e)
{
 return  isInPk (e,root);
}
private String isInPk (String e,Node n)
{
  String indexes="";
 
 String [] wordapart=e.split(",", 2);
 Node nextNode=null;

int  index=find (n,wordapart[0]);
indexes+=index;
 //System.out.println(""+index+" "+wordapart[0]);
 if(index==-1)
 {
   return "-1";
 }
 else if(wordapart.length<2)
 {
     return indexes;
 }
 else 
 {

         nextNode=n.childern.get(index);
         return (indexes+","+isInPk (wordapart[1],nextNode));
       
  
 }//else
  
  
}
 private Node getE (Node n,int childNo)
 {
 return n.childern.get(childNo);
 }
 Node getE(String path)
 {
     int p;
    Node n=root;
   String[] pathnode=path.split(",");
    for(int i=0;i<pathnode.length;i++)
    {
    p = Integer.parseInt(pathnode[i]);
       n= getE(n,p);
    }

    return n;
 }

int find(Node n,String aword)
{
  for(int i=0;i<n.childern.size();i++)

      if(aword.matches(n.childern.get(i).getWord())==true)
              return i;


  return  -1;

}
 void eliminate(Node parent)
{


 for(int i=0;i<parent.noChildren();i++)
 {
     if(parent.childern.get(i).noChildren()>0)
         eliminate(parent.childern.get(i));
     else{
         parent.childern.remove(i);


        }
parent.Parent.childern.remove(parent);
 }//for 


}
public void force_purning(int D,double Ssig)
{
  for(int i=0;i<root.noChildren();i++)
         {

             if((root.childern.get(i).cnt/D)>=Ssig)
                 purnTree(root.childern.get(i),D,Ssig);
             else if((root.childern.get(i).cnt/D)<Ssig)
                {
                eliminate(root.childern.get(i));
                }

         }//for
}
private void purnTree(Node parent,int D,double Ssig)
{
   
    for(int i=0;i<parent.noChildren();i++)
         {

             if((parent.childern.get(i).cnt/D)>=Ssig)
                 purnTree(parent.childern.get(i),D,Ssig);
             else if((parent.childern.get(i).cnt/D)<Ssig)
                {
                eliminate(parent.childern.get(i));
                }

         }//for
    
}//Node parent
void TraversTree()
{
   TraversTree(root);
}
private void TraversTree(Node parent)
{
    parent.loxicographicallyOrdered();


 for(int i=0;i<parent.noChildren();i++)
 {

     if(parent.childern.get(i).noChildren()>0)
         TraversTree(parent.childern.get(i));
     else{
         Pnods.add(parent.childern.get(i));
        }

 }//for


}//
 private void creatfile() {
         String nowtime= DateUtils.nowfile();
        try {
            fout = new FileOutputStream(nowtime+ "prefixTree.txt", true);
           ps = new PrintStream(fout);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrefixTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
void printTree(int D,double Ssig)
{
    creatfile();
    ps.println("TopicNow : " + DateUtils.now());
    ps.println("********************************************");

    printTree(root,D,Ssig);
}
private void printTree(Node parent,int D,double Ssig)
{
    parent.loxicographicallyOrdered();
boolean flag=false;
//nodes.push(parent);
 for(int i=0;i<parent.noChildren();i++)
 {
    
     if(parent.childern.get(i).noChildren()>0)
     {
         nodes.push(parent.childern.get(i));
       printTree(parent.childern.get(i),D,Ssig);
       nodes.pop();
       
     }//if
        
     else{

         nodes.push(parent.childern.get(i));
         for(int j=0;j<nodes.size();j++)
         { Node n=(Node)nodes.elementAt(j);
           //  if(n.IS_MAX==true && (n.ML/D)>=Ssig)
             {
         flag=true;
         // System.out.print(n.getWord()+""+n.cnt+" ");
           ps.print(n.getWord()+n.cnt+",");
              }//maximal
         }//for
        nodes.pop();

        
//System.out.println();
     //if(flag==true)
            ps.println();
        }//else

 }//for

}//printTree
double Cmaxestimate(String e)
{
     double max=Double.MAX_VALUE;
  
   double CK=0;
String []perstr=perm1(e.split(",")).split("#");

for(int i=0;i<perstr.length;i++)
      for(int j=i+1;j<perstr.length;j++)
      {
       
          String str1=Belongs(perstr[i]);
          String str2=Belongs(perstr[j]);
          if(!str1.contains("-1") && !str2.contains("-1"))
          {
           
           CK=Math.min(getE(str1).cnt, getE(str2).cnt);
          
           if(max>CK)
               max=CK;
          }
      }
return max;
}

double Cerrestimate(String e)
{
return ( Cmaxestimate(e)-Cminestimate(e));
}
double Cminestimate(String e)
{

String []perstr=perm1(e.split(",")).split("#");
int min=-1;
int CK=0;
  for(int i=0;i<perstr.length;i++)
      for(int j=i+1;j<perstr.length;j++)
      {
          String str1=Belongs(perstr[i]);
          String str2=Belongs(perstr[j]);
          if(!str1.contains("-1") && !str2.contains("-1"))
          {
               
CK=Math.max(0,(str1.length()+str2.length()- getNumberOfMatchingCharacters(str1,str2)));

           if(min<CK)
              min=CK;


          }
      }//for j

return min;
}//

public int getNumberOfMatchingCharacters(String str1,String str2) {
		int numberOfMatchingCharacters = 0;
       for(int i=0;i<str1.length();i++)

           if(str1.charAt(i)==str2.charAt(i))
               numberOfMatchingCharacters++;
           else
               break;
		return numberOfMatchingCharacters;
	}

   public  static String perm1(String[] s) { return perm1("", s); }
    private static String  perm1(String prefix, String[] s) {
        String perstr="";
        int N = s.length;
        if (N == 1)
        {
          prefix=prefix.substring(0, prefix.length()-1);
          perstr+=(prefix+"#");
        }
        else {
            for (int i = 0; i < N; i++)
            {
                String []str= new String[N-1];
                int c=0;
                for(int j=0;j<i;j++)
                {
                str[c]=s[j];
                c++;
                }
                for(int j=i+1;j<N;j++)
                {
                str[c]=s[j];
                c++;
                }
            perstr+= perm1(prefix + s[i]+",", str);
            }//for i


        }//else
        return perstr;

    }
}//end class
