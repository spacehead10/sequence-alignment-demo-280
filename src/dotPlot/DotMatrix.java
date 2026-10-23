package dotPlot;

import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import sequence.*;

import static core.Main.getScreenHeight;

public class DotMatrix {
    public static final float X_MARGIN = 50;
    public static final float Y_MARGIN = 50;
    private final Sequence seqH, seqV; // Horizontal and vertical sequences
    private Cell[][] cells;
    private int xStep, yStep;
    private final int window;
    private final int mismatchLim;

    public DotMatrix(Sequence seqH, Sequence seqV) {
        this(seqH, seqV, 1, 0);
    }

    public DotMatrix(Sequence seqH, Sequence seqV, int window, int mismatchLim) {
        this.seqH = seqH;
        this.seqV = seqV;
        cells = new Cell[seqH.length()][seqV.length()];
        xStep = 0;
        yStep = 0;
        this.window = window;
        this.mismatchLim = mismatchLim;
    }

    public DotMatrix(String seqStrH, String seqStrV, Class<? extends Sequence> seqType) {
        this(seqStrH, seqStrV, seqType, 1, 0);
    }

    public DotMatrix(String seqStrH, String seqStrV, Class<? extends Sequence> seqType, int window, int mismatchLim) {
        if (seqType.equals(NucleotideSequence.class)) {
            seqH = new NucleotideSequence(seqStrH);
            seqV = new NucleotideSequence(seqStrV);
        }
        else if (seqType.equals(AminoAcidSequence.class)) {
            seqH = new AminoAcidSequence(seqStrH);
            seqV = new AminoAcidSequence(seqStrV);
        }
        else {
            seqH = new Sequence(seqStrH);
            seqV = new Sequence(seqStrV);
        }
        cells = new Cell[seqH.length() + 1][seqV.length() + 1];
        Cell.sideLength = (getScreenHeight() - 2 * Y_MARGIN) / cells[0].length;

        cells[0][0] = new Cell(0, 0);
        for (int i = 0; i < seqH.length(); i++) {
            cells[i + 1][0] = new Cell(seqH.letterAt(i), i + 1, 0);
        }
        for (int j = 0; j < seqV.length(); j++) {
            cells[0][j + 1] = new Cell(seqV.letterAt(j), 0, j + 1);
        }
        for (int i = 1; i < cells.length; i++) {
            for (int j = 1; j < cells[0].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        xStep = 0;
        yStep = 0;
        this.window = window;
        this.mismatchLim = mismatchLim;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(X_MARGIN, Y_MARGIN, cells.length * Cell.sideLength, cells[0].length * Cell.sideLength);

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].render(g);
            }
        }

        if (xStep < seqH.length()) { // Show the info of the currently selected cell
            float curX = X_MARGIN + (xStep + 1) * Cell.sideLength;
            float curY = Y_MARGIN + (yStep + 1) * Cell.sideLength;
            g.setColor(Color.green);
            g.drawRect(curX, curY, Cell.sideLength, Cell.sideLength);
            g.setColor(Color.black);
            float infoX = curX + Cell.sideLength / 2;
            float infoY = curY + Cell.sideLength / 2;
            Media.drawAlignedString(seqH.letterAt(xStep) + " = " + seqV.letterAt(yStep) + ":", infoX, infoY,
                    Media.CENTER, Media.BOTTOM, Media.font16, g);
            Media.drawAlignedString("" + seqH.letterAt(xStep).equals(seqV.letterAt(yStep)), infoX, infoY,
                    Media.CENTER, Media.TOP, Media.font16, g);
        }
        else { // Highlight the diagonal at the very end
            int i = 0;
            int j = 0;
            while (i < seqH.length() && j < seqV.length()) {
                cells[i + 1][j + 1].highlight = true;
                i++;
                j++;
            }
        }
    }

    public void keyPressed(int key) {
        switch (key) {
            case Input.KEY_ENTER -> completeAll();
            case Input.KEY_DOWN -> completeStep();
        }
    }

    private void completeStep() {
        if (xStep < seqH.length()) {
            if (yStep < seqV.length()) {
                if (seqH.letterAt(xStep).equals(seqV.letterAt(yStep))) {
                    cells[xStep + 1][yStep + 1].hasDot = true;
                }
                yStep++;
            }
            if (yStep >= seqV.length()) {
                xStep++;
                yStep = 0;
            }
        }
    }

    private void completeAll() {
        while (xStep < seqH.length()) {
            completeStep();
        }
    }

    private static class Cell {
        private static float sideLength; // Initialized separately in DotMatrix constructor
        private String text;
        private int gx, gy;
        private boolean hasDot;
        private boolean highlight;

        private Cell(String text, int gx, int gy) {
            this.text = text;
            this.gx = gx;
            this.gy = gy;
            hasDot = false;
            highlight = false;
        }

        private Cell(int gx, int gy) {
            this("", gx, gy);
        }

        private void render(Graphics g) {
            if (hasDot) {
                if (highlight && gx == gy) { // Highlight the diagonal pattern of a perfect match
                    g.setColor(Color.blue);
                }
                else {
                    g.setColor(Color.black);
                }
                g.fillRect(px(), py(), sideLength, sideLength);
            }
            else if (!text.isBlank()) {
                g.setColor(Color.black);
                Media.drawAlignedString(text, px() + sideLength / 2, py() + sideLength / 2, Media.CENTER, Media.CENTER,
                        Media.font32, g);
            }
            g.setColor(Color.black);
            g.drawRect(px(), py(), sideLength, sideLength);
        }

        private float px() {
            return X_MARGIN + sideLength * gx;
        }

        private float py() {
            return Y_MARGIN + sideLength * gy;
        }
    }
}
