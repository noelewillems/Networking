// Port 7000
import java.net.*;

class DHTServer {
    public static void main(String[] args) throws Exception {
        // Check if args are correct.
        if(args.length != 5) {
            System.out.println("Incorrect - please format like java DHTServer <name of server> <listen port> <next server port> <start of range> <end of range>");
            System.exit(0);
        }

        String serverName = args[0];
        String listenPort = args[1];
        String nextServerPort = args[2];
        int startRange = Integer.parseInt(args[3]);
        int endRange = Integer.parseInt(args[4]);

        DatagramSocket serverSocket = new DatagramSocket(7000);
        byte[] receiveData = new byte[1024];
        while(true) {

            // Deserialize into packet
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            Packet p = Packet.covertFromBytes(receivePacket.getData());

            // If ttl of packet is 0, discard and report error
            if(p.ttl == 0) {
                p.ttl = -2; // Lets the client know to discard it
                DatagramPacket sendPacket = new DatagramPacket(p.convertToBytes(), p.convertToBytes().length);
                serverSocket.send(sendPacket);
                continue;
            }

            // If hash has not been created yet, create one.
            if(p.hash == 0) {
                p.hash = p.getHash();
            }

            // Determine if this server is responsible for the command. Aka, if hash value is in range of values.
            
            // If responsible, execute command and report back to client.

            // If not responsible, decrement ttl and forward packet to next server.
        }
    }
}
