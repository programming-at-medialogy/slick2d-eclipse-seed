package oose2015.components;

import com.artemis.Component;

import org.newdawn.slick.geom.Vector2f;

/**
 * Created by @Kasper on 14/03/2015
 * <p/>
 * Description:
 * hold data about velocity and acceleration
 * <p/>
 * Usage:
 * Must, like all components, be added to an Entity
 */

public class MovementComponent extends Component {
    public Vector2f vel = new Vector2f(0,0);
    //public Vector2f acc = new Vector2f(0,0);
}
