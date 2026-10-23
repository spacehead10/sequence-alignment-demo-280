package core.state;

import core.Media;
import dotPlot.DotMatrix;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import sequence.*;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

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
    private String seq1, seq2;
    private int window, mismatchLim;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        // TODO: Remove this after making an actual system for creating a dot matrix with user-selected sequences
        makeDotMatrix("ACTAGTGTATTAGC", "ACTAGTTATGTAGC", NucleotideSequence.class, 1, 0);
//        makeDotMatrix("ACTAGTGTATTAGC", "ACTAGTGT", NucleotideSequence.class, 1, 0);
//        makeDotMatrix("ACTAGTGT", "ACTAGTGTATTAGC", NucleotideSequence.class, 1, 0);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        dotMatrix.render(g);
        renderInfoPanel(g);
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

    private void makeDotMatrix(String seqStrH, String seqStrV, Class<? extends Sequence> seqType, int window,
                               int mismatchLim) {
        dotMatrix = new DotMatrix(seqStrH, seqStrV, seqType, window, mismatchLim);
        seq1 = seqStrH;
        seq2 = seqStrV;
        this.window = window;
        this.mismatchLim = mismatchLim;
    }

    private void renderInfoPanel(Graphics g) {
        float leftBound = 2 * DotMatrix.X_MARGIN + (getScreenHeight() - 2 * DotMatrix.Y_MARGIN);
        float topBound = DotMatrix.Y_MARGIN;

        g.setColor(Color.white);
        g.drawRect(leftBound, topBound, getScreenWidth() - leftBound - DotMatrix.X_MARGIN,
                (getScreenHeight() - 2 * topBound));

        String[] infoLines = new String[] {
                "Sequences:",
                seq1,
                seq2,
                "",
                "Parameters:",
                "Window size = " + window,
                "Mismatch limit = " + mismatchLim,
                "Stringency = " + (window - mismatchLim)
        };
        g.setFont(Media.font32);
        for (int i = 0; i < infoLines.length; i++) {
            g.drawString(infoLines[i], leftBound + 10, topBound + 10 + i * Media.font32.getHeight());
        }
    }
}
