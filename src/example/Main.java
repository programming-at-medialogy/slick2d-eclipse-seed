package example;


public class Main {

	GameController gameController = new GameController(); 
 void initGame() {

		gameController.createStack();
		gameController.addPlayer();
		gameController.setPair(0, 2);
		System.out.println("Decline:");
		gameController.decline(0);
		System.out.println("Set pair nr 2:");
		gameController.setPair(0, 2);
		gameController.stack.debugStack();
		System.out.println("Decline:");
		System.out.println(gameController.race.type.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());


		System.out.println(gameController.player.get(0).pair[1].units);
		gameController.player.get(0).pair[1].effect();
		System.out.println(gameController.player.get(0).pair[1].units);
		

	
	}

	public static void main(String[] args) {

		MapTwoPlayer mapTwoPlayer = new MapTwoPlayer();
		System.out.println("defence value = " + mapTwoPlayer.field1pt4.getDefenceValue());
		
		
		
		Main m = new Main();
		m.initGame();
		
	}
}
