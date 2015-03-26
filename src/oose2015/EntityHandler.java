package oose2015;

import oose2015.entities.Enemy;
import oose2015.entities.Entity;
import oose2015.entities.Player;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

/**
 * Created by @Kasper on 26/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class EntityHandler {
    ArrayList<Entity> entities;

    public EntityHandler(){
        entities = new ArrayList<Entity>();
        entities.add(new Player(new Vector2f(0,0)));
        entities.add(new Enemy(new Vector2f(0,0)));


    }

    public void update(int dt){
        for (Entity entity : entities) {
            entity.update(dt);
        }
    }

    public void render(Graphics graphics){
        for (Entity entity : entities) {
            entity.render(graphics);
        }
    }

}
