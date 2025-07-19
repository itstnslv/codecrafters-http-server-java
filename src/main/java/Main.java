import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try (var serverSocket = new ServerSocket(4221);
             var clientSocket = serverSocket.accept();
             var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             var out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            // Since the tester restarts your program quite often, setting SO_REUSEADDR
            // ensures that we don't run into 'Address already in use' errors
            serverSocket.setReuseAddress(true);

            String responseString, requestString;

            requestString = in.readLine();
            System.out.println("Request string: " + requestString);
            if (requestString != null && requestString.split(" ")[1].equals("/")) {
                responseString = "HTTP/1.1 200 OK\r\n\r\n";
            } else {
                responseString = "HTTP/1.1 404 Not Found\r\n\r\n";
            }

            out.println(responseString);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
