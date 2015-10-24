package example;


public class Main {

	GameController gameController = new GameController(); 
	
	public void initGame() {

		gameController.createStack();
		gameController.addPlayer();
		gameController.setPair(0, 2);
		System.out.println("Decline:");
		gameController.decline(0);
		System.out.println("Set pair nr 2:");
		gameController.setPair(0, 2);
		gameController.stack.debugStack();
		System.out.println("Decline:");
		gameController.decline(0);
		gameController.stack.debugStack();

		
	
	}

	public static void main(String[] args) {

		Main m = new Main();
		m.initGame();
		
	}
}
