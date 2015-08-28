package net.apps.blocks.model.matrix.generator;

import net.apps.blocks.R;
import net.apps.blocks.model.matrix.generator.MatrixGenerator;

public class FruitsInSquareMatrixGenerator extends MatrixGenerator {

    private static final int NUM_OF_PAIRS = 2;
    private static final int NUM_OF_ROWS = 4;
    private static final int NUM_OF_COLS = 4;
    private static final int[] DRAWABLES = new int[] {
            R.drawable.ic_strawberry,
            R.drawable.ic_grape,
            R.drawable.ic_lime,
            R.drawable.ic_nut
    };

    @Override
    public int[] getDrawables() {
        return DRAWABLES;
    }

    @Override
    public int getNumOfPairs() {
        return NUM_OF_PAIRS;
    }

    @Override
    public int getNumOfRows() {
        return NUM_OF_ROWS;
    }

    @Override
    public int getNumOfCols() {
        return NUM_OF_COLS;
    }

    @Override
    public int[] getTypes() {
        return DRAWABLES;
    }
}
