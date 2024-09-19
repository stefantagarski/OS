import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPServer {
    private static AtomicInteger messageCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected.");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String clientMessage = in.readLine();
                if (!"login".equalsIgnoreCase(clientMessage)) {
                    clientSocket.close();
                    return;
                }

                out.println("logged in");
                String message;
                while ((message = in.readLine()) != null) {
                    if ("logout".equalsIgnoreCase(message)) {
                        out.println("logged out");
                        break;
                    }

                    int totalMessages = messageCounter.incrementAndGet();
                    out.println("echo: " + message);
                    System.out.println("Total messages received: " + totalMessages);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
