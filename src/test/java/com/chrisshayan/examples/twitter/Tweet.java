package com.chrisshayan.examples.twitter;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Created by chris on 1/15/16.
 */
public class Tweet {
    public static void main(String[] args) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("cArSCa5hJmwSQuyPJ8otQ", "UMBefvvvs0E5zG9QzmTikHw6iPOnijA7bw2y1goD4");
        twitter.setOAuthAccessToken(new AccessToken("24154138-zSt5cdc85urDveQdXYoMDqIp5IN9CtQEUsybcxrAP", "wbDqoGklIu9XXNOOL7urwA1J9p4vzkQIMWsAGqi9MvYK0"));

        twitter.updateStatus(new StatusUpdate("hey @execedia, thought you will like http://theLeader.io"));
    }
}
