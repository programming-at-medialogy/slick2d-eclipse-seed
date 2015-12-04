import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkServer extends Thread  {
	
	private static ServerSocket serverSocket;
	private static final int PORT = 0;
	private static ArrayList<ClientHandler> handlers;
	
	public void run() {
		handlers = new ArrayList();
		
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException ioEx) {
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}
		
		do {
			// wait for client
			System.out.println("\nListening on port " + serverSocket.getLocalPort());
			Socket client = null;
			try {
				System.out.println("Server Ip Address: " + InetAddress.getLocalHost().getHostAddress());
				client = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nNew client accepted.\n");
	
			handlers.add(new ClientHandler(client, handlers.size()));
			handlers.get(handlers.size() - 1).start();
			ServerMain.addPlayer(handlers.size() - 1);
			ServerActions.sendId(handlers.size() - 1);

		} while (handlers.size() != 1);
	}
	
	public static void send(int id, String message) {
		System.out.println("Test");
		handlers.get(id).send(message);
	}
	
	public static void sendToAll(String message) {
		for (ClientHandler handler : handlers)
			handler.send(message);
	}
	
	public int getNumClients() {
		return handlers.size();
	}
}

class ClientHandler extends Thread {
	
	private Socket client;
	private Scanner input;
	private PrintWriter output;
	private final int id;
	private static final String exitCode = "QUIT";
	
	public ClientHandler(Socket socket, int id) {
		client = socket;
		try {
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(
			client.getOutputStream(),true);
		} catch(IOException ioEx) {
			ioEx.printStackTrace();
		}
		
		this.id = id;
	}
	
	public void run() {
		
		String received;

		do {
			received = input.nextLine();
			ServerActions.received(id, received);
		} while (!received.equals(exitCode));
		
		NetworkServer.sendToAll(exitCode);
		
		try {
			if (client!=null) {
				System.out.println("Closing down connection�");
				client.close();
			}
		} catch(IOException ioEx) {
			System.out.println("Unable to disconnect!");
		}
	}
	
	public synchronized void send(String message) {
		
		try {
			System.out.println("Sending: " + message + "\nTo: " + client.getInetAddress().getHostAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output.println(message);
	}
}