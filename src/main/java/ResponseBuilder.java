import java.util.Map;

class ResponseBuilder {

    private static final String CRLF = "\r\n";

    private final StringBuilder response;

    ResponseBuilder() {
        response = new StringBuilder();
    }

    ResponseBuilder statusLine(ResponseCode responseCode) {
        response.append(responseCode.message());
        response.append(CRLF);
        return this;
    }

    ResponseBuilder headers(Map<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            response.append("%s: %s%s".formatted(header.getKey(), header.getValue(), CRLF));
        }
        response.append(CRLF);
        return this;
    }

    ResponseBuilder body(String body) {
        response.append(body);
        return this;
    }

    String build() {
        return response.toString();
    }
}
