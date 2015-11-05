package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by TMA on 05-11-2015.
 */
public class InputNameScreen extends BasicGameState{

    Image bg;
    TextField textField;
    Button b;

    static String tmpName;

    public InputNameScreen(){}

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        bg = new Image("assets/namescreenbg.png");
        textField = new TextField(gc,gc.getDefaultFont(),625,130,560,65);
        textField.setBorderColor(org.newdawn.slick.Color.yellow);
        b = new Button("Button", 43, 200, 0);
        b.init(gc);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.drawImage(bg,0,0);
        textField.render(gc,g);
        b.render(gc,g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        tmpName = textField.getText();
        b.update(gc, i);
        if(b.clickWithin(gc)) {
            sbg.enterState(1);
        }

    }
}
