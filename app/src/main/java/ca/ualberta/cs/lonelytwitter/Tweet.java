package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by watts1 on 9/13/16.
 */
public abstract class Tweet {
    private String message;
    private Date date;

    /**
     * This constructor makes a Tweet given string parameter.
     *
     * @param message This parameter is the given string for the tweet.
     */
    public Tweet(String message){
        this.message = message;
        this.date = new Date();
    }

    /**
     * This constructor makes a Tweet given string parameter and date parameter.
     *
     * @param message This parameter is the given string for the tweet.
     * @param date    This parameter is ths given date for the tweet.
     */
    public Tweet(String message, Date date){
        this.message = message;
        this.date = date;
    }


    /**
     * Is important boolean.
     *
     * @return the boolean
     */
    public abstract Boolean isImportant();


    /**
     * This method sets the message for the Tweet and if the message is too long, throw an exception.
     *
     * @param message This parameter is the given string for the tweet.
     * @throws TweetTooLongException the tweet is too long
     */
    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() > 140) {
            //Do something
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    @Override
    public String toString(){
        return  date.toString() + " | " + message;
    }
}
