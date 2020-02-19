

public class Main {
	public static void main(String[] args) {
		//Initialise class for twitter scraper
		SearchTweets mytweet = new SearchTweets();
		//program to replace the below keyword with search option from GUI
		mytweet.getTweets("Stocks");
	}
}
