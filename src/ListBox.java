import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

public class ListBox {

	private final int MAX_CHARS;
	
	private static final int PADDING = 3;
	
	private int x, y;
	private int lastMY;
	private int width, height;
	private int offsetY;
	private BasicGameState state;
	private boolean isClicked;
	private boolean isActive;
	private ArrayList<String> content;
	private static boolean isMouseDown;
	private static ArrayList<ListBox> listBoxes = new ArrayList<ListBox>();
	
	public ListBox(int x, int y, int width, int height, BasicGameState state) {
		this.x = x;
		this.y = y;
		this.lastMY = 0;
		this.width = width;
		this.height = height;
		this.state = state;
		this.offsetY = 0;
		this.isClicked = false;
		this.isActive = false;
		
		// sets max chars based on width
		this.MAX_CHARS = (int)(width / 9.5);
		
		isMouseDown = false;
		content = new ArrayList<String>();
		
		listBoxes.add(this);
	}
	
	/**
	 * Adds a string to the list box.
	 * Splits the string into multiple strings if it is too long.
	 * @param message The string to be added
	 */
	public void addString(String message) {
		// if the length is longer than the max chars per line it should be split into substrings
		if (MAX_CHARS < message.length()) {
			for (int i = 0; i < message.length() / MAX_CHARS; i++) {
				// ignore spaces after newline
				int offset = 0;
				for (; Character.isSpaceChar(message.charAt(i * MAX_CHARS + offset)) && offset < MAX_CHARS - 1; offset++);
				
				// add the substrings
				String newMessage = message.substring(offset + i * MAX_CHARS, (i + 1) * MAX_CHARS);
				content.add(0, newMessage);
			}
			
			// add the last substring
			if (message.length() % (int)(width / 9.5) != 0) {
				String newMessage = message.substring((message.length() / MAX_CHARS) * MAX_CHARS);
				content.add(0, newMessage);
			}
		} else {
			content.add(0, message);
		}
	}
	
	/**
	 * Updates all ListBoxes.
	 * @param state The state that it has to update in
	 */
	public static void update(BasicGameState state) {
		for (ListBox listBox : listBoxes) {
			// check if listBox was clicked
			if (!isMouseDown && Mouse.isButtonDown(0)) { 			// click
				if (Mouse.getX() > listBox.x && 
					Mouse.getX() < listBox.x + listBox.width &&
					Windows.scHeight - Mouse.getY() > listBox.y && 
					Windows.scHeight - Mouse.getY() < listBox.y + listBox.height) {
					listBox.isClicked = true;
					listBox.lastMY = Windows.scHeight - Mouse.getY();
				}
			} else if (isMouseDown && !Mouse.isButtonDown(0)) { 	// release
				listBox.isClicked = false;
			}
			
			// only do this if the listBox was clicked
			if (listBox.isClicked) {
				// calculates the difference in mouseY since last frame
				int diffY = Windows.scHeight - Mouse.getY() - listBox.lastMY;
				listBox.lastMY = Windows.scHeight - Mouse.getY();
	
				// adjusts the offset
				if (!(listBox.offsetY + diffY < 0 || listBox.offsetY + diffY > listBox.content.size() * 20 - listBox.height + 40)) {
					listBox.offsetY += diffY;
				}
			}
		}
		
		isMouseDown = Mouse.isButtonDown(0);
	}
	
	/**
	 * Draws all ListBoxes
	 * @param g A slick2d graphics object
	 * @param state The state that it has to draw in
	 */
	public static void draw(Graphics g, BasicGameState state) {
		for (ListBox listBox : listBoxes) {
			if (listBox.state == state) {
				// draw box
				g.setColor(new Color(0, 0, 0));
				g.fillRect(listBox.x, listBox.y, listBox.width, listBox.height);
				if (listBox.isActive)
					g.setColor(new Color(255, 255, 255));
				else 
					g.setColor(new Color(200, 200, 200));
				g.fillRect(listBox.x + PADDING, listBox.y + PADDING, listBox.width - PADDING * 2, listBox.height - PADDING * 2);
				
				// draw strings
				g.setColor(new Color(0, 0, 0));
				for (int i = 0; i < listBox.content.size(); i++) {
					int textY = listBox.y + listBox.height - (i + 2) * 20 + listBox.offsetY;
					if (textY > listBox.y && textY < (listBox.y + listBox.height - 20))
						g.drawString(listBox.content.get(i), listBox.x + PADDING, textY);
				}
			}
		}
	}
	
	/**
	 * Sets the state of the ListBox to active
	 */
	public void activate() {
		isActive = true;
	}
	
	/**
	 * Sets the state of the ListBox to not active
	 */
	public void deactivate() {
		isActive = false;
	}
}
