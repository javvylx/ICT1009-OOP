import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreNLPProtos.Sentiment;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;


public class SentimentAnalysis {

 //Regex for extracting data out from .txt file
 private static final Pattern content = Pattern.compile("(?:\"tweet\":).*(?:2020, )(.*)(?:]})"); //for json file


 public static String getSentiment(String sentence) {
  if (sentence == null ) {
   return "";
  }
  String output = "";
  
  if ( sentence != null) { 
   try {
	   PrintStream err = System.err;

	// now make all writes to the System.err stream silent 
	System.setErr(new PrintStream(new OutputStream() {
	    public void write(int b) {
	    }}));

    Properties props = new Properties();
          props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
          StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
          int mainSentiment = 0;
          if (sentence != null && sentence.length() > 0) {
              int longest = 0;
              Annotation annotation = pipeline.process(sentence);
              for (CoreMap sentenceStructure : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                  Tree tree = sentenceStructure.get(SentimentAnnotatedTree.class);
                  int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                  String partText = sentence.toString();
                  if (partText.length() > longest) {
                      mainSentiment = sentiment;
                      longest = partText.length();
                      System.setErr(err); 
                  }
              }
          }
          
          String sentimentString = null;
          
          if (mainSentiment == 2 || mainSentiment > 4 || mainSentiment < 0) {
              sentimentString = "NEUTRAL";
          }
          else if (mainSentiment == 0 || mainSentiment == 1) {
           sentimentString = "NEGATIVE";
          }
          else {
           //3 or 4
           sentimentString = "POSITIVE";
          }
    
    output = new Gson().toJson(sentimentString);
    
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
  
  return "Sentiment: " + output;
 }
 

 public static void main(String args[]) throws IOException {
  try {
   ArrayList<String> tweets = new ArrayList<String>();

   // output
   ArrayList<String> words = new ArrayList<String>();
   BufferedReader file = new BufferedReader(new FileReader("twitter.json"));
   String line = "";
   while ((line = file.readLine()) != null) {
    words.add(line);
   }

   file.close();

   for (int i = 0; i < words.size(); i++) {
    Matcher m = content.matcher(words.get(i));
    if (m.find()) {
     tweets.add(m.group(1));
     System.out.println(m.group(1));
    }
   }
   
   
   String sentimentScore;
   
   for (int i = 0; i < tweets.size(); i++) {
    sentimentScore = getSentiment(tweets.get(i));
    System.out.println(sentimentScore);
   }

  }

  catch (

  FileNotFoundException e) {
   e.printStackTrace();

  }
  
 }
 
}