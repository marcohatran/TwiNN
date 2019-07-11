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
         
     }
     void conecateString(String twits )
     {
            // System.out.println(twits);
             beforeTran=twits.split("-&>");
         //  System.out.println(java.util.Arrays.toString(beforeTran));
     }//conecateString
 public void run() {

        try {
            Translate.setHttpReferrer("http:/*******/");
          
String[] afterTran= new String [beforeTran.length];
         
for (int i = 0; i < beforeTran.length; i++) {

            afterTran[i] = Translate.execute(beforeTran[i], Language.AUTO_DETECT, Language.ENGLISH);
            if( afterTran[i]==null)
                afterTran[i]="0";
}
          
            System.out.println("TransNow : " + DateUtils.now());
   /
        } catch (Exception ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        }

 }
}//end class
