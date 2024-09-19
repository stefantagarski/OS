import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Server address
            int serverPort = 8080;

            // Send login message
            sendAndReceive(clientSocket, "login", serverAddress, serverPort);

            Scanner scanner = new Scanner(System.in);
            String message;
            while (true) {
                System.out.println("Enter a message (or 'logout' to quit): ");
                message = scanner.nextLine();

                // Send message to server
                sendAndReceive(clientSocket, message, serverAddress, serverPort);

                if (message.equals("logout")) {
                    break; // Exit client loop
                }
            }
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendAndReceive(DatagramSocket socket, String message, InetAddress address, int port) throws Exception {
        byte[] sendBuffer = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(sendPacket);

        // Receive response from server
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Server response: " + receivedMessage);
    }
}
