package oose2015.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import oose2015.Settings;
import oose2015.components.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by @Kasper on 14/03/2015
 * <p/>
 * Description:
 * System for player movement, handles input
 * <p/>
 * Usage:
 * ---
 */

public class PlayerMovementSystem extends EntityProcessingSystem{

    private Input input;

    @Wire ComponentMapper<TransformComponent> tm;
    @Wire ComponentMapper<MovementComponent> mm;
    @Wire ComponentMapper<PlayerComponent> pm;

    public PlayerMovementSystem(GameContainer gameContainer) {
        //noinspection unchecked
        super(Aspect.getAspectForAll(TransformComponent.class, MovementComponent.class, PlayerComponent.class).exclude(MonsterComponent.class));

        input = gameContainer.getInput();
    }


    @Override
    protected void inserted(Entity entity) {
        System.out.println(entity + " player added to movement system");
    }

    @Override
    protected void process(Entity entity) {
        //get component
        TransformComponent transform = tm.get(entity);
        MovementComponent movement = mm.get(entity);
        PlayerComponent player = pm.get(entity);

        //update keys
        player.updateKeys(input);

        //init temp. vars
        float delta = world.getDelta();
        Vector2f input = new Vector2f(0,0);

        //handle input
        if(player.downDown && !player.upDown)
            input.y = 1;
        else if(player.upDown && !player.downDown)
            input.y = -1;

        if(player.leftDown && !player.rightDown)
            input.x = -1;
        else if(player.rightDown && !player.leftDown )
            input.x = 1;

        //multiplies player speed with the current input
        input.set(input.x * player.speed.x, input.y * player.speed.y);

        //adds input to current vel scales with constants and deltatime
        movement.vel.add(input);
        movement.vel.scale(player.drag * Settings.PIXELS_TO_METERS * delta);

        //add the velocity to position
        transform.pos.add(movement.vel);

    }
}
