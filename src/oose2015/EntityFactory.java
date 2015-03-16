package oose2015;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;

import oose2015.components.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by @Kasper on 14/03/2015
 *
 * Class is meant to keep every entity that can be created. class is static
 */

public class EntityFactory {

    private static World world;

    public static void setWorld(World w){
        world = w;
    }

    /**
     * Return a player entity with transform, texture, movement, player component
     * @return Entity player
     */
    public static Entity create_player(){
        Entity entity = world.createEntity();
        EntityEdit edit = entity.edit();

        TransformComponent transform = new TransformComponent();
        TextureComponent texture = new TextureComponent();
        MovementComponent movement = new MovementComponent();
        PlayerComponent player = new PlayerComponent(Input.KEY_LEFT,Input.KEY_RIGHT, Input.KEY_UP,Input.KEY_DOWN);

        Vector2f size = new Vector2f(50,50);
        texture.shape = new Rectangle(-size.x/2,-size.y/2,size.x,size.y);
        texture.color = new Color(0xbada55);
        texture.size = new Vector2f(size);
        texture.order = -1;

        player.speed = new Vector2f(10,10);
        player.drag = 0.4f;

        edit.add(transform);
        edit.add(texture);
        edit.add(movement);
        edit.add(player);

        return edit.getEntity();
    }

    public static Entity create_monster(){
        Entity entity = world.createEntity();
        EntityEdit edit = entity.edit();

        TransformComponent transform = new TransformComponent();
        TextureComponent texture = new TextureComponent();
        MovementComponent movement = new MovementComponent();
        BodyComponent body = new BodyComponent();
        MonsterComponent monster = new MonsterComponent();


        Vector2f size = new Vector2f(25,25);
        texture.shape = new Circle(0,0,size.x/2);
        texture.color = new Color(0xee1000);
        texture.size = new Vector2f(size);

        body.speed = new Vector2f(7,7);
        body.drag = 0.6f;

        edit.add(transform);
        edit.add(texture);
        edit.add(movement);
        edit.add(body);
        edit.add(monster);

        return edit.getEntity();
    }



}
