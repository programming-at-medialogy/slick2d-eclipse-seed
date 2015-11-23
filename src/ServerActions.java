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
		Player.resources[type1]=Player.resources[type1]+amount1; //change to initPlayer
		Player.resources[type2]=Player.resources[type2]-amount2; //change to initPlayer
		Player.resources[type1]=Player.resources[type1]-amount1; //change to acceptPlayer
		Player.resources[type2]=Player.resources[type2]+amount2; //change to acceptPlayer
		
		//Update all clients !
	}
	
	
	static void buyRoad(Position start, Position end, int player){
		//Remove ressources from player
		Player.resources[ResourceType.MARSSAND.toInt()]--;
		Player.resources[ResourceType.SPACEDEBRIS.toInt()]--;
		//Place road
		placeRoad(start,end,player);
		//update all clients!
	}
	
	static void buyCity(Position pos, int player){
		//Remove ressources from player
		Player.resources[ResourceType.MARSSAND.toInt()]--; 
		Player.resources[ResourceType.SPACEDEBRIS.toInt()]--; 
		Player.resources[ResourceType.ALIENDNA.toInt()]--; 
		Player.resources[ResourceType.MOONWATER.toInt()]--;
		//Place building
		placeBuilding(pos,player);
		//update all clients!
	}
	
	static void upgradeCity(Position pos, int player){
		//Remove ressources from player
		Player.resources[ResourceType.MARSSAND.toInt()]=Player.resources[ResourceType.MARSSAND.toInt()]-2; 
		Player.resources[ResourceType.SPACEDEBRIS.toInt()]=Player.resources[ResourceType.SPACEDEBRIS.toInt()]-3;
		for(int i = 0; i < Building.buildings.size(); i++){
			if (Building.buildings.get(i).position == pos){
				Building.buildings.get(i).upgrade();
			}
		}
		//update all clients!
	}
	
	static void BuyCard(){
	}
	
	static void useDevelopment(){
	}
}
