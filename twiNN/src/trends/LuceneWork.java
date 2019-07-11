package trends;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;


import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.lucene.analysis.Analyzer;

public class LuceneWork implements Runnable{
    String twitstr;
    int lang;
  String[]  Languages={"Arabic","BrazilianPortuguese","Czech","Danish","German","Greek","English",
            "Spanish","Persian","Finnish","French","Hungarian","Italian","Japanese","Korean"
            ,"Dutch","Norwegian","Portuguese","Romanian","Russian","Swedish","Turkish","Chinese" };
  public  LuceneWork(String args, int lan)
  {
      twitstr=args;
lang=lan;
  }
   public void run() {

 
        try {
            // 0. Specify the analyzer for tokenizing text.
            //    The same analyzer should be used for indexing and searching
           // StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);

           Analyzer analyzer = languageAnalysis.languageAnalysis(lang);

            // 1. create the index
            Directory index = new RAMDirectory();

            // the boolean arg in the IndexWriter ctor means to
            // create a new index, overwriting any existing index
            IndexWriter w = new IndexWriter(index, analyzer, true, IndexWriter.MaxFieldLength.UNLIMITED);
       
            String[] tiwits = twitstr.split("-&>");
            twitstr=null;
            int hitsPerPage = tiwits.length;
            for (int i = 0; i < tiwits.length; i++) {
                
                addDoc(w, tiwits[i]);
            }

            tiwits=null;
            w.close();
            IndexSearcher searcher = new IndexSearcher(index, true);
            
            //////////////By Me 1/////////////////////////////////////////////////
            IndexReader ir = searcher.getIndexReader();
            TermEnum terms = ir.terms();

                

            ArrayList<WordFrequency> myArr = new ArrayList<WordFrequency>();
            int Corrlcounter = 0;
            while (terms.next()) {
                if(terms.term().text().length()>=4)
                {
                myArr.add(new WordFrequency(terms.term().text(), terms.docFreq()));
                if (terms.docFreq() > 50)
                    {
                        Corrlcounter++;
                    }//terms.docFreq() > 50
                }//
            }//while
            terms.close();
//////////////////////////////////////////////////////////////////////////
            int[][] corroletion = new int[Corrlcounter][Corrlcounter];
            
            WordFrequency.sortByDescendingFrequency(myArr);
            for (int i = 1; i < Corrlcounter; i++) {
                for (int j = 0; j < i; j++) {
                    String querystr = myArr.get(i).toStringWordOnly() + " AND " + myArr.get(j).toStringWordOnly();
                    //System.out.print(myArr.get(i).toStringWordOnly()+"&&&"+myArr.get(j).toStringWordOnly());
                    Query q = new QueryParser(Version.LUCENE_CURRENT, "title", analyzer).parse(querystr);
                    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
                    searcher.search(q, collector);
                    corroletion[i][j] = collector.getTotalHits();
                } //j
            }
            analyzer.close();
            // searcher can only be closed when there
            // is no need to access the documents any more.
            searcher.close();
            index.close();
             ir.close();
//////////////////////my code 2////////////////////////////
            //for(int i=0;i<myArr.size();i++)
            //    System.out.println(myArr.get(i).toString());
////////////////////////////////////////////////////
            boolean cor = false;
            String topic = "";
            ArrayList<String> ls = new ArrayList<String>();
            for (int i = 1; i < Corrlcounter; i++) {
                cor = false;
                topic = "";
                for (int j = 0; j < i; j++) {
                    if (corroletion[i][j] >= 10) {
                        topic += myArr.get(j).toStringWordOnly() + " ";
                        cor = true;
                    }
                } //j
                if (cor == true) {
                    topic += myArr.get(i).toStringWordOnly();
                    ls.add(topic);
                }
            } //i
            myArr=null;
            corroletion=null;
            ////////////////////////////////////////////////////////////
            for (int i = 0; i < ls.size(); i++) {
                for (int j = i + 1; j < ls.size(); j++) {
                    String value = (String) ls.listIterator(i).next();
                    String value2 = (String) ls.listIterator(j).next();
                    if (value2.contains(value)) {
                        ls.remove(i);
                        i = 0;
                        j = i + 1;
                    } //if
                } //j
            } //i
            Iterator it = ls.iterator();
            ls=null;
            createFolder(Languages[lang]);
         String nowtime= DateUtils.nowfile();
            FileOutputStream fout = new FileOutputStream("languagetxt/"+Languages[lang]+"/"+Languages[lang]+nowtime+".txt", true);
            PrintStream ps = new PrintStream(fout);
            
            while (it.hasNext()) {
                String value = (String) it.next();
                ps.println("Value :" + value);
            }
            it=null;
            System.out.println("TopicNow : " + DateUtils.now());
            ps.println("********************************************");
            // Close our output stream
            fout.close();
            
        } catch (ParseException ex) {
            Logger.getLogger(LuceneWork.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CorruptIndexException ex) {
            Logger.getLogger(LuceneWork.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LockObtainFailedException ex) {
            Logger.getLogger(LuceneWork.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LuceneWork.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

  private static void addDoc(IndexWriter w, String value) throws IOException {
    Document doc = new Document();
    doc.add(new Field("title", value, Field.Store.YES, Field.Index.ANALYZED));
    w.addDocument(doc);
 
  }

static void createFolder(String foldername)
    {
   File f=new File("C:\\Users\\Suha\\Documents\\NetBeansProjects\\tiwtren\\languagetxt\\"+foldername);
if(f.exists()==false){

    f.mkdirs();
   // System.out.println("Directory Created");
}

    }
}
