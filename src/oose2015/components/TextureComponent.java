package oose2015.components;

import com.artemis.Component;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by @Kasper on 14/03/2015
 * <p/>
 * Description:
 * This Component holds data for what shape, color, size and drawing order
 * <p/>
 * Usage:
 * Must, like all components, be added to an Entity
 */

public class TextureComponent extends Component {
    public Color color;
    public Vector2f size;
    public Shape shape;

    //the greatest is on top ( higher )
    public byte order = 0;
}
