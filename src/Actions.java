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
		
		static void buyRoad(){
			//Check if player have funds
			//Check if request is possible
			//Send message to server
		}
		
		static void buyCity(){
			//Check if player have funds
			//Check if request is possible
			//Send message to server
		}
		
		static void upgradeCity(){
			//Check if player have funds
			//Check if request is possible
			//Send message to server
		}
		
		static void useDevelopment(){
			//Method used to notify server that user wants to use a developement card
		}

		static void rollDice(){
			//Method used to notify server that user wants to roll the dice
		}
}
