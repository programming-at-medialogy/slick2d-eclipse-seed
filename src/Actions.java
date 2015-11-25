public class Actions {

	//Methods for initial phase
	
		static void placeBuilding(Position pos, int player){
			//Check if request is possible
			
			// no need to do this, just use the Building.canBuild() method
			
			/*boolean canBuild = false;
			for(int i = 0; i < Building.buildings.size(); i++){
				if(pos==Building.buildings.get(i).position){
					System.out.println("There is already a building here!");
					canBuild =false;
					break;
				}else if (Position.getLength(pos, Building.buildings.get(i).position)<2){
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
				
			}*/
			
			if (Building.canBuild(pos)) {
				//Send message to server. Something like:
				//networkHelper.sendMessage("Build building", pos, player);
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
