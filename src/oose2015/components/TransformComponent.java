package oose2015.components;

import com.artemis.Component;

import org.newdawn.slick.geom.Vector2f;

/**
 * Created by @Kasper on 14/03/2015
 * <p/>
 * Description:
 * holds data about the transform of an entity ie. its position, scale and rotation
 * <p/>
 * Usage:
 * Must, like all components, be added to an Entity
 */

public class TransformComponent extends Component {

    public Vector2f pos;

    public Vector2f scale = new Vector2f(1,1);
    public float rotation;
}
