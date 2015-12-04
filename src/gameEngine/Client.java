package gameEngine;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Client extends BasicGame {

	public static int boardWidth = 1920;
	public static int boardHight = 1080;
	private Shape circle = null;
	static int circleWidth;
	static int circleHeight;
	static int radius;
	Server server = new Server();
	public Player player1 = new Player("Atlanta", server.assignRole());
	public Player player2 = new Player("Atlanta", server.assignRole());

	Client(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		circle = new Circle(circleWidth, circleHeight, radius);// drawing circle

		System.out.println("Player 1 role is: " + player1.getRole());
		System.out.println("Player 1 is now in city: " + player1.currentCity.toString());

		// server.citiesOnBoard.get(5).setInfectionRate(4);
		for (int i = 0; i < 16; i++) {
			if (server.citiesOnBoard.get(i).getInfectionRate() != 0) {
				System.out.println(
						server.citiesOnBoard.get(i).getcityName() + " was infected. It's current infection rate is "
								+ server.citiesOnBoard.get(i).getInfectionRate());
			} else if (server.citiesOnBoard.get(i).getInfectionRate() == 0) {
				System.out.println(
						server.citiesOnBoard.get(i).getcityName() + " was not infected. It's current infection rate is "
								+ server.citiesOnBoard.get(i).getInfectionRate());}}}
			 // ^prints out the infected cities and the rest of cities'
				// infection rates and whether it was indected or not^

		

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {


		// Image fieldDirector = new Image("images/fieldDirector.png"); //unused
		// Image quarantineSpecialist = new
		// Image("images/quarantineSpecialist.png"); //unused
		// need to create images for the 6 role cards
		Image medic = new Image("images/medic.png");
		Image fieldDirector = new Image("images/roleCard1.png");

		
		//Image fieldDirector = new Image("images/fieldDirector.png"); //unused
		//Image quarantineSpecialist = new Image("images/quarantineSpecialist.png"); //unused
		//need to create images for the 6 role cards
		Image field = new Image("images/medic.png");

		Image dispatcher = new Image("images/dispatcher.png");
		Image researcher = new Image("images/researcher.png");
		Image scientist = new Image("images/scientist.png");
		Image consistencyPlanner = new Image("images/consistencyPlanner.png");
		Image operationsExpert = new Image("images/operationsExpert.png");
		Image player1Img = new Image("images/player1.png");
		Image player2Img = new Image("images/player2.png");
		Image background = new Image("images/board.jpg");// background image
		g.drawImage(background, 0, 0);// rendering background

		// Draw a player according vity positio !!!not complete!!!

		g.drawImage(player1Img, server.citiesOnBoard.get(server.findCity(player1.getCurrentCity())).getcityPosX(),
				server.citiesOnBoard.get(server.findCity(player1.getCurrentCity())).getcityPosY());

		// Renders the assigned role card
		if (player1.getRole() == "Medic") {
			g.drawImage(medic, 26, 700);

		} else if (player1.getRole() == "Dispatcher") {
			g.drawImage(dispatcher, 26, 700);

		} else if (player1.getRole() == "Researcher") {

			g.drawImage(researcher, 26, 700);

		} else if (player1.getRole() == "Scientist") {
			g.drawImage(scientist, 26, 700);

		} else if (player1.getRole() == "Consistency planner") {
			g.drawImage(consistencyPlanner, 26, 700);

		} else if (player1.getRole() == "Operations expert") {
			g.drawImage(operationsExpert, 26, 700);
		} else if (player1.getRole() == "Field Director") {
			g.drawImage(fieldDirector, 26, 700);
		}

		// System.out.println(Server.citiesOnBoard.get(Server.findCity(player1.getCurrentCity())).getcityPosX());
		// g.drawImage(fieldDirector, 26, 700);
		g.drawImage(player2Img, server.citiesOnBoard.get(server.findCity(player1.getCurrentCity())).getcityPosX() - 20,
				server.citiesOnBoard.get(server.findCity(player1.getCurrentCity())).getcityPosY());

		//Draw a player according vity positio !!!not complete!!!
	
		g.drawImage(player1Img, server.citiesOnBoard.get(server.findCity(player1.getCurrentCity())).getcityPosX() , server.citiesOnBoard.get(server.findCity(player1.getCurrentCity())).getcityPosY());
		
		
		//Renders the assigned role card
		if(player1.getRole() == "Medic"){
			g.drawImage(medic, 26, 700);
	
		}else if(player1.getRole() == "Dispatcher"){
			g.drawImage(dispatcher, 26, 700);
	
		}else if(player1.getRole() == "Researcher"){
		
			g.drawImage(researcher, 26, 700);
	
		}else if(player1.getRole() == "Scientist"){
			g.drawImage(scientist, 26, 700);
	
		}else if(player1.getRole() == "Consistency planner"){
			g.drawImage(consistencyPlanner, 26, 700);
	
		}else if(player1.getRole() == "Operations expert"){
			g.drawImage(operationsExpert, 26, 700);
		}
		
		//System.out.println(Server.citiesOnBoard.get(Server.findCity(player1.getCurrentCity())).getcityPosX());
		//g.drawImage(fieldDirector, 26, 700);
		//g.drawImage(player2Img, 396, 354);

		g.setColor(Color.white);
		g.fillOval(circleWidth, circleHeight, radius, radius);
		g.draw(circle);
		// g.drawString("WORKS!", 250, 200);
	}

	public static void main(String args[]) {

		Client client = new Client("Infect");

		DatagramSocket sock = null;
		int port = 9000;
		String s;
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

		try {

			int maxFPS = 60;// initialising frame rate per second
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Client("Pandemic"));
			appgc.setTargetFrameRate(maxFPS);// setting fps
			appgc.setDisplayMode(boardWidth, boardHight, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {

			sock = new DatagramSocket();

			InetAddress host = InetAddress.getByName("localhost");

			while (true) {
				// takes input and sends the packet
				echo("Enter message to send : ");
				s = (String) cin.readLine();
				byte[] b = s.getBytes();

				DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
				sock.send(dp);

				// now receive reply
				// creating buffer for receiving incoming data
				byte[] buffer = new byte[65536];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				sock.receive(reply);

				byte[] data = reply.getData();
				s = new String(data, 0, reply.getLength());

				// details for incoming data - client ip : client port - client
				// message
				echo(reply.getAddress().getHostAddress() + " : " + reply.getPort() + " - " + s);

			}
		}

		catch (IOException e) {
			System.err.println("IOException " + e);
		}
	}

	// simple function to echo data to terminal
	public static void echo(String message) {
		System.out.println(message);
	}}

