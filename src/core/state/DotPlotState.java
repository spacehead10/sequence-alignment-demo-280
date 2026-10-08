package core.state;

import dotPlot.DotMatrix;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import sequence.*;

public class DotPlotState extends BasicGameState {
    private int id;

    public DotPlotState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    private StateBasedGame sbg;

    private DotMatrix dotMatrix;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        // TODO: Remove this after making an actual system for creating a dot matrix with user-selected sequences
        dotMatrix = new DotMatrix("ACTAGTGTATTAGC", "ACTAGTTATGTAGC", NucleotideSequence.class);
//        dotMatrix = new DotMatrix("ACTAGTGTATTAGC", "ACTAGTGTATTAGC", NucleotideSequence.class);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        dotMatrix.render(g);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
    }

    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_ESCAPE -> System.exit(0);
            default -> dotMatrix.keyPressed(key);
        }
    }

    public void mousePressed(int button, int x, int y) {
    }
}
