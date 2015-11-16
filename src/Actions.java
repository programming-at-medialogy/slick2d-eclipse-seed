public class Actions {

	//Methods for initial phase
	
		static void placeBuilding(){
			//Method to place buildings at game start
		}
		
		static void placeRoad(){
			//Method to place roads at game start
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
			//Method used to notify server that user wants to buy a development card
		}
		
		static void buyRoad(){
			//Method used to notify server that user wants to buy a road
		}
		
		static void buyCity(){
			//Method used to notify server that user wants to buy a city
		}
		
		static void upgradeCity(){
			//Method used to notify server that user wants to upgrade a city
		}
		
		static void useDevelopment(){
			//Method used to notify server that user wants to use a developement card
		}

		static void rollDice(){
			//Method used to notify server that user wants to roll the dice
		}
}
