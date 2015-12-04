import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ServerMain {
	
	static JFrame myFrame;
	public static void main(String args[]){ 
		
		
		
		System.out.println("Server is running");
		ServerActions.initActions();
		NetworkServer networkServer = new NetworkServer();
		networkServer.run();
		GameData.initGameData(networkServer.getNumClients());
		ServerActions.sendPlayerAmount();
		ServerActions.nameRequest();
		ServerActions.generateMap();
	}

	public static void displayStuff(String string, String string2) {
		JLabel textLabel = new JLabel(string);
		textLabel.setBounds(10, 10, 200, 20);
		JLabel textLabel2 = new JLabel(string2);
		System.out.println("Test");
		myFrame = new JFrame("Settlers Server"); 
		myFrame.add(textLabel);
		myFrame.add(textLabel2);
		//myFrame.add;
		myFrame.setSize(300,400); 
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		myFrame.setVisible(true); 
		
	} 
	
	public static void addPlayer(int id) {
		
	}
	

	
/*
	public ServerMain(String title) {
		super(title);
	}

	static boolean test = false;
	static String port, ip;
	public static void start() {
		
		try {
			AppGameContainer game = new AppGameContainer(new ServerMain("Settlers Server"));
			game.setDisplayMode(Windows.scWidth,Windows.scHeight, Windows.fullscreen); 
			game.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	
	public static void addPlayer(int id) {
		//GameData.players.add(new Player(id));
	}


	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		if (test) {
			g.drawString(port, 0, 0);
			g.drawString(ip, 0, 200);
		}
	}


	@Override
	public void init(GameContainer arg0) throws SlickException {
		System.out.println("Server is running");
		ServerActions.initActions();
		NetworkServer networkServer = new NetworkServer();
		networkServer.run();
		GameData.initGameData(networkServer.getNumClients());
		ServerActions.sendPlayerAmount();
		ServerActions.nameRequest();
		ServerActions.generateMap();
	}


	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}


	public static void displayStuff(String string, String string2) {
		test = true;
		port = string;
		ip = string2;
	}
	*/
}
