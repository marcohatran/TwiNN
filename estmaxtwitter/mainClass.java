/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estmaxtwitter;

import twitter4j.*;


public final class mainClass {
    /**
     * Main entry of this application.
     * @param args
     */


    public static void main(String[] args)throws TwitterException, Exception{
      

        estMax listener = new estMax();
        TwitterStream twitterStream = new TwitterStreamFactory(listener).getInstance();
        twitterStream.sample();
  

    }//main
 
}//class



