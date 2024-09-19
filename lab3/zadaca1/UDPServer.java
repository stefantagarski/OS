import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(8080); // Server listening on port 8080
            byte[] receiveBuffer = new byte[1024];
            boolean isLoggedIn = false;

            System.out.println("UDP Server is running and waiting for clients...");

            while (true) {
                // Receive data from client
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                if (receivedMessage.equals("login")) {
                    isLoggedIn = true;
                    String response = "logged in";
                    byte[] sendBuffer = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                    serverSocket.send(sendPacket);
                    System.out.println("Client logged in.");
                } else if (isLoggedIn && receivedMessage.equals("logout")) {
                    String response = "logged out";
                    byte[] sendBuffer = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                    serverSocket.send(sendPacket);
                    System.out.println("Client logged out.");
                    break; // Exit server loop
                } else if (isLoggedIn) {
                    // Echo back the same message
                    byte[] sendBuffer = receivedMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                    serverSocket.send(sendPacket);
                    System.out.println("Received and echoed back: " + receivedMessage);
                }
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
