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

public class MoveableEntity extends Entity{

    public Vector2f velocity;
    public Vector2f acceleration;

    public float mass;
    public float friction;

    public boolean isSolid;


    protected void move(int dt){

    }
}
