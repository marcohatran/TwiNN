/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estmaxtwitter;





/**
 *
 * @author Suha
 */
public class UpdateItemSetsClass {
    double Smin=0.2;
    double Serr=0.03;
  //  double Ssig=0.25;
    int q;
    double Cmax;
    double Cerr;
Transaction Qk;
static estMax M;
public UpdateItemSetsClass (estMax emax)
{
     M=emax;
}
public  void Update_itemSets()
 {
    
   InformationUpdating();
  
   
  // M.P.printTree();

 }// end Update_itemSets

private void InformationUpdating()
{
 Qk= new Transaction();
  
 String[] powerTk=M.T.StringPS();
 

for(int i=0;i<powerTk.length;i++)
{
    
   Node e=M.P.retBelong(powerTk[i]);
    if(e!=null)//e is in Pk
    {
        
        e.cnt =((e.cnt)*M.t)+1;
        e.tid=M.k;
     if( (e.cnt/M.D) <M.Ssig)
     { M.P.eliminate(e);}
     else if (e.ML<M.k && Math.floor(e.cnt/Smin )>=M.k && 
                (e.err/M.D)<= Serr && Qk.BelongstoQkPS(e.getWord())==false)
     {
         e.ML=Math.floor(e.cnt/Smin );
         e.IS_MAX=true;
         Qk.Uonion(e.getWord());
     }
      
    }//if e belong to P

}//for M.T.powerTk
 
 String[] einQKps=Qk.StringPS();
for(int i=0;i<einQKps.length;i++)
{
Node e=M.P.retBelong(einQKps[i]);
    if(e!=null)//e is in Pk
    {
        
        e.ML=(e.cnt/Smin);
        e.IS_MAX=false;
    }
}//for e belong to Qk power set

 M.T=null;
///*****************************Item insertation****************************//
Transaction Tdas=new Transaction();
for(int i=0;i<powerTk.length;i++)
{
   
    if(powerTk[i].contains(",")==false){//one word
     Node e=M.P.retBelong(powerTk[i]);
        if(e==null)//not in P and length=1
        {

        Node newfather =M.P.insertfather(powerTk[i]);
                newfather.cnt=1;
                newfather.err=0;
                newfather.tid=M.k;
                newfather.ML=M.k;
                newfather.IS_MAX=true;
    
         }
        else if(e!=null&&(e.cnt/M.D)>=M.Ssig)//is in P
        {
            Tdas.Uonion(powerTk[i]);
        }//else if
    }//if one word
}//belong to Tk and not Pk

powerTk=null;
 

String [] powerTdas =Tdas.StringPS();
for(int i=0;i<powerTdas.length;i++)
{
  //  System.out.println(powerTdas[i]+"@"+M.k);

    String epath=M.P.Belongs(powerTdas[i]);
    if(isEbar(epath,powerTdas[i])==true)
    {
//System.out.println(powerTdas[i]+"$"+M.k);
        Cmax=M.P.Cmaxestimate(powerTdas[i]);
        if((Cmax/M.D)>M.Ssig)
        {
            Node ebar=M.P.insert(powerTdas[i]);
         Cerr=M.P.Cerrestimate(powerTdas[i]);
            ebar.cnt=Cmax;
            ebar.err=Cerr;
            ebar.tid=M.k;
            ebar.ML=(int)Math.floor(Cmax/Smin);
            if(ebar.ML>=M.k&&(ebar.err/M.D)<=Serr)
            {
              ebar.IS_MAX=true;
              Qk.Uonion(ebar.getWord());
            }
            else
            ebar.IS_MAX=false;
        }

    }//if ebar
}//for powerTdas

}//InformationUpdating
private boolean isEbar(String path,String word)
{
    String [] tem=word.split(",");
 String []pathtem=path.split(",");

    if(path.contains("-1")==true && path.length()>=2&&tem.length==pathtem.length)
        return true;
    else
        return false;

}//isEbar
/*ArrayList<String> select_maximal()
{

}*/

}//end class
