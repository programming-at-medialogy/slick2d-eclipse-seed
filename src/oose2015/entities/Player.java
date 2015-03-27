package oose2015.entities;

import org.newdawn.slick.geom.Vector2f;

/**
 * Created by @Kasper on 26/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class Player extends Agent {

    public float health;

    public Player(Vector2f position){
        this.position = position;
        System.out.println("Player created");
    }
}
