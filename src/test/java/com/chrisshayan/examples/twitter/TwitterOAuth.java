package com.chrisshayan.examples.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chris on 1/15/16.
 */
public class TwitterOAuth {
    public static void main(String[] args) throws TwitterException, IOException {
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("cArSCa5hJmwSQuyPJ8otQ", "UMBefvvvs0E5zG9QzmTikHw6iPOnijA7bw2y1goD4");
        RequestToken requestToken = twitter.getOAuthRequestToken();
        AccessToken accessToken = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken) {
            System.out.println("Open the following URL and grant access to your account:");
            System.out.println("getAuthorizationURL=" + requestToken.getAuthorizationURL());
            System.out.println("getAuthenticationURL = " + requestToken.getAuthenticationURL());
                    System.out.print("Enter the PIN(if available) or just hit enter.[PIN]:");
            String pin = br.readLine();
            try{
                if(pin.length() > 0){
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                }else{
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if(401 == te.getStatusCode()){
                    System.out.println("Unable to get the access token.");
                }else{
                    te.printStackTrace();
                }
            }
        }
        //persist to the accessToken for future reference.
        storeAccessToken((int) twitter.verifyCredentials().getId(), accessToken);
        Status status = twitter.updateStatus(args[0]);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
        System.exit(0);
    }
    private static void storeAccessToken(int useId, AccessToken accessToken){
        System.out.println("accessToken.getToken() = " + accessToken.getToken()); //24154138-zSt5cdc85urDveQdXYoMDqIp5IN9CtQEUsybcxrAP
        System.out.println("accessToken = " + accessToken.getTokenSecret()); //wbDqoGklIu9XXNOOL7urwA1J9p4vzkQIMWsAGqi9MvYK0
    }

}
