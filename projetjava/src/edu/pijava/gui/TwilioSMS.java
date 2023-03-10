/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author rimbs
 */
public class TwilioSMS {
    // Set your Twilio account SID and auth token
    public static final String ACCOUNT_SID = "ACf785d947a6ff3386444c79acfae41e4d";
    public static final String AUTH_TOKEN = "60b0f0074f4c5b09d1d4468dac6d2048";

    // Set the phone number to send the message to
    public static final String TO_NUMBER = "+21650734421";

    // Set your Twilio phone number
    public static final String FROM_NUMBER = "+15673343235";

    public static void main(String[] args) {

        // Initialize the Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send the message
        Message message = Message.creator(new PhoneNumber(TO_NUMBER), new PhoneNumber(FROM_NUMBER), "Hello Zohra").create();

        // Print the message SID
        System.out.println("Message SID: " + message.getSid());
        
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

  

    System.out.println("Message envoyé avec succès.");
    }
}
