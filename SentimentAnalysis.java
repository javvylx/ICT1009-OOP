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

class marks
{ //class to store count of results
	private int pos = 0;
	private int neg = 0;
	private int neu = 0;
	private int total = 0;
	public marks() 
	{//default constructor
		pos = neg = neu = total = 0; //sets all to 0
	}
	public void addpos () 
	{ //adds to positive counter
		   pos ++;
		   total++;
		} 
	public void addneg () 
	{ //adds to negative counter
		   neg ++;
		   total++;
		} 
	public void addneu () 
	{ // adds to neutral counter
		   neu ++;
		   total++;
		} 
	public int getpos() 
	{
		return pos;
	}
	public int getneg() 
	{
		return neg;
	}
	public int getneu() 
	{
		return neu;
	}
	public int gettotal() 
	{
		return total;
	}
	public void reset() 
	{
		pos = neg = neu = total = 0; //sets all to 0
	}
}
public class SentimentAnalysis 
{
	public static String getSentiment(String sentence, marks marker) 
	{
		if (sentence == null ) {
			return "";
			}
			String output = "";
			if ( sentence != null) 
			{ 
				try 
				{
				PrintStream err = System.err;
				//make all writes to the System.err stream silent 
				System.setErr(new PrintStream(new OutputStream() {
					public void write(int b) {
				}}));
					Properties property = new Properties(); // creates a StanfordCoreNLP object with for sentiment analysis
					property.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
					StanfordCoreNLP pipeline = new StanfordCoreNLP(property);
					int OverallSentiment = 0;
					if (sentence != null && sentence.length() > 0) 
					{
						int longest = 0;
						Annotation annotator = pipeline.process(sentence);// annotates sentence for processing
						for (CoreMap sentenceStructure : annotator.get(CoreAnnotations.SentencesAnnotation.class)) 
						{// for every annotation that is created
							Tree senttree = sentenceStructure.get(SentimentAnnotatedTree.class);//;sentiment tree for analysis
							int sentimentresult = RNNCoreAnnotations.getPredictedClass(senttree);// NLP predicts sentiment based on sentence and produces a int
							String partialText = sentence.toString();//sets partialText to be current annotated sentence
							if (partialText.length() > longest)
							{// if annotated sentence is longer than previous
								OverallSentiment = sentimentresult; // overall sentiment will be the current sentiment
								longest = partialText.length(); // updates the longest annotated sentence length
								System.setErr(err); //removes message to console
							}
						}
					}
					String sentimentString = null;
					//Sentiment numbers ""Negative" = 0 or 1 "Neutral" = 2 "Positive" = 3 or 4
					if (OverallSentiment == 2 || OverallSentiment > 4 || OverallSentiment < 0) 
					{
						sentimentString = "NEUTRAL";
						marker.addneu();
					}else if (OverallSentiment == 0 || OverallSentiment == 1) 
					{
						sentimentString = "NEGATIVE";
						marker.addneg();
					}else //if value is 3 or 4
					{
						sentimentString = "POSITIVE";
						marker.addpos();
					}
					output = new Gson().toJson(sentimentString);
				} catch (Exception e) 
					{
						e.printStackTrace();
					}
			}
			return "Sentiment: " + output;
	}
 
	private static void passtweetObject(JSONObject tweet, marks marker) 
	{
	 //Get object within list
		JSONObject tweetObject = (JSONObject) tweet.get("tweet");
		/*for debugging only
		//Get date
		String date = (String) tweetObject.get("date");    
		System.out.println(date); - debugging 
		//Get username
		String user = (String) tweetObject.get("username");  
		System.out.println(user);
		//Get content
		String content = (String) tweetObject.get("content");    
		System.out.println(content);
		String Sentiment;
		Sentiment = getSentiment(content,marker);
		System.out.println(Sentiment);
		System.out.println();*/
	}
 @SuppressWarnings("unchecked")
	public static void main(String args[]) throws IOException 
	{
		marks marker = new marks();// creates marker object to store count
		JSONParser jsonParser = new JSONParser(); // creates JSON Parser object
		String syspath = System.getProperty("user.home")+ "\\Desktop\\" + "twitter.json"; //same system pash as twitter function
		try (FileReader reader = new FileReader(syspath))
		{
			//Read JSON file
			Object obj = jsonParser.parse(reader);
			JSONArray tweetList = (JSONArray) obj; // converts file content into json array
			/*System.out.println(tweetList);*/
			//Iterate over tweet array
			tweetList.forEach( twee -> passtweetObject( (JSONObject) twee, marker));
		 } catch (FileNotFoundException e) 
			{
			 e.printStackTrace();
			} catch (IOException e) 
			{
			 e.printStackTrace();
			} catch (ParseException e) 
			{
			 e.printStackTrace();
			}
		System.out.printf("Positive: %d\nNegative: %d\nNeutral: %d\n", marker.getpos(), marker.getneg(), marker.getneu()); // prints counter for sentiments
		//marker.reset(); - will set object counters to 0
		System.out.printf("Total Sentiments: %d",marker.gettotal()); // prints total sentiments
	}
}