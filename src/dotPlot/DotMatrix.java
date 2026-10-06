package dotPlot;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import sequence.*;

import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class DotMatrix {
    private static final float X_MARGIN = 50;
    private static final float Y_MARGIN = 50;
    private Sequence seq1, seq2;
    private Cell[][] cells;

    public DotMatrix(Sequence seq1, Sequence seq2) {
        this.seq1 = seq1;
        this.seq2 = seq2;
        cells = new Cell[seq1.length()][seq2.length()];
    }

    public DotMatrix(String seqStr1, String seqStr2, Class<? extends Sequence> seqType) {
        if (seqType.equals(NucleotideSequence.class)) {
            seq1 = new NucleotideSequence(seqStr1);
            seq2 = new NucleotideSequence(seqStr2);
        }
        else if (seqType.equals(AminoAcidSequence.class)) {
            seq1 = new AminoAcidSequence(seqStr1);
            seq2 = new AminoAcidSequence(seqStr2);
        }
        else {
            seq1 = new Sequence(seqStr1);
            seq2 = new Sequence(seqStr2);
        }
        cells = new Cell[seq1.length() + 1][seq2.length() + 1];
        Cell.sideLength = (getScreenHeight() - 2 * Y_MARGIN) / cells[0].length;

        // TODO: Replace this loop with an actual system for initializing the cells with the provided sequences
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
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

    private static class Cell {
        private static float sideLength; // Initialized separately in DotMatrix constructor
        private String text;
        private int gx, gy;

        private Cell(String text, int gx, int gy) {
            this.text = text;
            this.gx = gx;
            this.gy = gy;
        }

        private Cell(int gx, int gy) {
            this("", gx, gy);
        }

        private void render(Graphics g) {
            g.setColor(Color.black);
            g.drawRect(X_MARGIN + sideLength * gx, Y_MARGIN + sideLength * gy, sideLength, sideLength);
        }
    }
}
