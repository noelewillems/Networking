// Noel Willems COS 327 Lab 0
// UDP client example in Java

import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception {
        int portNum = 0;
        String url = "";

        // If only 1 arg, assume local host and that arg = port
        if(args.length == 1) {
            url = "localhost";
            portNum = Integer.parseInt(args[0]);
            if(portNum < 0 || portNum > 65535) {
                System.err.println("Invalid port number!");
                System.exit(0);
            }
        // If 2 args, 1st arg is port and 2nd arg is url
        } else if(args.length == 2) {
            portNum = Integer.parseInt(args[1]);
            if(portNum < 0 || portNum > 65535) {
                System.err.println("Invalid port number!");
                System.exit(0);
            }
            url = args[0];
        // If incompatible # of args, exit
        } else if(args.length < 1 || args.length > 2) {
            System.err.println("Incorrect args!");
            System.exit(0);
        }

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(url);

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, portNum);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}