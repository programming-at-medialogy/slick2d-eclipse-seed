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
        System.out.println("debil");
        
    this.server = server;
    this.clientSocket = clientSocket;
  }

  public void run() {
    System.out.println("Lox");
    
    BufferedReader bufferedReader = null;
	try {
    System.out.println("Lox2");
		bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	} catch (IOException e1) {
	      System.out.println("Lox3");
		e1.printStackTrace();
	}

    String inputLine;

  try {
    System.out.println("Started reading shit");
		while((inputLine = bufferedReader.readLine()) != null) {
      server.sendMessage(clientSocket);
      System.out.println("labas");
		  System.out.println(inputLine);
    }
    System.out.println("Shit is done");
	} catch (IOException e) {
		
		e.printStackTrace();
	}
  }
}
