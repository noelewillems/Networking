// Takes GET and PUT commands
// When client creates Packet to GET or PUT, set ttl to -1. 
// When a server receives this packet, change ttl before forwarding. Decrement each server by arrival. If it reaches 0, discard request.
import java.io.*;
import java.net.*;
import java.util.*;

class DHTClient {
    public static void main(String[] args) throws Exception {
        String portNum = args[0]; // Port of server the client sends request to
        DatagramSocket clientSocket = new DatagramSocket(6000); // Listen on port 6000
        System.out.println("Enter a command:");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print(">");
            String[] userInput = sc.nextLine().split(" ");

            // Uppercase the command then check to make sure user input is a valid command
            String cmd = userInput[0].toUpperCase();
            if(!cmd.equals("GET") && !cmd.equals("PUT")) {
                System.out.println("Incorrect format; correct format is GET <word> or PUT <word> <definition>");
                continue;
            }

            // If valid command, check if a GET or PUT, and check the command format.
            // GET
            if(cmd.equals("GET")) {
                if(userInput.length != 2) { 
                    System.out.println("Incorrect format; correct format is GET <word>");
                    continue;
                } else {
                    String word = userInput[1];
                    Packet p = new Packet(-1, 6000, Command.GET, 0, word, "");
                    byte[] packetBytes = p.convertToBytes();
                    InetAddress IPAddress = InetAddress.getByName("localhost");
                    DatagramPacket sendPacket = new DatagramPacket(packetBytes, packetBytes.length, IPAddress, 7000);
                    clientSocket.send(sendPacket);
                    continue;
                }
            // PUT
            } else {
                // Send userInput[1] as word and send range of userInput 2 --> length as definition
                String word = userInput[1];
                String[] a = Arrays.copyOfRange(userInput, 2, userInput.length);
                StringBuilder def = new StringBuilder();
                for(int i = 0; i < a.length; i++) {
                    def.append(a[i]);
                    def.append(" ");
                }
                String definition = def.toString();
                Packet p = new Packet(-1, 6000, Command.PUT, 0, word, definition);
                byte[] packetBytes = p.convertToBytes();
                InetAddress IPAddress = InetAddress.getByName("localhost");
                DatagramPacket sendPacket = new DatagramPacket(packetBytes, packetBytes.length, IPAddress, 7000);
                clientSocket.send(sendPacket);
            }
            System.out.println();
        }
    }
}