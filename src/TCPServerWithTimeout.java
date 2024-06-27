// TCPServerWithTimeout.java
import java.io.*;
import java.net.*;

public class TCPServerWithTimeout {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000");
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(5000); // Indstiller en timeout på 5 sekunder
            System.out.println("New client connected");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            try {
                String message = reader.readLine();
                System.out.println("Received: " + message);
                writer.println("Echo: " + message);
            } catch (SocketTimeoutException e) {
                System.out.println("Read timed out");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}