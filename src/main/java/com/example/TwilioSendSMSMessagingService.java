package com.example;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioSendSMSMessagingService {

    public static void main(String[] args) {

        Twilio.init(
            System.getenv("TWILIO_ACCOUNT_SID"),
            System.getenv("TWILIO_AUTH_TOKEN"));

        Message message = Message.creator(
            new PhoneNumber("<TO number - ie your cellphone>"),
            System.getenv("MESSAGING_SERVICE_SID"),
            "Hello from Twilio 📞")
            .setStatusCallback("https://<YOUR-STATUS-CALLBACK-URL>")
            .create();

        System.out.println(message.getSid());

    }

}
