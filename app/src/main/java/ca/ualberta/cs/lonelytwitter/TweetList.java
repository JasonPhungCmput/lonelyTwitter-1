package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzphung on 9/27/16.
 */
public class TweetList {
    /**
     * The Tweets.
     */
    List<Tweet> tweets = new ArrayList<Tweet>();
    private int tweetCount = 0;

    /**
     * This method adds the Tweet to the TweetList
     *
     * @param tweet The tweet to be added
     */
    public void add(Tweet tweet){
        //Illegal argument exception
        if (tweets.contains(tweet)){
            throw new IllegalArgumentException("There is already the same tweet in the list.");
        }
        tweets.add(tweet);

    }

    /**
     * Has tweet boolean.
     *
     * @param tweet the tweet
     * @return the boolean
     */
    public boolean hasTweet(Tweet tweet) {
        return tweets.contains(tweet);
    }

    /**
     * Gets tweet.
     *
     * @param i the
     * @return the tweet
     */
    public Tweet getTweet(int i) {
        return tweets.get(i);
    }

    /**
     * Gets tweets.
     *
     * @return the tweets
     */
    public List getTweets() {
        //for(int i = 0; i < tweets.size(); i++){
          //  return tweets.get(i);
        //}
        return tweets;
    }

    /**
     * Delete.
     *
     * @param a the a
     */
    public void delete(Tweet a){
        tweets.remove(a);
    }

    /**
     * Remove tweet.
     *
     * @param a the Tweet to be removed
     */
    public void removeTweet(Tweet a){
        tweets.remove(a);
    }

    /**
     * Gets the number of tweets in the TweetList .
     *
     * @param tweet the tweetList
     * @return the number of tweets in the list
     */
    public int getcount(Tweet tweet) {
        for(int i = 0; i < tweets.size(); i++){
            tweetCount += 1;
        }
        return tweetCount;
    }
}

