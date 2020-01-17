package micro.messaging.twilio;

class ApiResponse {

  public final String sid;
  public final String date_created;
  public final String date_updated;
  public final String to;
  public final String from;
  public final String body;
  public final String error_code;
  public final String error_message;

  public ApiResponse(
      String sid,
      String date_created,
      String date_updated,
      String to,
      String from,
      String body,
      String error_code,
      String error_message) {
    this.sid = sid;
    this.date_created = date_created;
    this.date_updated = date_updated;
    this.to = to;
    this.from = from;
    this.body = body;
    this.error_code = error_code;
    this.error_message = error_message;
  }
}
