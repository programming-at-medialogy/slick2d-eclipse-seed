package gameEngine;
import java.io.*;
import java.net.*;

public class Server {

	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;
	static Users[] user = new Users[10];	//Need to cap it at two
	
	public static void main (String[] args) throws Exception 
	{
		System.out.println("Starting server...");
		serverSocket = new ServerSocket(7777);
		System.out.println("Server is running!");
		
		while(true)
		{
		socket = serverSocket.accept();
		for (int i = 0; i < 10; i++) 
			{
				System.out.println("Connected from: " + socket.getInetAddress());
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream()); 
					if (user[i] == null) 
						{
							user[i] = new Users(out, in, user);	
							Thread thread = new Thread(user[i]);
							thread.start(); 
							break;
						}
		
			}
		}
	}
}
