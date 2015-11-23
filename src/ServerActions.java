public class ServerActions {
	
	
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
		GameData.players.get(player).resources[ResourceType.MARSSAND.toInt()]--;
		GameData.players.get(player).resources[ResourceType.SPACEDEBRIS.toInt()]--;
		//Place road
		placeRoad(start,end,player);
		//update all clients!
	}
	
	static void buyCity(Position pos, int player){
		//Remove ressources from player
		GameData.players.get(player).resources[ResourceType.MARSSAND.toInt()]--; 
		GameData.players.get(player).resources[ResourceType.SPACEDEBRIS.toInt()]--; 
		GameData.players.get(player).resources[ResourceType.ALIENDNA.toInt()]--; 
		GameData.players.get(player).resources[ResourceType.MOONWATER.toInt()]--;
		//Place building
		placeBuilding(pos,player);
		//update all clients!
	}
	
	static void upgradeCity(Position pos, int player){
		//Remove ressources from player
		GameData.players.get(player).resources[ResourceType.MARSSAND.toInt()]=Player.resources[ResourceType.MARSSAND.toInt()]-2; 
		GameData.players.get(player).resources[ResourceType.SPACEDEBRIS.toInt()]=Player.resources[ResourceType.SPACEDEBRIS.toInt()]-3;
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
}
