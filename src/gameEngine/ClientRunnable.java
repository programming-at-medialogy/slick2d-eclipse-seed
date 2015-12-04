package gameEngine;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ClientRunnable implements Runnable {

  protected Socket clientSocket = null;
  protected Server server = null;

  public ClientRunnable(Socket clientSocket, Server server){
        System.out.println("tst");
        
    this.server = server;
    this.clientSocket = clientSocket;
  }

  public void run() {
    System.out.println("test1");
    
    BufferedReader bufferedReader = null;
	try {
    System.out.println("test2");
		bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	} catch (IOException e1) {
	      System.out.println("test3");
		e1.printStackTrace();
	}

    String inputLine;

  try {
    System.out.println("Started reading");
		while((inputLine = bufferedReader.readLine()) != null) {
      server.sendMessage(clientSocket);
      System.out.println("test4");
		  System.out.println(inputLine);
    }
    System.out.println("Done");
	} catch (IOException e) {
		
		e.printStackTrace();
	}
  }
}
