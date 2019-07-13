package micro.messaging.twilio;

class TwilioMessage {

    private String To;
    private String From;
    private String Body;

    public TwilioMessage(String to, String from, String body) {
        To = to;
        From = from;
        Body = body;
    }

    public String getTo() {
        return To;
    }

    public TwilioMessage setTo(String to) {
        To = to;
        return this;
    }

    public String getFrom() {
        return From;
    }

    public TwilioMessage setFrom(String from) {
        From = from;
        return this;
    }

    public String getBody() {
        return Body;
    }

    public TwilioMessage setBody(String body) {
        Body = body;
        return this;
    }
}
