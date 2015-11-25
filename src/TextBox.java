import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

public abstract class TextBox {
	
	private static final int PADDING = 3;
	
	private int x, y;
	private int width, height;
	
	private int fontSize;
	private int maxChars;
	
	private String content;
	
	private boolean isAlpha;
	private boolean isNumeric;
	private boolean isWhiteSpace;
	
	private boolean isActive;
	
	private BasicGameState state;
	
	private static ArrayList<TextBox> textBoxs = new ArrayList<TextBox>();
	
	TextBox(int x, int y, int width, int height, int fontSize, BasicGameState state) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.state = state;
		this.fontSize = fontSize;
		
		this.isActive = false;
		this.isAlpha = true;
		this.isNumeric = true;
		this.isWhiteSpace = true;
		this.content = "";
		
		// sets a max chars based on the 
		this.maxChars = (int)(width / fontSize * 1.2);
		
		textBoxs.add(this);
	}
	
	/**
	 * Draws all textboxs.
	 * @param g A slick2d graphics object
	 * @param state The state that it has to draw in
	 */
	public static void draw(Graphics g, BasicGameState state) {
		for (TextBox textBox : textBoxs) {
			if (state == textBox.state) {
				g.setColor(new Color(0, 0, 0));
				g.fillRect(textBox.x, textBox.y, textBox.width, textBox.height);
				if (textBox.isActive)
					g.setColor(new Color(255, 255, 255));
				else
					g.setColor(new Color(200, 200, 200));
				g.fillRect(textBox.x + PADDING, textBox.y + PADDING, textBox.width - PADDING * 2, textBox.height - PADDING * 2);
				g.setColor(new Color(0, 0, 0));
				g.drawString(textBox.content, textBox.x + PADDING * 2, textBox.y + textBox.height / 2 - textBox.fontSize / 2);
			}
		}
	}
	
	/**
	 * Updates all textboxs.
	 * @param state The state that it has to update in
	 */
	public static void update(BasicGameState state) {
		int x = Mouse.getX();
		int y = Windows.scHeight - Mouse.getY();
		
		if (Mouse.isButtonDown(0)) {
			for (TextBox textBox : textBoxs) {
				if (state == textBox.state) {
					if (x > textBox.x && x < textBox.x + textBox.width && y > textBox.y && y < textBox.y + textBox.height) {
						textBox.isActive = true;
					} else {
						textBox.isActive = false;
					}
				}
			}
		}
	}
	
	/**
	 * This needs to be called in the keyPressed state for all Slick states.
	 * @param key The keycode
	 * @param c The character
	 * @param state The state to register keypresses in
	 */
	public static void keyPress(int key, char c, BasicGameState state) {
		for (TextBox textBox : textBoxs) { 
			if (state == textBox.state) {
				if (textBox.isActive) {
					if (textBox.maxChars > textBox.content.length()) {
						if (Character.isAlphabetic(c) && textBox.isAlpha) {
							textBox.content += c;
						} else if ((Character.isDigit(c) || key == 52) && textBox.isNumeric) { // 52 is '.'
							textBox.content += c;
						} else if (Character.isSpaceChar(c) && textBox.isWhiteSpace) {
							textBox.content += c;
						} 
					}
					
					if (key == 14 && textBox.content.length() > 0) { // blackslash key
						textBox.content = textBox.content.substring(0, textBox.content.length() - 1);
					}
					
					if (key == 28) { // return key
						textBox.onSubmit();
					}
				}
			}
		}
	}
	
	/**
	 * Sets the allowed input on a text box.
	 * @param isAlpha Allows alphabetical characters
	 * @param isNumeric Allows numeric characters
	 * @param isWhiteSpace Allows whitespace
	 */
	public void setPermissions(boolean isAlpha, boolean isNumeric, boolean isWhiteSpace) {
		this.isAlpha = isAlpha;
		this.isNumeric = isNumeric;
		this.isWhiteSpace = isWhiteSpace;
	}
	
	/**
	 * Gets the content of the String.
	 * @return The content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Abstract method that gets called when the enter key is pressed.
	 */
	public abstract void onSubmit();
}