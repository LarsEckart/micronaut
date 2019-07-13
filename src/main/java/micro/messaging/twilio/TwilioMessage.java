package micro.messaging.twilio;

class TwilioMessage {

    String To;
    String From;
    String Body;

    public TwilioMessage(String to, String from, String body) {
        To = to;
        From = from;
        Body = body;
    }
}
