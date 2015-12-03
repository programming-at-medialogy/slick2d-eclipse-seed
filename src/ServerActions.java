import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * Class containing all actions the server will do. Sends messages to and
 * receives messages from the clients
 * 
 * @author Frederik Emil
 *
 */

public class ServerActions {
	static Gson gson;
	static Position rStartPos;
	static int expectingRoad;
	static int startIndex;
	/**
	 * An object of this class is never instantiated, so this function should be
	 * called in order to initialize certain variables.
	 */
	static void initActions() {
		gson = new Gson();
		expectingRoad = -1;
		startIndex = 0;
	}

	/**
	 * Called when game is started. Generates map and updates clients
	 */
	static void generateMap() {
		Hexagon[] hexagons = Hexagon.generateMap();

		String message;
		for (Hexagon hexagon : hexagons) {
			message = "Hexagon " + gson.toJson(hexagon);
			NetworkServer.sendToAll(message);
		}
	}
	
	public static void sendPlayerAmount() {
		NetworkServer.sendToAll("PlayerNums " + GameData.players.size());
	}

	static void collectResources() {
		int dieRoll = Dice.dice1 + Dice.dice2;
		for (int i = 0; i < GameData.players.size(); i++) {
			for (int j = 0; j < GameData.buildings.size(); j++) {
				if (GameData.buildings.get(j).PLAYER == GameData.players.get(i).NUMBER) {
					Hexagon[] nearbyHexagons = GameData.buildings.get(j).POSITION.getNearbyHexagons();
					for (int k = 0; k < nearbyHexagons.length; k++) {
						if (nearbyHexagons[k].NUMBER == dieRoll && !nearbyHexagons[k].isRobbed()) {
							if (GameData.buildings.get(j).isUpgraded()) {
								GameData.players.get(i).resources[nearbyHexagons[k].TYPE.toInt()] += 2;
								GameData.players.get(i).resourceAmount += 2;

							}
							if (nearbyHexagons[k].TYPE != ResourceType.DESERT && nearbyHexagons[k].isRobbed()) {
								GameData.players.get(i).resources[nearbyHexagons[k].TYPE.toInt()]++;
								GameData.players.get(i).resourceAmount++;
							}
						}
					}
				}
			}
			GameData.players.get(i).updateResAmount();
		}
		
		updatePlayerResources();
	}

	private static void updatePlayerResources() {
		for (int i = 0; i < GameData.players.size(); i++) {
			ArrayList<Integer> resources = new ArrayList<>();
			for (int j = 0; j < GameData.players.get(i).resources.length; j++)
				resources.add(GameData.players.get(i).resources[j]);
			
			String message = gson.toJson(resources, new TypeToken<ArrayList<Integer>>(){}.getType());
			NetworkServer.sendToAll("Resource " + i + " " + message);
		}
	}

	static void addDevelopmentCard(int ID) {
			GameData.players.get(ID).devCard[DevelopmentCardDeck.BuyCard().toInt()]++;
		}
	
	public static CardType buyCard(int ID) {
		if (DevelopmentCardDeck.cards.size() != 0 && GameData.players.get(ID).resources[ResourceType.CORN.toInt()] >= 1
				&& GameData.players.get(ID).resources[ResourceType.ROCK.toInt()] >= 1
				&& GameData.players.get(ID).resources[ResourceType.SHEEP.toInt()] >= 1) {
			CardType returnCard = DevelopmentCardDeck.cards.get(0);
			DevelopmentCardDeck.cards.remove(0);
			return returnCard;
		}
		System.out.println("No card");
		return null;
	}
	
	static void playDevelopmentCard(CardType type, int ID) {
		switch(type) {
		case KNIGHT:
			//Move Robber
			//Take 1 resourceCard
			break;
		case VICTORYPOINT:
			GameData.players.get(ID).points++;
			break;
		case YEAROFPLENTY:
			//Missing graphical representation
			//GameData.players.get(ID).addResource();
			break;
		case ROADBUILD:
			//Missing graphical representation
			//Road.buildRoad(Position startPos, Position endPos, ID);
			break;
		case MONOPOLY:
			//Select a resource whicht the other players must hand over.
			break;
		}
	}


	/**
	 * Method called when a message is received from a client
	 * 
	 * @param clientId
	 *            Index of the player that has send the message
	 * @param message
	 *            The message received
	 */
	public synchronized static void received(int clientId, String message) {
		System.out.println(message);
		if (message.equals("Collect")) {
			collectResources();
			String outMessage = gson.toJson(GameData.players);
			NetworkServer.sendToAll("Collect " + outMessage);
		}
		else if (message.equals("addDevelop")) {
			addDevelopmentCard(clientId);
			buyCard(clientId);
			String outMessage = gson.toJson(GameData.players);
			NetworkServer.sendToAll("Collect " + outMessage);
		}
		else if (message.equals("PlayDevelop")){
			//Missing Graphical Representation
			//playDevelopmentCard();
		}
		
		else if (message.equals("Roll")) {
			NetworkServer.sendToAll("Roll1 " + Dice.RollDice(1));
			NetworkServer.sendToAll("Roll2 " + Dice.RollDice(2));
			collectResources();
		}
		
		else if (expectingRoad == clientId) {
			System.out.println("Road");
			Position rEndPos = gson.fromJson(message, Position.class);
			if (Road.buildRoad(Position.assignPosition(rStartPos.DIVISION, rStartPos.INDEX), Position.assignPosition(rEndPos.DIVISION, rEndPos.INDEX), clientId) != null) {
				NetworkServer.sendToAll("Road " + clientId + " " + gson.toJson(rStartPos));
				NetworkServer.sendToAll(message);
			}
			expectingRoad = -1;
		} else if (clientId == GameData.turn && message.equals("rollDice")) {
			
		} else if (message.equals("End turn")) {
			GameData.turn = (GameData.turn + 1) % GameData.players.size();
			NetworkServer.sendToAll("Turn " + GameData.turn);
		}

		else {
		
			String objectType = "";
			int jsonIndex = 0;
			for (int i = 0; i < message.length() && !Character.isSpaceChar(message.charAt(i)); i++) {
				objectType += message.charAt(i);
				jsonIndex = i + 2;
			}
			if (jsonIndex >= message.length()) {
				System.out.println("ERROR ON LINE: " + message);
				return;
			}
	
			message = message.substring(jsonIndex);
	
			if (objectType.equals("Building")) {
				Position inPos = gson.fromJson(message, Position.class);
				if (Building.build(inPos, clientId) != null)
					NetworkServer.sendToAll("Building " + clientId + " " + message);
			} else if (objectType.equals("Upgrade")) {
				Position inPos = gson.fromJson(message, Position.class);
				if (Building.getByPosition(inPos).upgrade())
					NetworkServer.sendToAll("Upgrade " + clientId + inPos);
			} else if (objectType.equals("Road")) {
				rStartPos = gson.fromJson(message, Position.class);
				expectingRoad = clientId;
			} else if (objectType.equals("Chat")) {
				NetworkServer.sendToAll("Chat " + clientId + " " + message);
			} else if (objectType.equals("Trade")) {
				GameData.tObject = gson.fromJson(message, TradeObject.class);
				NetworkServer.sendToAll("Trade " + message);
			} else if (objectType.equals("TradeAccept")) {
				GameData.tObject = gson.fromJson(message, TradeObject.class);
				GameData.players.get(
						GameData.tObject.initPlayer).resources[GameData.tObject.hasType] -= GameData.tObject.has.length;
				GameData.players.get(
						GameData.tObject.initPlayer).resources[GameData.tObject.wantsType] += GameData.tObject.wants.length;
				GameData.players.get(
						GameData.tObject.acceptPlayer).resources[GameData.tObject.hasType] += GameData.tObject.has.length;
				GameData.players.get(
						GameData.tObject.acceptPlayer).resources[GameData.tObject.wantsType] -= GameData.tObject.wants.length;
				NetworkServer.sendToAll("TradeAccept " + message);
			} else if (objectType.equals("Name")){
				System.out.println("Name received");
				GameData.players.set(clientId, new Player(message, clientId));
				startIndex++;
				if (startIndex == GameData.players.size()) {
					String outMessage = gson.toJson(GameData.players);
					NetworkServer.sendToAll("Players " + outMessage);
				}
			} else if (objectType.equals("Robber")) {
				System.out.println("Received robber");
				NetworkServer.sendToAll("Robber " + message);
			} 

		}
	}
	
	public static void nameRequest() {
		NetworkServer.sendToAll("SendName");
	}

	public static void sendId(int i) {
		NetworkServer.send(i, "ID " + i);
	}
}
