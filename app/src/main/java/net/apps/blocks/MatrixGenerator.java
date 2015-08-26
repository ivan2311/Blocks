package net.apps.blocks;

import java.util.Date;
import java.util.Random;

public class MatrixGenerator {

    private static final int FIRST_STAGE_ROWS = 4;
    private static final int FIRST_STAGE_COLS = 4;
    private static final int FIRST_STAGE_NUM_OF_PAIRS = 2;
    private static final int[] FIRST_STAGE_DRAWABLES = {
            R.drawable.square_blue,
            R.drawable.square_green,
            R.drawable.square_yellow,
            R.drawable.square_red
    };

    public static Matrix firstStage() {
        int[] drawablesArray = getDrawablesArray(FIRST_STAGE_DRAWABLES, FIRST_STAGE_NUM_OF_PAIRS);
        Matrix matrix = new Matrix(FIRST_STAGE_ROWS, FIRST_STAGE_COLS);
        int i=0;
        for (int row = 0; row < FIRST_STAGE_ROWS; row++) {
            for (int col = 0; col < FIRST_STAGE_COLS; col++) {
                matrix.setElement(row, col, new Element(drawablesArray[i++]));
            }
        }

        return matrix;
    }

    private static int[] getDrawablesArray(int[] drawablesSource, int numOfPairs) {
        Date date = new Date();
        Random random = new Random(date.getTime());
        int drawablesSourceLength = drawablesSource.length;
        int[] countDrawablesUsage = new int[drawablesSourceLength];
        int drawablesArrayLength = drawablesSourceLength * numOfPairs * 2; // multiply by 2 because of pair
        int[] drawablesArray = new int[drawablesArrayLength];
        for (int i=0; i<drawablesArrayLength; i++) {
            int index = random.nextInt(drawablesSourceLength);
            while (true) {
                if (countDrawablesUsage[index] < numOfPairs * 2) { // multiply by 2 because of pair
                    break;
                } else {
                    index = (index + 1) % drawablesSourceLength;
                }
            }
            countDrawablesUsage[index]++;
            drawablesArray[i] = drawablesSource[index];
        }

        return drawablesArray;
    }

}
