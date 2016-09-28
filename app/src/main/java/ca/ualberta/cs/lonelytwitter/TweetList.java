package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzphung on 9/27/16.
 */
public class TweetList {
    List<Tweet> tweets = new ArrayList<Tweet>();
    private int tweetCount = 0;
    public void add(Tweet tweet){
        //Illegal argument exception
        if (tweets.contains(tweet)){
            throw new IllegalArgumentException("There is already the same tweet in the list.");
        }
        tweets.add(tweet);

    }

    public boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }

    public Tweet getTweet(int i) {
        return tweets.get(i);
    }

    public List getTweets(){
        //for(int i = 0; i < tweets.size(); i++){
          //  return tweets.get(i);
        //}
        return tweets;
    }

    public void delete(Tweet a){
        tweets.remove(a);
    }

    public void removeTweet(Tweet a){
        tweets.remove(a);
    }

    public int getcount(Tweet tweet){
        for(int i = 0; i < tweets.size(); i++){
            tweetCount += 1;
        }
        return tweetCount;
    }
}

