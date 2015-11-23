public class Actions {

	//Methods for initial phase
	
		static void placeBuilding(Position pos, int player){
			//Check if request is possible
			boolean canBuild = false;
			for(int i = 0; i < GameData.buildings.size(); i++){
				if(pos==GameData.buildings.get(i).position){
					System.out.println("There is already a building here!");
					canBuild =false;
					break;
				}else if (Position.getLength(pos, GameData.buildings.get(i).position)<2){
					System.out.println("This is too close to another building");
					canBuild =false;
					break;
				}else{
					canBuild =true;
				}				
			}
			if (canBuild){
				//Send message to server. Something like:
				//networkHelper.sendMessage("Build building", pos, player);
				
			}
		}
		
		static void placeRoad(Position start, Position end, int player){
			//Check if request is possible
			boolean canBuild = false;
			for(int i = 0; i < Road.roads.size(); i++){
				if((start==GameData.roads.get(i).start && end==GameData.roads.get(i).end)||
				   (end==GameData.roads.get(i).start && start==GameData.roads.get(i).end)){
					System.out.println("There is already a road here!");
					canBuild =false;
					break;
				}else{
					canBuild =true;
				}
			}
			if (canBuild){
				//Send message to server. Something like:
				//networkHelper.sendMessage("Build Road", start, end, player);
				
			}
		}
		
		
		//Methods for trade-phase
		
		static void initiateTrade(TradeObject trade, int player){
			//Method to use when the player wants to trade with other players
			//Check if player have funds
			int type  = trade.has[0];
			if(GameData.players.get(player).resources[type]>=trade.has.length){
				//Send message to server. Something like:
				//networkHelper.sendMessage("Trade Offer",trade, player);
			}
		}
		
		static void receiveTrade(TradeObject trade, int player){
			//Method called when other users wants to trade resources
			//Update graphics
		}
		
		static void acceptTrade(TradeObject trade,int thisPlayer,int opponent){
			//Method called when user accept trade
			//Check if player have funds
			int type  = trade.wants[0];
			if(GameData.players.get(thisPlayer).resources[type]>=trade.wants.length){
				//Send message to server. Something like:
				//networkHelper.sendMessage("TradeAccept",trade, thisPlayer, opponent);
			}
		}

		
		// Methods for building-phase
		
		static void buyRoad(Position start, Position end, int player){
			//Check if player have funds
			if(GameData.players.get(player).resources[ResourceType.MARSSAND.toInt()]>=1 && 
			   GameData.players.get(player).resources[ResourceType.SPACEDEBRIS.toInt()]>=1){
				//Check if request is possible and send message
				placeRoad(start, end, player);
			}
		}
		
		static void buyCity(Position pos, int player){
			//Check if player have funds
			if(GameData.players.get(player).resources[ResourceType.MARSSAND.toInt()]>=1 && 
				GameData.players.get(player).resources[ResourceType.SPACEDEBRIS.toInt()]>=1 && 
				GameData.players.get(player).resources[ResourceType.ALIENDNA.toInt()]>=1 && 
				GameData.players.get(player).resources[ResourceType.MOONWATER.toInt()]>=1){
				//Check if request is possible and send message
				placeBuilding(pos, player);
			}
		}
		
		static void upgradeCity(Position pos, int player){
			//Check if player have funds
			if(GameData.players.get(player).resources[ResourceType.MARSSAND.toInt()]>=2 && 
				GameData.players.get(player).resources[ResourceType.SPACEDEBRIS.toInt()]>=3){
				//Check if request is possible
				for(int i = 0; i < GameData.buildings.size(); i++){
					if(pos==GameData.buildings.get(i).position && 
					   player == GameData.buildings.get(i).player &&
					   GameData.buildings.get(i).upgraded == false){
						//Send message to server. Something like:
						//networkHelper.sendMessage("upgrade", player, pos);
					}
				}
			}
		}
		
		static void BuyCard(){
			//Check if player have funds
			//Check if request is possible
			//Send message to server
		}
		
		static void useDevelopment(){
			//Method used to notify server that user wants to use a developement card
		}

		static void rollDice(){
			//Method used to notify server that user wants to roll the dice
			//Send message to server. Something like:
			//networkHelper.sendMessage("Roll dice", player);
		}
}
