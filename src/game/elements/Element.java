package game.elements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
 
public abstract class Element {
	protected float x;
	protected float y;
	protected Image sprite;
 
	public Element(float x, float y) throws SlickException{
		this.x = x;
		this.y = y;
		// ----- Placeholder for image -----
		sprite = new Image("data/img/placeholder_sprite.png");
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void render(){
		sprite.draw(x-2,y-2);
	}
}