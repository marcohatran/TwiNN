/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estmaxtwitter;

import java.io.IOException;
import twitter4j.Status;
import twitter4j.StatusAdapter;


/**
 *
 * @author Suha
 */
public class estMax extends StatusAdapter{
   PrefixTree P;
  Transaction T;
  private Filter tweetFilter;
  int k=1;
  int D=0;
  double Ssig=0.09;
  private final int f=1000;
  private final int d=2;
  private final int w=1;
  final double t=1/(Math.pow(d,(1/w)));
  public estMax() throws IOException
      {
      tweetFilter=new Filter();
       P = new PrefixTree();

      }
public void onStatus(Status status)
{
try {
                
              String cleanstr=tweetFilter.RemoveStopwords(status.getText());
              if(!cleanstr.isEmpty())
              {
                  D =(int)(D*t)+1;
                  T= new Transaction();
                  T.Uonion(cleanstr);
          UpdateItemSetsClass upd= new UpdateItemSetsClass(this);
                 upd.Update_itemSets();
                
                  if(k%f==0)
                {
                 
                
                   P.printTree(D,Ssig);
                   upd.Qk.printTopTmaxSet();
                    //Force pruning
                  P.force_purning(D, Ssig);
                  
                  
                }
                 k++;
                 upd=null;
                 T=null;
              }



}catch (Exception ex)
{
    System.err.print(ex);
}//catch
}//onStatus

}//estMax
