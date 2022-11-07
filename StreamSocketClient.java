import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;


public class StreamSocketClient implements Runnable
{
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("... start in client ...");
        try {
            System.out.println("insert ip: ");
            String ip = sc.nextLine();
            System.out.println("insert port: ");
            int port = sc.nextInt();
            Socket connection = new Socket(ip, port);
            PrintWriter out = new PrintWriter(connection.getOutputStream());

            String quit = "q";
            String move;

            do {
                System.out.println("enter method id: ");
                int methodID = sc.nextInt();
                Request request;
                switch (methodID) {
                    case 1:
                        request =  new Request((byte) 1);
                        System.out.println("numbers: ");
                        int nums1 = sc.nextInt();
                        int sum1 = 0;
                        for(int i=1;i<=nums1;i++)
                        {
                            System.out.println("number " + i + ": ");
                            sum1 += sc.nextShort();
                        }

                        System.out.println("... "+sum1+" ...");
                        request.setText("erg from client: " + sum1);
                        out.write(request.getText());
                        out.flush();
                        break;

                    case 2:
                        request =  new Request((byte) 2);
                        System.out.println("numbers: ");
                        int nums2 = sc.nextInt();
                        for (int i = 1; i <= nums2; i++)
                        {
                            System.out.println("number " + i + ": ");
                            i = sc.nextInt();
                            if (i >= 0)
                            {
                                System.out.println(i + " is positive");
                                out.write(i + " is positive");
                                out.flush();
                            }
                        }
                        break;

                    case 3:
                        request =  new Request((byte) 3);
                        out.write("... closing connection to Server ...");
                        out.flush();
                        connection.close();
                        break;

                    case 4:
                        request =  new Request((byte) 4);
                        out.write("... shut down server ...");
                        out.flush();
                }

                System.out.println("new action ?");
                move = sc.nextLine();

            } while (!(quit.equals(move)));

            System.out.println("... conn alive?: " + connection.isConnected());


        }catch (Exception e)
        {
            e.getMessage();
        }finally
        {
            System.out.println("... end in client ...");
        }
    }

    class Request
    {

        public Request(byte methodId)
        {
            this.methodId = methodId;
            System.out.println("... request initialized ...");
        }


        private byte methodId;
        private int textlen;
        private String text;
        public byte getMethodId() {return (byte) this.methodId;}
        public void setMethodId(byte v) {this.methodId = v;}
        public byte getTextlen() {return (byte) this.textlen;}
        public void setTextlen(int v) {this.textlen = v;}
        public String getText() {return this.text;}
        public void setText(String v) {this.text = v;}

    }

}
