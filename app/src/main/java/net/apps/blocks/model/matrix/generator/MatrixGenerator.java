package net.apps.blocks.model.matrix.generator;

import net.apps.blocks.model.Element;
import net.apps.blocks.model.matrix.Matrix;

import java.util.Date;
import java.util.Random;

public abstract class MatrixGenerator {

    public abstract int[] getDrawables();

    public abstract int getNumOfPairs();

    public abstract int getNumOfRows();

    public abstract int getNumOfCols();

    public Matrix generateMatrix() {
        int[] drawablesArray = getDrawablesArray(getDrawables(), getNumOfPairs());
        int numOfRows = getNumOfRows();
        int numOfCols = getNumOfCols();
        Matrix matrix = new Matrix(numOfRows, numOfCols);
        int i=0;
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col++) {
                matrix.setElement(row, col, new Element(drawablesArray[i++]));
            }
        }
        return matrix;
    }

    private int[] getDrawablesArray(int[] drawablesSource, int numOfPairs) {
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
