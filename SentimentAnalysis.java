import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreNLPProtos.Sentiment;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

class marks{

	private int pos = 0;
	private int neg = 0;
	private int neu = 0;
	private int total = 0;
	public marks() {}
	public void addpos () {
		   pos ++;
		   total++;
		} 
	public void addneg () {
		   neg ++;
		   total++;
		} 
	public void addneu () {
		   neu ++;
		   total++;
		} 
	public int getpos() {
		return pos;
	}public int getneg() {
		return neg;
	}public int getneu() {
		return neu;
	}public int gettotal() {
		return total;
	}
	public void reset() {
		pos = 0;
		neg = 0;
		neu = 0;
		total = 0;
	}
	
}
public class SentimentAnalysis {

 public static String getSentiment(String sentence, marks marker) {
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
            marker.addneu();
          }
          else if (mainSentiment == 0 || mainSentiment == 1) {
           sentimentString = "NEGATIVE";
          marker.addneg();
          }
          else {
           //3 or 4
           sentimentString = "POSITIVE";
           marker.addpos();
          }
    
    output = new Gson().toJson(sentimentString);
    
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
  
  return "Sentiment: " + output;
 }
 
 

 private static void passtweetObject(JSONObject tweet, marks marker) 
 {
     //Get object within list
     JSONObject tweetObject = (JSONObject) tweet.get("tweet");
     //Get date
    // String date = (String) tweetObject.get("date");    
   //  System.out.println(date);
      
     //Get username
    // String user = (String) tweetObject.get("username");  
   //  System.out.println(user);
      
     //Get content
     String content = (String) tweetObject.get("content");    
   //  System.out.println(content);
     String Sentiment;
     Sentiment = getSentiment(content,marker);
 //  System.out.println(Sentiment);
   //  System.out.println();

 }
 @SuppressWarnings("unchecked")
public static void main(String args[]) throws IOException {
 marks marker = new marks();
	 JSONParser jsonParser = new JSONParser();
	 String syspath = System.getProperty("user.home")+ "\\Desktop\\" + "twitter.json";
     try (FileReader reader = new FileReader(syspath))
     {
         //Read JSON file
         Object obj = jsonParser.parse(reader);

         JSONArray tweetList = (JSONArray) obj;
      //   System.out.println(tweetList);
          
         //Iterate over tweet array
         tweetList.forEach( twee -> passtweetObject( (JSONObject) twee, marker));

     } catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     } catch (ParseException e) {
         e.printStackTrace();
     }
     System.out.printf("Positive: %d\nNegative: %d\nNeutral: %d\n", marker.getpos(), marker.getneg(), marker.getneu());
     //marker.reset();
     System.out.printf("Total Sentiments: %d",marker.gettotal());




  
 }

}