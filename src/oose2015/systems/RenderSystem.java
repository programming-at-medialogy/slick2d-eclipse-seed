package oose2015.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.Bag;
import oose2015.Settings;
import oose2015.components.TextureComponent;
import oose2015.components.TransformComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.Comparator;

/**
 * Created by @Kasper on 14/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class RenderSystem extends EntityProcessingSystem {

    private GameContainer gameContainer;
    private Bag<Entity> renderQueue;

    private Comparator<Entity> comparator;

    @Wire ComponentMapper<TransformComponent> tm;
    @Wire ComponentMapper<TextureComponent> texm;

    public RenderSystem(GameContainer gameContainer){
        //noinspection unchecked
        super(Aspect.getAspectForAll(TransformComponent.class, TextureComponent.class));

        this.gameContainer = gameContainer;
        renderQueue = new Bag<Entity>();

        //comparator to compar entites draw order
        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity a, Entity b) {
                return (int)Math.signum(texm.get(a).order - texm.get(b).order);
            }
        };
    }

    @Override
    protected void inserted(Entity entity) {
                renderQueue.add(entity);

        renderQueue.sort(comparator);

        System.out.println(entity + " added to render system");
    }

    @Override
    protected  void removed(Entity entity){
        renderQueue.remove(entity);
    }

    public void render(){
        //get current graphics
        Graphics g = gameContainer.getGraphics();

        //loop through render queue
        for(int i = 0;i<renderQueue.size();i++) {
            Entity entity = renderQueue.get(i);

            //get components
            TransformComponent transform = tm.get(entity);
            TextureComponent texture = texm.get(entity);

            g.pushTransform();

            //transformations
            g.translate(transform.pos.x, transform.pos.y);
            g.rotate(transform.pos.x, transform.pos.y, transform.rotation);
            g.scale(transform.scale.x, transform.scale.y);

            //set color
            g.setColor(texture.color);

            //draw any given shape
            g.fill(texture.shape);


            if(Settings.DEBUG){
                g.setColor(Color.black);
                g.drawString(Byte.toString(texture.order),0,0);
            }


            g.popTransform();
        }
    }

    @Override
    protected void process(Entity e) {

    }
}
