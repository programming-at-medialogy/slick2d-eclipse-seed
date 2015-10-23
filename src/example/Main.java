package example;


public class Main {

	GameController gameController = new GameController();

	public void initGame() {

		gameController.initDb();
		gameController.initializeStack();
		gameController.shuffleStack();
		gameController.debugStack();

	}

	public static void main(String[] args) {

		Main m = new Main();
		m.initGame();

	}
}
