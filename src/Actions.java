import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Class containing all actions the client can do. Sends messages to and
 * receives messages from the server
 * 
 * @author Frederik Emil
 *
 */
public class Actions {
	static Gson gson;
	static Position startR;
	static Position endR;
	static int expR;

	/**
	 * An object of this class is never instantiated, so this function should be
	 * called in order to initialize certain variables.
	 */
	static void initActions() {
		gson = new Gson();
		expR = -1;
	}

		
	/**
	 * Called when player wants to place a building in initial phase. Checks if
	 * possible and sends message to server
	 * 
	 * @param pos
	 *            Potision of the building
	 * @param player
	 *            Index of the player
	 */
	static void placeBuilding(Position pos, int player) {

		Building newBuilding = Building.build(pos, player);
		if (newBuilding != null) {
			String message = gson.toJson(pos);
			NetworkClient.sendMessage("Building " + message);
		}

	}

	/**
	 * Called when player wants to place a road in initial phase. Checks if
	 * possible and sends message to server
	 * 
	 * @param start
	 *            Start position of the road
	 * @param end
	 *            End position of the road
	 * @param player
	 *            Index of the player
	 */
	static void placeRoad(Position start, Position end, int player) {
		
		Road newRoad = Road.buildRoad(start, end, player);
		if (newRoad != null) {
			String message = gson.toJson(start);
			NetworkClient.sendMessage("Road " + message);
			String message2 = gson.toJson(end);
			NetworkClient.sendMessage(message2);
		}
	}
	
	static void endTurn() {
		NetworkClient.sendMessage("End turn");
	}
	
	static void collect(){
		NetworkClient.sendMessage("Collect");
	}
	
	static void addDevelopmentCard() {
		NetworkClient.sendMessage("addDevelop");
	}
	
	static void buyDevelopmentCard() {
		NetworkClient.sendMessage("buyDevelop");
	}
	
	static void playDevelopmentCard() {
		NetworkClient.sendMessage("PlayDevelop");
	}
	
	static void moveRobber(int hexIndex) {
		System.out.println("Sending: Robber " + hexIndex);
		NetworkClient.sendMessage("Robber " + hexIndex);
	}

	/**
	 * Called when a player wants to propose a trade Checks if possible and
	 * sends message to server
	 * 
	 * @param trade
	 *            The TradeObject containing info on the trade
	 * @param player
	 *            Index of the player
	 */
	static void initiateTrade(TradeObject trade) {
		// Method to use when the player wants to trade with other players
		// Check if player have funds
		for (int i = 0; i < trade.has.length; i++) {
			if (GameData.players.get(GameData.ownIndex).resources[i] < trade.has[i]) {
				System.out.println("Error in trade");
				GameState.declinedTrade();
				return;
			}
		}
		
		String message = gson.toJson(trade);
		NetworkClient.sendMessage("Trade " + message);
	}

	/**
	 * Called when player wants to accept a trade offer. Checks if possible and
	 * sends message to server
	 */
	static void acceptTrade() {
		// Method called when user accept trade
		// Check if player have funds
		
		for (int i = 0; i < GameData.players.get(0).resources.length; i++) {
			if (GameData.players.get(GameData.ownIndex).resources[i] < GameData.tObject.wants[i]) {
				System.out.println("You do not have enough resources!");
				return;
			}
		}
		
		NetworkClient.sendMessage("TradeAccept");
		/*
		int type = GameData.tObject.wants[0];
		if (GameData.players.get(GameData.ownIndex).resources[type] >= GameData.tObject.wants.length) {
			GameData.tObject.acceptPlayer = GameData.ownIndex;
			String message = gson.toJson(GameData.tObject);
			// Send message to server
			NetworkClient.sendMessage("TradeAccept");
		}*/
	}
	
	static void declineTrade() {
		NetworkClient.sendMessage("TradeDecline");
	}

	/**
	 * Called when player wants to buy a road. Checks if possible and calls
	 * placeRoad-method
	 * 
	 * @param start
	 *            Start position of the road
	 * @param end
	 *            End position of the road
	 * @param player
	 *            Index of the player
	 */
	static void buyRoad(Position start, Position end, int player) {
		// Check if player have funds
		if (GameData.players.get(player).resources[ResourceType.BRICK.toInt()] >= 1
			&& GameData.players.get(player).resources[ResourceType.TREE.toInt()] >= 1) {
			// Check if request is possible and send message
			placeRoad(start, end, player);
		}
	}

	/**
	 * Called when player wants to buy a building Checks if possible and calls
	 * placeBuilding
	 * 
	 * @param pos
	 *            Position of the building
	 * @param player
	 *            Index of the player
	 */
	static void buyBuilding(Position pos, int player) {
		// Check if player have funds
		if (GameData.players.get(player).resources[ResourceType.BRICK.toInt()] >= 1
			&& GameData.players.get(player).resources[ResourceType.CORN.toInt()] >= 1
			&& GameData.players.get(player).resources[ResourceType.SHEEP.toInt()] >= 1
			&& GameData.players.get(player).resources[ResourceType.TREE.toInt()] >= 1) {
			// Check if request is possible and send message
			placeBuilding(pos, player);
		}
	}

	/**
	 * Called when player wants to upgrade a building Checks if possible and
	 * sends message to server
	 * 
	 * @param pos
	 *            Position of the building
	 * @param player
	 *            Index of the player
	 */
	static void upgradeCity(Position pos, int player) {
		// Check if player have funds
		if (GameData.players.get(player).resources[ResourceType.CORN.toInt()] >= 2
				&& GameData.players.get(player).resources[ResourceType.ROCK.toInt()] >= 3) {
			// Check if request is possible
			for (int i = 0; i < Building.getBuildings().size(); i++) {
				if (Position.comparePosition(pos, Building.getBuildings().get(i).POSITION)
						&& player == Building.getBuildings().get(i).PLAYER
						&& Building.getBuildings().get(i).isUpgraded() == false) {
					Building.getBuildings().get(i).upgrade();
					gson = new Gson();
					String message = gson.toJson(pos);
					NetworkClient.sendMessage("Upgrade " + message);
				}
			}
		}
	}

	/**
	 * Used for sending chat messages to the server
	 * 
	 * @param message
	 */
	static void chat(String message) {
		NetworkClient.sendMessage("Chat " + message);
	}

	static void rollDice() {
		// Method used to notify server that user wants to roll the dice
		// Send message to server. Something like:
		// networkHelper.sendMessage("Roll dice", player);
		System.out.println("Roll");
		NetworkClient.sendMessage("Roll");
	}

	/**
	 * Method called when client receives a message from the server.
	 * 
	 * @param message
	 *            Messaged received by the server
	 */
	static void received(String message) {
		System.out.println(message);
		
		if (message.equals("Undo Building")) {
			// do something
		}
		
		else if (message.equals("SendName")) {
			NetworkClient.sendMessage("Name " + IntroState.playerName);
		}

		else if (expR != -1) {
			System.out.println("Expecting road!");
			endR = gson.fromJson(message, Position.class);

			Road newRoad = Road.buildRoad(Position.assignPosition(startR.DIVISION, startR.INDEX), Position.assignPosition(endR.DIVISION, endR.INDEX), expR);
			System.out.println("Road built!");
			
			expR = -1;
		} else if (message.equals("InitDone")) {
			System.out.println("First phase done");
			GameState.isInit = false;
		} else if (message.equals("TradeAccept")) {
			System.out.println("Trade accepted");
			GameState.acceptedTrade();
		} else if (message.equals("TradeDecline")) {
			System.out.println("Trade declined");
			GameState.declinedTrade();
		}

		else {
			String objectType = "";
			int playerID = 0;
			int jsonIndex = 0;
			for (int i = 0; i < message.length() && !Character.isSpaceChar(message.charAt(i)); i++) {
				objectType += message.charAt(i);
				jsonIndex = i + 2;
			}

			if (objectType.equals("Hexagon")) {
				message = message.substring(jsonIndex);
				Hexagon hex = gson.fromJson(message, Hexagon.class);
				Hexagon.addHex(hex);
				System.out.println("Map loaded!");
			} else if (objectType.equals("Roll1")) {
				message = message.substring(jsonIndex);
				Dice.dice1 = Integer.parseInt(message);
			} else if (objectType.equals("Roll2")) {
				message = message.substring(jsonIndex);
				Dice.dice2 = Integer.parseInt(message);
				GameState.processDie();
			} else if (objectType.equals("ID")) {
				message = message.substring(jsonIndex);
				int ownIndex = Integer.parseInt(message);
				GameData.ownIndex = ownIndex;
				System.out.println("I am index " + ownIndex + "!");
			} else if (objectType.equals("PlayerNums")) {
				message = message.substring(jsonIndex);
				System.out.println("There are " + Integer.parseInt(message) + " players!");
				GameData.setPlayerNum(Integer.parseInt(message));
				PreGameState.canStart();
			} else if (objectType.equals("Players")) {
				message = message.substring(jsonIndex);
				
				// create arraylist of players
				GameData.players = gson.fromJson(message, new TypeToken<ArrayList<Player>>(){}.getType());
				
				// set own index
				for (Player player : GameData.players) {
					if (player.getName().equals(IntroState.playerName)) {
						GameData.ownIndex = player.NUMBER;
						break;
					}
				}
				
				System.out.println("Own index: " + GameData.ownIndex + "!");
				System.out.println("Loaded players: " + GameData.players + "!");
			} else if (objectType.equals("Turn")) {
				message = message.substring(jsonIndex);
				GameData.turn = Integer.parseInt(message);
			} else if (objectType.equals("Robber")) {
				message = message.substring(jsonIndex);
				Hexagon.getHexagons()[Integer.parseInt(message)].rob();
			} else if (objectType.equals("Trade")) {
				// Method called when other users wants to trade resources
				message = message.substring(jsonIndex);
				TradeObject trade = gson.fromJson(message, TradeObject.class);
				GameData.tObject = trade;
				GameState.chooseTrade();
			} else if (objectType.equals("TradeAccept")) {
				message = message.substring(jsonIndex);
				GameData.tObject = gson.fromJson(message, TradeObject.class);
				GameData.players.get(
						GameData.tObject.initPlayer).resources[GameData.tObject.hasType] -= GameData.tObject.has.length;
				GameData.players.get(
						GameData.tObject.initPlayer).resources[GameData.tObject.wantsType] += GameData.tObject.wants.length;
				GameData.players.get(
						GameData.tObject.acceptPlayer).resources[GameData.tObject.hasType] += GameData.tObject.has.length;
				GameData.players.get(
						GameData.tObject.acceptPlayer).resources[GameData.tObject.wantsType] -= GameData.tObject.wants.length;
			} 
			
			else {
				for (int i = jsonIndex; !Character.isSpaceChar(message.charAt(i)); i++) {
					playerID = Integer.parseInt(String.valueOf(message.charAt(i)));
					jsonIndex = i + 2;
				}

				if (objectType.equals("Building")) {
					System.out.println("Received a building from " + playerID + "!");
					System.out.println(message);
					
					// convert string to position
					message = message.substring(jsonIndex);
					Position tempPos = gson.fromJson(message, Position.class);
					Position position = Position.assignPosition(tempPos.DIVISION, tempPos.INDEX);
					
					// error checking
					if (Building.getByPosition(position) != null) {
						// The building already exists
					} 
					
					else {
						Building.build(position, playerID);
						System.out.println("Building here");
					}
					
				} else if (objectType.equals("Resource")) {
					System.out.println("Received updated resource for player " + playerID);
					message = message.substring(jsonIndex);
					ArrayList<Integer> tempResources = gson.fromJson(message, new TypeToken<ArrayList<Integer>>(){}.getType());
					int[] res = new int[tempResources.size()];
					for (int i = 0; i < res.length; i++) {
						res[i] = tempResources.get(i);
					}
					GameData.players.get(playerID).resources = res;
					GameData.players.get(playerID).updateResAmount();
				}
				
				else if (objectType.equals("Road")) {
					System.out.println("Received a road from: " + playerID);
					message = message.substring(jsonIndex);
					startR = gson.fromJson(message, Position.class);
					expR = playerID;
					System.out.println("Road");
				} else if (objectType.equals("Upgrade")) {
					message = message.substring(jsonIndex);
					Position position = gson.fromJson(message, Position.class);
					Building.getByPosition(position).upgrade();
					System.out.println("Upgrade this nigger");
				} else if (objectType.equals("Chat")) {
					System.out.println("Chat");
					message = message.substring(jsonIndex);
					if (playerID != GameData.ownIndex)
						ListBox.addString(message, playerID);
				} else if (objectType.equals("Players")){
					GameData.players = gson.fromJson(message, ArrayList.class);
				}
			}
		}
	}
}