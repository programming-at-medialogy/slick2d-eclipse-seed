

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetworkServer {
	
	private static final int PORT = 82;
	
	private static String sentence;
	private static String tempSentence;
	
	private static ServerSocket serverSocket;
	private static Socket clientLink;
	private static Scanner socketReader;
	
	private static PrintWriter serverOutput;
	
	public static void main(String args[]) throws IOException {
		
		serverSocket = new ServerSocket(PORT);
		clientLink = serverSocket.accept();
		
		System.out.println("The server has now been opened.");
		System.out.println("Waiting for input...");
		
		socketReader = new Scanner(clientLink.getInputStream());
		sentence = socketReader.nextLine();
		
		/*
		System.out.println("A new number has been accepted from the client: " + num);
		System.out.println("Performing mathematical operation...");
		*/
		
		tempSentence = sentence.toUpperCase();
		
		System.out.println("The new sentence is now: " + tempSentence);
		
		serverOutput = new PrintWriter(clientLink.getOutputStream());
		serverOutput.println(tempSentence);
		serverOutput.flush();
		
	}

}
