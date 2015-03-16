package oose2015.components;

import com.artemis.Component;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by @Kasper on 16/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * Must, like all components, be added to an Entity

 */

public class BodyComponent extends Component {
    public Vector2f speed;
    public float drag;
}
