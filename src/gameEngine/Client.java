import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client 
{
	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	
	public static void main (String[] args) throws Exception
    {
		System.out.println("Connecting..");
		socket = new Socket("localhost", 7777);
		System.out.println("Connected");
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		Input input = new Input(in);
		Thread thread = new Thread(input);
		thread.start();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name and press 'Enter'...");
		String name = scanner.nextLine();
		out.writeUTF(name);
		
		while(true) {
			String sendMessage = scanner.nextLine();
			out.writeUTF(sendMessage);
		}
		
    }	
		
}
