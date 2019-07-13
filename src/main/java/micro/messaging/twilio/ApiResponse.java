package micro.messaging.twilio;

public class ApiResponse {

    private String sid;
    private String date_created;
    private String date_updated;
    private String to;
    private String from;
    private String body;
    private String error_code;
    private String error_message;

    public String getSid() {
        return sid;
    }

    public ApiResponse setSid(String sid) {
        this.sid = sid;
        return this;
    }

    public String getDate_created() {
        return date_created;
    }

    public ApiResponse setDate_created(String date_created) {
        this.date_created = date_created;
        return this;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public ApiResponse setDate_updated(String date_updated) {
        this.date_updated = date_updated;
        return this;
    }

    public String getTo() {
        return to;
    }

    public ApiResponse setTo(String to) {
        this.to = to;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public ApiResponse setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getBody() {
        return body;
    }

    public ApiResponse setBody(String body) {
        this.body = body;
        return this;
    }

    public String getError_code() {
        return error_code;
    }

    public ApiResponse setError_code(String error_code) {
        this.error_code = error_code;
        return this;
    }

    public String getError_message() {
        return error_message;
    }

    public ApiResponse setError_message(String error_message) {
        this.error_message = error_message;
        return this;
    }
}
