// Noel Willems COS 327 Lab 0
// UDP server example in Java

import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        if(args.length != 1) {
            System.err.println("Please enter 1 arg as a port number!");
            System.exit(0);
        }

        DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(args[0]));
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());

            InetAddress IPAddress = receivePacket.getAddress();

            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();

            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            serverSocket.send(sendPacket);
        }
    }
}