import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkClient {
	
	private static final int PORT = 82;
	
	private static int num;
	private static int temp;
	
	private static InetAddress host;
	
	private static Scanner userInput;
	private static Socket clientSocket;
	private static Scanner socketReader;
	
	private static PrintWriter clientOutput;
	
	public static void main(String[] args) throws IOException, UnknownHostException {
		
		//This currently gets the IP of the local host. 
		host = InetAddress.getLocalHost();
		
		//This sets up the program's ability to read any input that is parsed into it.
		userInput = new Scanner(System.in);
		clientSocket = new Socket(host, PORT);
		socketReader = new Scanner(clientSocket.getInputStream());
		
		//This is to prompt the user to provide an input.
		System.out.println("Enter a number: ");
		num = userInput.nextInt();
		
		//This parses information from the client to the server.
		clientOutput = new PrintWriter(clientSocket.getOutputStream());
		clientOutput.println(num);
		clientOutput.flush();
		
		//This prints out what is parsed back to the client from  the server.
		temp = socketReader.nextInt();
		System.out.println(temp);
		
	}

}
