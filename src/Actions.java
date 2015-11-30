import com.google.gson.Gson;

public class Actions {
	static Gson gson;
	static Position startR;
	static Position endR;
	static int expR;
	
	static void initActions(){
		gson = new Gson();
		expR = -1;
	}
	//Methods for initial phase
	
		static void placeBuilding(Position pos, int player){

			Building newBuilding = Building.build(pos, player);
			if (newBuilding != null) {
				String message = gson.toJson(newBuilding);
				NetworkClient.sendMessage("Building " + message);
			}

		}
		
		static void placeRoad(Position start, Position end, int player){
			//Check if request is possible
			boolean canBuild = false;
			for(int i = 0; i < GameData.roads.size(); i++){
				if((Position.comparePosition(start, GameData.roads.get(i).start) && Position.comparePosition(end, GameData.roads.get(i).end))||
				   (Position.comparePosition(end, GameData.roads.get(i).start) && Position.comparePosition(start, GameData.roads.get(i).end))){
					System.out.println("There is already a road here!");
					canBuild =false;
					break;
				}else{
					canBuild =true;
				}
			}
			if (canBuild){
				Road newRoad = Road.buildRoad(start, end, player);
				if (newRoad != null) {
					String message = gson.toJson(start);
					NetworkClient.sendMessage("Road " + message);
					String message2 = gson.toJson(end);
					NetworkClient.sendMessage(message2);
				}	
			}
		}
		
		
		//Methods for trade-phase
		
		static void initiateTrade(TradeObject trade, int player){
			//Method to use when the player wants to trade with other players
			//Check if player have funds
			int type  = trade.has[0];
			if(GameData.players.get(player).resources[type]>=trade.has.length){
				//Send message to server
				String message = gson.toJson(trade);
				NetworkClient.sendMessage("Trade " + message);
			}
		}
		
		static void acceptTrade(){
			//Method called when user accept trade
			//Check if player have funds
			int type  = GameData.tObject.wants[0];
			if(GameData.players.get(GameData.ownIndex).resources[type]>=GameData.tObject.wants.length){
				GameData.tObject.acceptPlayer = GameData.ownIndex;
				String message = gson.toJson(GameData.tObject);
				//Send message to server
				NetworkClient.sendMessage("TradeAccept " + message);
			}
		}

		
		// Methods for building-phase
		
		static void buyRoad(Position start, Position end, int player){
			//Check if player have funds
			if(GameData.players.get(player).resources[ResourceType.BRICK.toInt()]>=1 && 
				GameData.players.get(player).resources[ResourceType.TREE.toInt()]>=1){
				//Check if request is possible and send message
				placeRoad(start, end, player);
			}
		}
		
		static void buyCity(Position pos, int player){
			//Check if player have funds
			if(GameData.players.get(player).resources[ResourceType.BRICK.toInt()]>=1 && 
			   GameData.players.get(player).resources[ResourceType.CORN.toInt()]>=2 && 
			   GameData.players.get(player).resources[ResourceType.ROCK.toInt()]>=1 && 
			   GameData.players.get(player).resources[ResourceType.TREE.toInt()]>=1){
				//Check if request is possible and send message
				placeBuilding(pos, player);
			}
		}
		
		static void upgradeCity(Position pos, int player){
			//Check if player have funds
			if(GameData.players.get(player).resources[ResourceType.CORN.toInt()]>=2 && 
				GameData.players.get(player).resources[ResourceType.ROCK.toInt()]>=3){
				//Check if request is possible
				for(int i = 0; i < Building.getBuildings().size(); i++){
					if(Position.comparePosition(pos, Building.getBuildings().get(i).POSITION) && 
					    player == Building.getBuildings().get(i).PLAYER &&
					    Building.getBuildings().get(i).isUpgraded() == false){
						Building.getBuildings().get(i).upgrade();
						gson = new Gson();
						String message = gson.toJson(pos);
						NetworkClient.sendMessage("Upgrade " + message);
					}
				}
			}
		}
		
		static void chat(String message) {
			NetworkClient.sendMessage("Chat " + message);
		}
		
		static void rollDice(){
			//Method used to notify server that user wants to roll the dice
			//Send message to server. Something like:
			//networkHelper.sendMessage("Roll dice", player);
		}
		static void received(String message){
			if(message == "Undo Building"){
				// do something
			}
			
			if (expR != -1) {
				endR = gson.fromJson(message, Position.class);
				Boolean canBuild = null;
				for(int i = 0; i < GameData.roads.size(); i++){
					if((Position.comparePosition(startR, GameData.roads.get(i).start) && Position.comparePosition(endR, GameData.roads.get(i).end))||
					   (Position.comparePosition(endR, GameData.roads.get(i).start) && Position.comparePosition(startR, GameData.roads.get(i).end))){
						System.out.println("There is already a road here!");
						canBuild =false;
						break;
					}else{
						canBuild =true;
					}
				}
				if (canBuild){
					Road newRoad = Road.buildRoad(startR, endR, expR);
					}
				expR = -1;		
			}
	
			else{
				String objectType = "";
				int playerID = 0;
				int jsonIndex = 0;
				for (int i = 0; !Character.isSpaceChar(message.charAt(i)); i++) {
					objectType += message.charAt(i);
					jsonIndex = i + 2;
				}
				
				if (objectType.equals("Hexagon")){
					message = message.substring(jsonIndex);
					Hexagon hex = gson.fromJson(message, Hexagon.class);
					Hexagon.addHex(hex);
					
					System.out.println("Hexgaons");
				} else if (objectType.equals("ID")) {
					message = message.substring(jsonIndex);
					int ownIndex = Integer.parseInt(message);
					GameData.ownIndex = ownIndex;
				}
				else{
					for (int i = jsonIndex; !Character.isSpaceChar(message.charAt(i)); i++) {
						playerID = Integer.parseInt(String.valueOf(message.charAt(i)));
						jsonIndex = i + 2;
					}
					
					if(objectType.equals("Building")){
						message = message.substring(jsonIndex);
						Position position = gson.fromJson(message, Position.class);
						if (Building.getByPosition(position) != null) {
							// The building already exists
						} else{
							Building.build(position, playerID);
							System.out.println("Upgrade this nigger");
						}
					}
					else if(objectType.equals("Road")){
						message = message.substring(jsonIndex);
						startR = gson.fromJson(message, Position.class);
						expR = playerID;
						System.out.println("Road");
					}
					else if (objectType.equals("Upgrade")){
						message = message.substring(jsonIndex);
						Position position = gson.fromJson(message, Position.class);
						Building.getByPosition(position).upgrade();
						System.out.println("Upgrade this nigger");
					}
					else if (objectType.equals("Chat")) {
						System.out.println("Chat");
						message = message.substring(jsonIndex);
						if (playerID != GameData.ownIndex)
							ListBox.addString(message, playerID);
					}
					else if(objectType.equals("Trade")){
						//Method called when other users wants to trade resources
						TradeObject trade = gson.fromJson(message, TradeObject.class);
						GameData.tObject = trade;
						if(GameData.tObject.initPlayer!=GameData.ownIndex){
							//Update graphics
						}
					}else if(objectType.equals("TradeAccept")){
						GameData.tObject = gson.fromJson(message, TradeObject.class);
						GameData.players.get(GameData.tObject.initPlayer).resources[GameData.tObject.hasType] -= GameData.tObject.has.length;
						GameData.players.get(GameData.tObject.initPlayer).resources[GameData.tObject.wantsType] += GameData.tObject.wants.length;
						GameData.players.get(GameData.tObject.acceptPlayer).resources[GameData.tObject.hasType] += GameData.tObject.has.length;
						GameData.players.get(GameData.tObject.acceptPlayer).resources[GameData.tObject.wantsType] -= GameData.tObject.wants.length;
					}
					}
				}
			}
		}