package example;

import org.newdawn.slick.*;

/**
 * Created by Marianne on 25-11-2015.
 */
public class ActionMenu extends BasicGame {

    private Button move;
    private Button researchSt;
    private Button removeCube;
    private Button share;
    private Button eventCard;
    private Button cure;

    private Image notAvailable;

    private boolean isMoveActive;
    private boolean isResearchSActive;
    private boolean isRemoveCubeActive;
    private boolean isShareActive;
    private boolean isEventCardActive;
    private boolean isCureActive;

    private boolean playerOutTurns = false;

    public ActionMenu(String gametitle) {

        super(gametitle);
        isMoveActive = false;
        isResearchSActive = false;
        isRemoveCubeActive = false;
        isShareActive = false;
        isEventCardActive = false;
        isCureActive = false;
    }


    @Override
    public void init(GameContainer gc) throws SlickException {
        notAvailable = new Image("assets/guielements/notavailable.png");

        move = new Button("move", 950, 20, 6);
        researchSt = new Button("researchSt", 1025, 15, 7);
        removeCube = new Button("removeCube", 1100, 15, 8);
        share = new Button("share", 1160, 10, 9);
        eventCard = new Button("eventCard", 1225, 15, 10);
        cure = new Button("cure", 1275, 15, 11);


        move.init(gc);
        researchSt.init(gc);
        removeCube.init(gc);
        share.init(gc);
        eventCard.init(gc);
        cure.init(gc);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        if (!playerOutTurns) {
            if (move.clickWithin(gc)) {
                isMoveActive = !isMoveActive;
                isResearchSActive = false;
                isRemoveCubeActive = false;
                isCureActive = false;
                isShareActive = false;
                isEventCardActive = false;
            }

            if (researchSt.clickWithin(gc)) {
                isResearchSActive = !isResearchSActive;
                isMoveActive = false;
                isRemoveCubeActive = false;
                isCureActive = false;
                isShareActive = false;
                isEventCardActive = false;
            }

            if (removeCube.clickWithin(gc)) {
                isRemoveCubeActive = !isRemoveCubeActive;
                isResearchSActive = false;
                isMoveActive = false;
                isCureActive = false;
                isShareActive = false;
                isEventCardActive = false;
            }

            if (share.clickWithin(gc)) {
                isShareActive = !isShareActive;
                isMoveActive = false;
                isRemoveCubeActive = false;
                isResearchSActive = false;
                isCureActive = false;
                isEventCardActive = false;
            }

            if (eventCard.clickWithin(gc)) {
                isEventCardActive = !isEventCardActive;
                isRemoveCubeActive = false;
                isMoveActive = false;
                isResearchSActive = false;
                isCureActive = false;
                isShareActive = false;
            }

            if (cure.clickWithin(gc)) {
                isCureActive = !isCureActive;
                isMoveActive = false;
                isResearchSActive = false;
                isRemoveCubeActive = false;
                isShareActive = false;
                isEventCardActive = false;
            }

        } else {
            isMoveActive = false;
            move.setActive(false);
            isResearchSActive = false;
            researchSt.setActive(false);
            isRemoveCubeActive = false;
            removeCube.setActive(false);
            isShareActive = false;
            share.setActive(false);
            isEventCardActive = false;
            eventCard.setActive(false);
            isCureActive = false;
            cure.setActive(false);
        }


        //SET THE PICTURE INDEX NUMBERS ACCORDING TO ITS ACTIVITY
        if (isMoveActive) {
            move.setPicIndexNo(23);
        } else {
            move.setPicIndexNo(6);
        }

        if (isRemoveCubeActive) {
            removeCube.setPicIndexNo(25);
        } else {
            removeCube.setPicIndexNo(8);
        }

        if (isResearchSActive) {
            researchSt.setPicIndexNo(24);
        } else {
            researchSt.setPicIndexNo(7);
        }

        if (isShareActive) {
            share.setPicIndexNo(26);
        } else {
            share.setPicIndexNo(9);
        }

        if (isEventCardActive) {
            eventCard.setPicIndexNo(27);
        } else {
            eventCard.setPicIndexNo(10);
        }

        if (isCureActive) {
            cure.setPicIndexNo(28);
        } else {
            cure.setPicIndexNo(11);
        }


    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        move.render(gc, g);
        researchSt.render(gc, g);
        removeCube.render(gc, g);
        share.render(gc, g);
        eventCard.render(gc, g);
        cure.render(gc, g);

        if (isShareActive || isEventCardActive) {
            g.drawImage(notAvailable, 682 - notAvailable.getWidth() / 2, 384 - notAvailable.getHeight() / 2);
        }

    }

    public boolean getIsMoveActive() {
        return isMoveActive;
    }

    public boolean getIsRemoveCubeActive() {
        return isRemoveCubeActive;
    }

    public boolean getIsResearchSActive() {
        return isResearchSActive;
    }

    public boolean getIsShareActive() {
        return isShareActive;
    }

    public boolean getIsEventCardActive() {
        return isEventCardActive;
    }

    public boolean getIsCureActive() {
        return isCureActive;
    }

    public void setPlayerOutTurns(boolean playerOutTurns) {
        this.playerOutTurns = playerOutTurns;
    }

    public void setMoveActive(boolean isMoveActive) {
        this.isMoveActive = isMoveActive;
    }

    public void setResearchSActive(boolean isResearchSActive) {
        this.isResearchSActive = isResearchSActive;
    }

    public void setRemoveCubeActive(boolean isRemoveCubeActive) {
        this.isRemoveCubeActive = isRemoveCubeActive;
    }

    public void setShareActive(boolean isShareActive) {
        this.isShareActive = isShareActive;
    }

    public void setEventCardActive(boolean isEventCardActive) {
        this.isEventCardActive = isEventCardActive;
    }

    public void setCureActive(boolean isCureActive) {
        this.isCureActive = isCureActive;
    }
}

