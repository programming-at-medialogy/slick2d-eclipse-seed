public class Actions {

	//Methods for initial phase
	
		static void placeBuilding(Position pos, int player){
			//Check if request is possible
			boolean canBuild = false;
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
				
			}
		}
		
		static void placeRoad(Position start, Position end, int player){
			//Check if request is possible
			boolean canBuild = false;
			for(int i = 0; i < Road.roads.size(); i++){
				if((start==Road.roads.get(i).start && end==Road.roads.get(i).end)||
				   (end==Road.roads.get(i).start && start==Road.roads.get(i).end)){
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
		
		static void initiateTrade(){
			//Method to use when the player wants to trade with other players
		}
		
		static void getTradeResponse(TradeObject tradeObject){
			//Method called when other users wants to trade resources
		}

		
		// Methods for building-phase
		
		static void BuyCard(){
			//Check if player have funds
			//Check if request is possible
			//Send message to server
		}
		
		static void buyRoad(Position start, Position end, int player){
			//Check if player have funds
			if(Player.resources[ResourceType.MARSSAND.toInt()]>=1 && 
			   Player.resources[ResourceType.SPACEDEBRIS.toInt()]>=1){
				//Check if request is possible and send message
				placeRoad(start, end, player);
			}
		}
		
		static void buyCity(Position pos, int player){
			//Check if player have funds
			if(Player.resources[ResourceType.MARSSAND.toInt()]>=1 && 
			   Player.resources[ResourceType.SPACEDEBRIS.toInt()]>=1 && 
			   Player.resources[ResourceType.ALIENDNA.toInt()]>=1 && 
			   Player.resources[ResourceType.MOONWATER.toInt()]>=1){
				//Check if request is possible and send message
				placeBuilding(pos, player);
			}
		}
		
		static void upgradeCity(Position pos, int player){
			//Check if player have funds
			if(Player.resources[ResourceType.MARSSAND.toInt()]>=2 && 
			   Player.resources[ResourceType.SPACEDEBRIS.toInt()]>=3){
				//Check if request is possible
				for(int i = 0; i < Building.buildings.size(); i++){
					if(pos==Building.buildings.get(i).position && 
					   player == Building.buildings.get(i).player &&
					   Building.buildings.get(i).upgraded == false){
						//Send message to server. Something like:
						//networkHelper.sendMessage("Roll dice", player);
					}
				}
			}
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
