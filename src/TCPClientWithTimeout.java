// TCPClientWithTimeout.java
import java.io.*;
import java.net.*;

public class TCPClientWithTimeout {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(hostname, port), 5000); // Indstiller en connection timeout på 5 sekunder
            socket.setSoTimeout(5000); // Indstiller en read timeout på 5 sekunder

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println("Hello Server");
            try {
                String response = reader.readLine();
                System.out.println("Server response: " + response);
            } catch (SocketTimeoutException e) {
                System.out.println("Read timed out");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}