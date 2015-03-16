package oose2015.states;

import com.artemis.Entity;
import com.artemis.World;

import oose2015.EntityFactory;
import oose2015.components.TransformComponent;
import oose2015.systems.MovementSystem;
import oose2015.systems.PlayerMovementSystem;
import oose2015.systems.RenderSystem;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by @Kasper on 14/03/2015
 * <p/>
 * Description:
 * Game State when the game run this will be updating
 * <p/>
 * Usage:
 * ---
 */

public class GameplayState implements GameState {

    World world;
    GameContainer container;
    StateBasedGame sbg;

    //Systems
    RenderSystem renderSystem;


    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container = gameContainer;
        this.sbg = stateBasedGame;
        world = new World();
        EntityFactory.setWorld(world);

        //create systems
        renderSystem = new RenderSystem(gameContainer);
        world.setSystem(new PlayerMovementSystem(gameContainer));
        world.setSystem(new MovementSystem());
        world.setSystem(renderSystem,false);

        //init world and systems
        world.initialize();

        //init entities
        initPlayer();
        initMonster();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.setBackground(new Color(0x161515));
        renderSystem.render();

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        world.setDelta(delta);
        world.process();

    }

    private void initPlayer(){
        Entity player = EntityFactory.create_player();

        player.getComponent(TransformComponent.class).pos = new Vector2f(0,0);

    }

    private void initMonster(){
        Entity monster = EntityFactory.create_monster();

        monster.getComponent(TransformComponent.class).pos = new Vector2f(10,100);
    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        System.out.println("entering game state");
    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void controllerLeftPressed(int i) {

    }

    @Override
    public void controllerLeftReleased(int i) {

    }

    @Override
    public void controllerRightPressed(int i) {

    }

    @Override
    public void controllerRightReleased(int i) {

    }

    @Override
    public void controllerUpPressed(int i) {

    }

    @Override
    public void controllerUpReleased(int i) {

    }

    @Override
    public void controllerDownPressed(int i) {

    }

    @Override
    public void controllerDownReleased(int i) {

    }

    @Override
    public void controllerButtonPressed(int i, int i1) {

    }

    @Override
    public void controllerButtonReleased(int i, int i1) {

    }

    @Override
    public void keyPressed(int i, char c) {

    }

    @Override
    public void keyReleased(int i, char c) {

    }

    @Override
    public void mouseWheelMoved(int i) {

    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mousePressed(int i, int i1, int i2) {

    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {

    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return false;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }
}
