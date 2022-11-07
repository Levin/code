import java.io.IOException;
import java.net.ServerSocket;

public class StreamSocketClientTester
{

    public static void main(String[] args)
    {
        System.out.println("... start in test-main ...");
        try {
            ServerSocket server = new ServerSocket(9009);
            StreamSocketClient client = new StreamSocketClient();
            Thread clientfred = new Thread(client);
            clientfred.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("... end in test-main ...");
    }
}
