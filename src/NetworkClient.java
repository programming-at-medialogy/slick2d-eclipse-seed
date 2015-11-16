import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class NetworkClient {
	
	//Port number
	private static final int PORT = 80;
	
	private static InetAddress serverIP;
	private static String hostIPName; 
	
	/**Active objects
	 * 
	 * clientSocket grants access to the server.
	 */
	private static Socket clientSocket;
	private static BufferedReader serverReader;
	private static PrintWriter serverOutput;
	
	public static void main(String arg[]) {
	
		try {
			
			serverIP = InetAddress.getLocalHost();
			hostIPName = serverIP.getHostName();
			hostIPName = serverIP.getHostAddress();
			
			System.out.println("The IP is " + serverIP);
			System.out.println("The host is " + hostIPName);
			
			Scanner userInput = new Scanner(System.in); //Test
			
			int number = userInput.nextInt(); //Test
			
			System.out.println(number); //Test
			
			clientSocket = new Socket(serverIP, PORT);
			serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			serverOutput = new PrintWriter(clientSocket.getOutputStream());
			
			String temp = serverReader.readLine();
			
			serverOutput.println("Host: " + serverIP);
			
			//String used to print each line in the document until the document ends.
			String modifiedSentence;
			
			while((modifiedSentence = serverReader.readLine()) != null) {
				
				System.out.println(modifiedSentence);
				
			}
			
			clientSocket.close();
			
		} 
		
		catch (IOException e){
			
			System.out.println("Error connecting to client.");
			
			e.printStackTrace();
			
		}
	
	}
	
}
