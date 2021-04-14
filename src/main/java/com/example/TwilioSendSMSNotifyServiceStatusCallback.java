package com.example;

import com.twilio.Twilio;
import com.twilio.rest.notify.v1.service.Notification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwilioSendSMSNotifyServiceStatusCallback {

    public static void main(String[] args) {

        Twilio.init(
            System.getenv("TWILIO_ACCOUNT_SID"),
            System.getenv("TWILIO_AUTH_TOKEN"));

        String bindingFormat = "{\"binding_type\": \"sms\", \"address\": \"%s\"}";

        Stream<String> numbers = Stream.of(
            "Recipient #1 Phone number",
            "Recipient #2 Phone number",
            "Recipient #3 Phone number" // etc
        );

        List<String> bindings = numbers
            .map(phoneNumber -> String.format(bindingFormat, phoneNumber))
            .collect(Collectors.toList());

        Notification notification = Notification.creator(
            System.getenv("TWILIO_NOTIFY_SERVICE_SID"))
            .setToBinding(bindings)
            .setBody("Multiple SMS sent in one API request using a Notify Service 🎉")
            .setSms(Map.of("status_callback", "<YOUR STATUS CALLBACK URL>"))
            .create();

        System.out.println(notification.getSid());

    }

}
