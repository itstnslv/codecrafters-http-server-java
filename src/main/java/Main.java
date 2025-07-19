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

            var handler = new RequestHandler(in);
            var response = handler.handle();
            out.println(response);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
