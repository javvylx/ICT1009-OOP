
import twitter4j.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchTweets {
	public static void getTweets(String s ) {
		//initialise new json object
		JSONObject obj = new JSONObject();
		//initialise new twitter object
		Twitter twitter = new TwitterFactory().getInstance();
		//initialse new json array
		JSONArray mytweet = new JSONArray();
		//initialise counter to limit to 20 tweets
		int counter=0;
        try {
            Query query = new Query(s);
            query.setCount(20);
            QueryResult result;
            //search for keyword
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            do {
                for (Status tweet : tweets) {
                	//remove retweets
                	if(tweet.isRetweet() == false) {
                		java.util.Date tweetdate = tweet.getCreatedAt();
                    	//add tweet date to the array
                        mytweet.add(tweetdate);
                        //add username to the array
                        String username = tweet.getUser().getScreenName();
                        mytweet.add(username);
                        //add tweet to the array
                        String tweettext = tweet.getText();
                        mytweet.add(tweettext);
                        //put tweet into json object
                        obj.put("tweet",mytweet);
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
            	file.write(obj.toJSONString());
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
