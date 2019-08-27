/**
 * 
 */
package br.com.iot.imepac.main;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * @author Claudiney e Marcella
 *
 */
public class Twitter4jMain {

	/**
	 * @param args
	 * @throws TwitterException 
	 */
	public static void main(String[] args) throws TwitterException {
		TwitterFactory tf = new TwitterFactory();
		Twitter twitter = tf.getInstance();
		try {
		    Query query = new Query("#IOTIMEPAC");
		    QueryResult result;
		    do {
		        result = twitter.search(query);
		        List<Status> tweets = result.getTweets();
		        for (Status tweet : tweets) {
		            System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
		        }
		    } while ((query = result.nextQuery()) != null);
		    System.exit(0);
		} catch (TwitterException te) {
		    te.printStackTrace();
		    System.out.println("Failed to search tweets: " + te.getMessage());
		    System.exit(-1);
		}
	}

}
