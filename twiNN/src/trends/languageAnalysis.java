/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trends;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.cz.CzechAnalyzer;
import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.analysis.el.GreekAnalyzer;
import org.apache.lucene.analysis.fa.PersianAnalyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.nl.DutchAnalyzer;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.util.Version;
/**
 *
 * @author Suha
 */
public class languageAnalysis {
  public enum Languages//23 language
  {
     Arabic,BrazilianPortuguese,Czech,Danish,German,Greek,English,
            Spanish,Persian,Finnish,French,Hungarian
            ,Italian,Japanese,Korean,Dutch,Norwegian,Portuguese,Romanian,Russian,Swedish
            ,Turkish,Chinese
  }
  String Languages[]={"ar","br","cs","da","de","el","en","es","fa","fi",
            "fr","hu","it","ja","ko","nl","nb","pt","ro","ru","sv","tr","zh"};

 public static Analyzer languageAnalysis(int L)
 {
     StopWords sw= new StopWords();
 switch(L)
 {
      case 0:

          return new ArabicAnalyzer(Version.LUCENE_30);
   
      case 1:
           return new BrazilianAnalyzer(Version.LUCENE_30);
         
    case 2:
        return new CzechAnalyzer (Version.LUCENE_30);
         
      case 3:
      return new SnowballAnalyzer(Version.LUCENE_30,"Danish");
      case 4:
     return new GermanAnalyzer(Version.LUCENE_30);
      case 5:
       return new GreekAnalyzer (Version.LUCENE_30);
     case 6:
        // return new SnowballAnalyzer(Version.LUCENE_30,"English");
         return new StopAnalyzer(Version.LUCENE_30,StopFilter.makeStopSet(sw.EnglishStopWords));
        case 7:
         return new SnowballAnalyzer(Version.LUCENE_30,"Spanish");
    
      case 8:
          return new PersianAnalyzer(Version.LUCENE_30);
      case 9:
          return new SnowballAnalyzer(Version.LUCENE_30,"Finnish");
      case 10:
         return new FrenchAnalyzer (Version.LUCENE_30);
     case 11:
         return new SnowballAnalyzer(Version.LUCENE_30,"Hungarian");
  
      case 12:
        return new SnowballAnalyzer(Version.LUCENE_30,"Italian");
     case 13:
        return new CJKAnalyzer (Version.LUCENE_30);
      case 14:
      return new CJKAnalyzer (Version.LUCENE_30);
      case 15:
         return new DutchAnalyzer (Version.LUCENE_30);
      case 16:
         return new SnowballAnalyzer(Version.LUCENE_30,"Norwegian");
      case 17:
         return new SnowballAnalyzer(Version.LUCENE_30,"Portuguese");
      case 18:
          return new SnowballAnalyzer(Version.LUCENE_30,"Romanian");
      case 19:
        return new RussianAnalyzer (Version.LUCENE_30);
      case 20:
         return new SnowballAnalyzer(Version.LUCENE_30,"Swedish");
      case 21:
         return new SnowballAnalyzer(Version.LUCENE_30,"Turkish");
      case 22:
       return  new SmartChineseAnalyzer(Version.LUCENE_30);

 }//switch
 return null;
 }
}
