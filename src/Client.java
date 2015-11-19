import java.net.Socket;
import java.io.PrintWriter;
public class Client
{
	private static Socket socket;
	private static PrintWriter printWriter;
	public static void main(String[] args)
	{
		try
		{
			socket = new Socket("localhost",9000);
			printWriter = new PrintWriter(socket.getOutputStream(),true);
			
			
			// Reads and prints the user name/path and the OS
			printWriter.println("Username:");
			printWriter.println( System.getProperty("user.name") ); 
			
			printWriter.println("Path:");
			printWriter.println(System.getenv("USERPROFILE")  );
			
			printWriter.println("Operating System::");
			printWriter.println( System.getProperty("os.name") );
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
