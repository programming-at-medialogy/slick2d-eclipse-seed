package oose2015.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import oose2015.components.BodyComponent;
import oose2015.components.MovementComponent;
import oose2015.components.TransformComponent;


/**
 * Created by @Kasper on 14/03/2015
 * <p/>
 * Description:
 * Movement system used for every non player entity, NOT USED ATM
 * <p/>
 * Usage:
 * ---
 */

public class MovementSystem extends EntityProcessingSystem{

    public MovementSystem() {
        //noinspection unchecked
        super(Aspect.getAspectForAll(TransformComponent.class, MovementComponent.class, BodyComponent.class));
    }

    @Override
    protected void inserted(Entity entity) {
        System.out.println(entity + " non player added to movement system");
    }

    @Override
    protected void process(Entity entity) {

    }
}
