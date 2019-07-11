/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estmaxtwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/////////////////////
/**
 *
 * @author Suha
 */
public class Node {
private String itemid;
double cnt;
double err;
int tid;
double ML;
boolean IS_MAX;
List<Node> childern;
Node Parent;
private static  NodeitemComparator
             itemidComparator =
                 new  NodeitemComparator ();
public Node(Node p)
{
Parent=p;
childern = new ArrayList<Node>();
}
/*void setcnt(int c)
{
cnt=c;
}
void seterr(int c)
{
err=c;
}
void settid(int c)
{
tid=c;
}
void setML(int c)
{
    ML=c;
}
void setIS_MAX(int c)
{
    //IS_MAX=c;
}
int getcnt()
{
    return cnt;
}
int geterr()
{
    return err;

}
int gettid()
{
    return tid;
}
int getML()
{
return ML;
}
int getIS_MAX()
{
    return IS_MAX;
}*/
int noChildren()
{
return childern.size();
}
void setWord(String aword)
{
    itemid=aword;

}//setWord
String getWord()
{
    return itemid;
}
void addChild(Node n)
{
childern.add(n);

}
public void loxicographicallyOrdered()
{
          if(!childern.isEmpty())
		Collections.sort(childern,itemidComparator);

}//

}
class NodeitemComparator implements Comparator
{
	public int compare(Object arg0, Object arg1)
	{
		Node node1 = (Node)arg0;
		Node node2 = (Node)arg1;



        if(node1.getWord().compareTo(node2.getWord())<0)

            return -1;

        else if(node1.getWord().compareTo(node2.getWord())>0)

            return 1;

        else

            return 0;
	}//compare
}
