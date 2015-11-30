import com.google.gson.Gson;

public class ServerActions {
	static Gson gson;
	static Position rStartPos;
	static int expectingRoad;
	
	static void initActions() {
		gson = new Gson();
		expectingRoad = -1;
	}
	
	static void generateMap() {
		Hexagon[] hexagons = Hexagon.generateMap();
		
		String message;
		for (Hexagon hexagon : hexagons) {
			message = "Hexagon " + gson.toJson(hexagon);
			NetworkServer.sendToAll(message);
		}
	}
	
	static void placeBuilding(Position pos, int player){
		Building.build(pos, player);
		//update all clients!
	}
	
	static void placeRoad(Position start, Position end, int player){
		Road.buildRoad(start, end, player);
		//Update all clients!
	}
	
	static void initialTrade(TradeObject player){
		//notify all clients except for the one that wants to trade!
	}
	
	static void acceptTrade(TradeObject trade, int initPlayer, int acceptPlayer){
		//Update ressources
		int type1  = trade.wants[0];
		int amount1 = trade.wants.length;
		int type2  = trade.has[0];
		int amount2 = trade.has.length;
		GameData.players.get(initPlayer).resources[type1]=GameData.players.get(initPlayer).resources[type1]+amount1; 
		GameData.players.get(initPlayer).resources[type1]=GameData.players.get(initPlayer).resources[type1]-amount2; 
		GameData.players.get(acceptPlayer).resources[type1]=GameData.players.get(acceptPlayer).resources[type1]-amount1; 
		GameData.players.get(acceptPlayer).resources[type2]=GameData.players.get(acceptPlayer).resources[type2]+amount2; 
		
		//Update all clients !
	}
	
	
	static void buyRoad(Position start, Position end, int player){
		//Remove ressources from player
		GameData.players.get(player).resources[ResourceType.BRICK.toInt()]--;
		GameData.players.get(player).resources[ResourceType.TREE.toInt()]--;
		//Place road
		placeRoad(start,end,player);
		//update all clients!
	}
	
	static void buyCity(Position pos, int player){
		//Remove ressources from player
		GameData.players.get(player).resources[ResourceType.CORN.toInt()]-=2; 
		GameData.players.get(player).resources[ResourceType.ROCK.toInt()]--; 
		GameData.players.get(player).resources[ResourceType.TREE.toInt()]--; 
		GameData.players.get(player).resources[ResourceType.BRICK.toInt()]--;
		//Place building
		placeBuilding(pos,player);
		//update all clients!
	}
	
	static void upgradeCity(Position pos, int player){
		//Remove ressources from player
		GameData.players.get(player).resources[ResourceType.CORN.toInt()]-=2; 
		GameData.players.get(player).resources[ResourceType.ROCK.toInt()]-=3;
		for(int i = 0; i < GameData.buildings.size(); i++){
			if (Position.comparePosition(GameData.buildings.get(i).POSITION, pos)){
				 GameData.buildings.get(i).upgrade();
			}
		}
		//update all clients!
	}
	
	static void BuyCard(){
	}
	
	static void useDevelopment(){
	}
	
	public synchronized static void received(int clientId, String message) {
		
		if (expectingRoad == clientId) {
			Position rEndPos = gson.fromJson(message, Position.class);
			if (Road.buildRoad(rStartPos, rEndPos, clientId) != null) {
				NetworkServer.sendToAll("Road " + clientId + gson.toJson(rStartPos));
				NetworkServer.sendToAll(message);
			}
			expectingRoad = -1;
		} else if (clientId == GameData.turn && message.equals("rollDice")) {
			
		}
		
		String objectType = "";
		int jsonIndex = 0;
		for (int i = 0; !Character.isSpaceChar(message.charAt(i)); i++) {
			objectType += message.charAt(i);
			jsonIndex = i + 2;
		}
		
		message = message.substring(jsonIndex);
		
		if(objectType.equals("Building")){
			Position inPos = gson.fromJson(message, Position.class);
			if (Building.build(inPos, clientId) != null) 
				NetworkServer.sendToAll("Building " + clientId + " " + message);
		} else if (objectType.equals("Upgrade")){
			Position inPos = gson.fromJson(message, Position.class);
			if (Building.getByPosition(inPos).upgrade()) 
				NetworkServer.sendToAll("Upgrade " + clientId + inPos);
		} else if (objectType.equals("Road")) {
			rStartPos = gson.fromJson(message, Position.class);
			expectingRoad = clientId;
		} else if (objectType.equals("Chat")) {
			NetworkServer.sendToAll("Chat " + clientId + " " + message);
		} else if(objectType.equals("Trade")){
			GameData.tObject = gson.fromJson(message, TradeObject.class);
			NetworkServer.sendToAll("Trade " + message);
		}else if(objectType.equals("TradeAccept")){
			GameData.tObject = gson.fromJson(message, TradeObject.class);
			GameData.players.get(GameData.tObject.initPlayer).resources[GameData.tObject.hasType] -= GameData.tObject.has.length;
			GameData.players.get(GameData.tObject.initPlayer).resources[GameData.tObject.wantsType] += GameData.tObject.wants.length;
			GameData.players.get(GameData.tObject.acceptPlayer).resources[GameData.tObject.hasType] += GameData.tObject.has.length;
			GameData.players.get(GameData.tObject.acceptPlayer).resources[GameData.tObject.wantsType] -= GameData.tObject.wants.length;
			NetworkServer.sendToAll("TradeAccept " + message);
		}
		
	}
	
	public static void updateGameData() {
		for (int i = 0; i < 4; i++) {
			NetworkServer.send(i, "ID " + i);
		}
	}
}
