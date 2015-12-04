package gameEngine;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Clienttt
{
	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;

	public static void main (String[] args) throws Exception
    {
		System.out.println("Connecting..");
		socket = new Socket("localhost", 9000);
		System.out.println("Connected");
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		out.write(System.getProperty("line.separator").getBytes());
		Input input = new Input(in);
		Thread thread = new Thread(input);
		thread.start();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Press ENTER");
		String name = scanner.nextLine();
		out.writeUTF(name);

		while(true) {
			String sendMessage = scanner.nextLine();
			out.writeUTF(sendMessage);
		}

    }

}
