
import twitter4j.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchTweets {
	public static void getTweets(String s ) {
		
		//initialise new twitter object
		Twitter twitter = new TwitterFactory().getInstance();
		
		//initialise counter to limit to 20 tweets
		int counter=0;
        try {
            Query query = new Query(s);
            query.setCount(20);
            QueryResult result;
            //search for keyword
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            //initialse new json array
    		JSONArray jarray = new JSONArray();
            do {
                for (Status tweet : tweets) {
                	//initialise new json object
            		JSONObject obj = new JSONObject();
            		JSONObject twitteroutput = new JSONObject();
                	//remove retweets
                	if(tweet.isRetweet() == false) {
                		java.util.Date tweetdate = tweet.getCreatedAt();
                    	//add tweet date to the array
                		twitteroutput.put("date",tweetdate);
                        //add username to the array
                        String username = tweet.getUser().getScreenName();
                        twitteroutput.put("user",username);
                        //add tweet to the array
                        String tweettext = tweet.getText();
                        twitteroutput.put("content",tweettext);
                        //put tweet into json object
                        obj.put("tweet",twitteroutput);
                        jarray.add(obj);
                        counter += 1;
                        //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() ); 
                       
                	}
                }
            } while (counter <= 20);
            try {
            	// create system path for json output file
            	String syspath = System.getProperty("user.home")+ "\\Desktop\\" + "twitter.json";
            	//true to append data to twitter.json
            	FileWriter file = new FileWriter(syspath,true);
            	//write to json file
            	jarray.writeJSONString(file);
            	file.flush();
                file.close();
            } catch (IOException e) {
            	e.printStackTrace();
            } 
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
	
}
