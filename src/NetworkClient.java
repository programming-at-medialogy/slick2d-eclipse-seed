import java.io.*;
import java.net.*;
import java.util.*;

public class NetworkClient
{
	private static InetAddress host;
	private static int port;
	private static Scanner userEntry;
	private static ServerHandler handler;
	
	public static void startClient(String hostIP, String hostPort) throws IOException {
		// setup user input scanner
		userEntry = new Scanner(System.in);
		
		// get host ip
		/*try {
			System.out.println("Enter host IP: ");
			host = InetAddress.getByName(userEntry.nextLine());
		} catch(UnknownHostException uhEx) {
			System.out.println("\nHost ID not found!\n");
			System.exit(1);
		}*/
		
		host = InetAddress.getByName(hostIP);
		
		// get host port
		//System.out.print("Enter host port: ");
		port = Integer.parseInt(hostPort);
		
		//setup socket
		Socket socket = new Socket(host, port);
		
		// start serverhandler
		handler = new ServerHandler(socket);
		handler.start();
	}
	
	public static void sendMessage(String message) {
		handler.sendMessage(message);
	}
	
	public static void sendMessage() {
		System.out.print("Enter the line to send to the server: ");
		String message = userEntry.nextLine();
		handler.sendMessage(message);
	}
}

class ServerHandler extends Thread {
	
	private static Socket server;
	private Scanner input;
	private PrintWriter output;
	private static final String exitCode = "QUIT";
	
	ServerHandler(Socket socket) {
		server = socket;
		
		try {
			input = new Scanner(server.getInputStream());
			output = new PrintWriter(server.getOutputStream(),true);
		} catch(IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// listen for messages
		String received;
		do {
			received = input.nextLine();
			Main.received(received);
		} while (!received.equals(exitCode));
		
		try {
			if (server!=null) {
				System.out.println("Closing down connection…");
				server.close();
			}
		} catch(IOException ioEx) {
			System.out.println("Unable to disconnect!");
		}
	}
	
	public void sendMessage(String message) {
		output.println(message);
	}
}