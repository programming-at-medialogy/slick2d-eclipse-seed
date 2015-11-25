import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    Socket socket = new Socket("127.0.0.1", 4444);
    ObjectOutputStream out;
    ObjectInputStream in;
    PlayerInformation obj;
    Scanner scanner = new Scanner(System.in);

    public Client () throws IOException, ClassNotFoundException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        obj = new PlayerInformation();
    }

    public void run() {
        final Thread outThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    String line = scanner.nextLine();
                    if ("p1".equals(line)) {
                        obj.playerOneReady = true;
                        try {
                            out.writeObject(obj);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    if ("p2".equals(line)) {
                        obj.playerTwoReady = true;
                        try {
                            out.writeObject(obj);
                            out.flush();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    if ("p3".equals(line)) {
                        obj.playerThreeReady = true;
                        try {
                            out.writeObject(obj);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    if ("p4".equals(line)) {
                        obj.playerFourReady = true;
                        try {
                            out.writeObject(obj);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        };
    outThread.start();

    final Thread inThread = new Thread(){
        @Override
        public void run(){


            try {
                while((obj = (PlayerInformation) in.readObject()) != null){

                            System.out.println("Your player number is : " + obj.playerNumber);
                            System.out.println(obj.SOCnumberIndex[2]);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    };
    inThread.start();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        client.run();



    }
}