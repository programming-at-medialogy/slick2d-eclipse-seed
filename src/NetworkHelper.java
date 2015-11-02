import java.io.*;
import java.net.*;
import java.util.*;

/**
 * NetworkHelper for client
 * @author frede_000
 *
 */
public class NetworkHelper {
	private static InetAddress host;
	private static final int PORT = 1234; 
	
	public static void main(String[] args){ //start client
			
		try{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("HostID not found!");
			System.exit(1);
		}
		accessServer();
	}
	
	public static void accessServer()
	{
		Socket link = null;
		Scanner input = null;
		Scanner userEntry = null;
		
		try{
			link = new Socket(host,PORT);
			input = new Scanner(link.getInputStream());
			
			PrintWriter output = new PrintWriter(link.getOutputStream(), true);
			
			userEntry = new Scanner(System.in);
			
			String message, response; 
			do{
				System.out.print("Enter message: ");
				message = userEntry.nextLine();
				output.println(message);
				response = input.nextLine();
				System.out.println("\nSERVER> "+response);
			}while (!message.equals("***CLOSE***"));
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				System.out.println("\n* Closing connection *");
				link.close();
				input.close();
				userEntry.close();
			}
			catch(IOException ioEx){
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}