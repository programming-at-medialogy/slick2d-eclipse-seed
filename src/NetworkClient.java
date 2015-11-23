import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkClient {
	
	private static final int PORT = 59482;
	
	private static String sentence;
	private static String tempSentence;
	
	private static String hostName;
	private static InetAddress hostAddress;
	
	private static Scanner userInput;
	private static Socket clientSocket;
	private static Scanner socketReader;
	
	private static PrintWriter clientOutput;
	
	public static void main(String args[]) throws IOException, UnknownHostException {
		
		hostName = "ThatGuyWithTheBeard";
		System.out.println(hostName);
		
		hostAddress = InetAddress.getByName(hostName);
		
		System.out.println(hostAddress.getHostAddress());
		
		
		//This currently gets the IP of the local host. 
		//Should not get local host, but the actual host, which is on a separate computer.
		//host = InetAddress.;
		
		//This sets up the program's ability to read any input that is parsed into it.
		userInput = new Scanner(System.in);
		clientSocket = new Socket(hostAddress, PORT);
		socketReader = new Scanner(clientSocket.getInputStream());
		
		//This is to prompt the user to provide an input.
		System.out.println("Enter a sentence : ");
		sentence = userInput.nextLine();
		
		//This parses information from the client to the server.
		clientOutput = new PrintWriter(clientSocket.getOutputStream());
		clientOutput.println(sentence);
		clientOutput.flush();
		
		//This prints out what is parsed back to the client from  the server.
		tempSentence = socketReader.nextLine();
		System.out.println(tempSentence);
		
		
	}	

}
