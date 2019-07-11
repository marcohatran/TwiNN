/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trends;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.queryParser.ParseException;

/**
 *
 * @author Suha
 */
public class Translator implements Runnable{
    String[] beforeTran;
//  Thread runner;

     public  Translator(String bTran) throws Exception
     {

          conecateString(bTran);
         
        // runner = new Thread(this, threadname);
         //runner.start();
     }
     void conecateString(String twits )
     {
            // System.out.println(twits);
             beforeTran=twits.split("-&>");
         //  System.out.println(java.util.Arrays.toString(beforeTran));
     }//conecateString
 public void run() {

        try {
            Translate.setHttpReferrer("http://symbateur.blogspot.com/");
           // Translate.setKey("AIzaSyAMY0aW_RgWOAQaOTrfK-eP4b0IXZV7v6o"); //setHttpReferrer(" asdf");//"http://symbateur.blogspot.com/"/
String[] afterTran= new String [beforeTran.length];
         
for (int i = 0; i < beforeTran.length; i++) {

            afterTran[i] = Translate.execute(beforeTran[i], Language.AUTO_DETECT, Language.ENGLISH);
            if( afterTran[i]==null)
                afterTran[i]="0";
}
           /* System.out.println("tiwit:" + afterTran[0]);
            System.out.println("tiwit:" + afterTran[1]);
            System.out.println("tiwit:" + afterTran[2]);
            System.out.println("tiwit:" + afterTran[3]);
            // System.out.println("tiwit:"+ afterTran[4]);*/
            System.out.println("TransNow : " + DateUtils.now());
   /*     try {
            //for i
            HelloLucene.HelloLucene(afterTran);
            } catch (IOException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
             */
        } catch (Exception ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        }

 }
}//end class
