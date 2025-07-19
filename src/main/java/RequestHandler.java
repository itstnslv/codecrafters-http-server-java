import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

class RequestHandler {

    private final BufferedReader input;

    RequestHandler(BufferedReader input) {
        this.input = input;
    }

    String handle() throws IOException {
        String responseString, requestString;

        requestString = input.readLine();
        System.out.println("Request string: " + requestString);
        var urlPath = requestString.split(" ")[1];
        if (urlPath.equals("/")) {
            responseString = new ResponseBuilder()
                    .statusLine(ResponseCode.OK)
                    .build();
        } else if (urlPath.contains("/echo")) {
            var pathVariable = urlPath.substring(urlPath.lastIndexOf("/") + 1);
            responseString = new ResponseBuilder()
                    .statusLine(ResponseCode.OK)
                    .headers(Map.of("Content-Type", "text/plain",
                            "Content-Length", String.valueOf(pathVariable.length())))
                    .body(pathVariable)
                    .build();
        } else {
            responseString = new ResponseBuilder()
                    .statusLine(ResponseCode.NOT_FOUND)
                    .build();
        }

        return responseString;
    }
}
