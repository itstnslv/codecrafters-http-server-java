import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try (var serverSocket = new ServerSocket(4221);
             var clientSocket = serverSocket.accept();
             var in = clientSocket.getInputStream();
             var out = clientSocket.getOutputStream()
        ) {
            // Since the tester restarts your program quite often, setting SO_REUSEADDR
            // ensures that we don't run into 'Address already in use' errors
            serverSocket.setReuseAddress(true);

            var br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();

            String response = "";
            if (line != null) {
                var urlPath = line.split(" ");
                if (urlPath[1].equals("/")) {
                    response = "HTTP/1.1 200 OK\r\n\r\n";
                } else {
                    response = "HTTP/1.1 404 Not Found\r\n\r\n";
                }
            }

            out.write(response.getBytes());
            out.flush();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
