package dotPlot;

import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import sequence.*;

import static core.Main.getScreenHeight;

public class DotMatrix {
    private static final float X_MARGIN = 50;
    private static final float Y_MARGIN = 50;
    private Sequence seqH, seqV; // Horizontal and vertical sequences
    private Cell[][] cells;

    public DotMatrix(Sequence seqH, Sequence seqV) {
        this.seqH = seqH;
        this.seqV = seqV;
        cells = new Cell[seqH.length()][seqV.length()];
    }

    public DotMatrix(String seqStrH, String seqStrV, Class<? extends Sequence> seqType) {
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
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(X_MARGIN, Y_MARGIN, cells.length * Cell.sideLength, cells[0].length * Cell.sideLength);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].render(g);
            }
        }
    }

    public void keyPressed(int key) {
        if (key == Input.KEY_ENTER) {
            completeAll();
        }
    }

    private void completeAll() {
        for (int i = 0; i < seqH.length(); i++) {
            for (int j = 0; j < seqV.length(); j++) {
                if (seqH.letterAt(i).equals(seqV.letterAt(j))) {
                    cells[i + 1][j + 1].hasDot = true;
                }
            }
        }
    }

    private static class Cell {
        private static float sideLength; // Initialized separately in DotMatrix constructor
        private String text;
        private int gx, gy;
        private boolean hasDot;

        private Cell(String text, int gx, int gy) {
            this.text = text;
            this.gx = gx;
            this.gy = gy;
            hasDot = false;
        }

        private Cell(int gx, int gy) {
            this("", gx, gy);
        }

        private void render(Graphics g) {
            if (hasDot) {
                if (gx == gy) { // Highlight the diagonal pattern of a match
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
