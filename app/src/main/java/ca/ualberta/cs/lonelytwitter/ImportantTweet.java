package ca.ualberta.cs.lonelytwitter;

/**
 * This is the class for ImportantTweet for the LonelyTwitter project. <p> It holds the information
 * for a tweet to know if its important or not. </p>
 * @author watts1
 * @since 1.0
 */

/**
 * Created by watts1 on 9/13/16.
 */
public class ImportantTweet extends Tweet {

    /**
     * This constructor makes a importanTweet given string parameter.
     *
     * @param message this parameter is the given string for the tweet.
     */
    public ImportantTweet(String message){
        super(message);
    }

    @Override
    public Boolean isImportant(){
        return Boolean.TRUE;
    }

}
