/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trends;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.*;


/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class PrintSampleStream extends StatusAdapter {
    /**
     * Main entry of this application.
     * @param args
     */


    public static void main(String[] args)throws TwitterException, Exception{
       
        StatusListener listener = new StatusAdapter() {
           
            int start=0;
           int numTwit=40000;
           //int numTwit=10;
             List<String> Langext=Arrays.asList("ar","br","cs","da","de","el","en","es","fa","fi",
                     "fr","hu","it","ja","ko","nl","nb","pt","ro","ru","sv","tr","zh");
             int langCount[]= new int [23];
             String[] langTweets= new String [23];
            // String [] twits= new String[numTwit];
            public void onStatus(Status status) {
                try {
                  String tweetlang=status.getUser().getLang();
                   int index = Langext.indexOf(tweetlang);
                  // if(index==6){
                   // System.out.println(/*"@" + status.getUser().getScreenName() + */"Txt:" + status.getText());

                       langTweets[index]+=(status.getText()+"-&>");
                   
                    if(langCount[index]==(numTwit-1))
                    {

                        
                    // Thread Tranthread = new Thread(new Translator(twits),"thread"+start);
                     //   Tranthread.start();
                       Thread Tranthread = new Thread( new LuceneWork(langTweets[index],index),"thread"+start);
                        Tranthread.start();
                         start++;
                        //initilazations
                        langCount[index]=0;
                         langTweets[index]="";
                      System.out.println("1000Now : " + DateUtils.now());
                    }
                    else
                       langCount[index]++;
                 
              //     }if index==6
                  
                    ////////////////////////////////////////////////
                } catch (Exception ex) {
                    Logger.getLogger(PrintSampleStream.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        TwitterStream twitterStream = new TwitterStreamFactory(listener).getInstance();
      twitterStream.sample();
  

    }//main
 
}//class



