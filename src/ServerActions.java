import com.google.gson.Gson;

public class ServerActions {
	static Gson gson;
	
	
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
	
	public static void received(int clientId, String message) {
		System.out.println("Test");
		gson = new Gson();
		String objectType = "";
		int jsonIndex = 0;
		for (int i = 0; !Character.isSpaceChar(message.charAt(i)); i++) {
			objectType += message.charAt(i);
			jsonIndex = i + 2;
		}
		if(objectType.equals("Building")){
			message = message.substring(jsonIndex);
			Building building = gson.fromJson(message, Building.class);
			System.out.println(building.getDivision());
			System.out.println("Det virker");
		} else {
			System.out.println("Lort");
		}
		//NetworkServer.sendToAll("Message received from " + clientId + ":\n" + message);
		/*if (GameData.players.get(clientId).getPlayerName() == null) {
			GameData.players.get(clientId).setPlayerName(message);
		}*/
	}
}
