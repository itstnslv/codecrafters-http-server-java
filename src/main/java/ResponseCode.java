enum ResponseCode {
    OK("HTTP/1.1 200 OK"),
    NOT_FOUND("HTTP/1.1 404 Not Found");

    private String message;

    ResponseCode(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
