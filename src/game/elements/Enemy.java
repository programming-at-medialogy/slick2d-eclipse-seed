package game.elements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Enemy extends Element {
    
    public Enemy(float x, float y) throws SlickException{
    	super(x,y);
    	sprite = new Image("D:/Medialogy/MED6/Oose/OOSE2015/images");
    }

}
