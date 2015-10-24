package example;


public class Main {

	Stack stack = new Stack();
	Player player = new Player();

	public void initGame() {

		stack.initDb();
		stack.initialize();
		stack.shuffle();
		stack.debugStack();

	}

	public static void main(String[] args) {

		Main m = new Main();
		m.initGame();

	}
}
