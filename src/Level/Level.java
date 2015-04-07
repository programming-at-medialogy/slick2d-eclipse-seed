package Level;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
 
public class Level {
 
    private TiledMap map;
 
    public Level(String level) throws SlickException{
        map = new TiledMap("levels/" + level + ".tmx","/images");
    }
 
    public void render(){
        map.render(0, 0);
    }
}