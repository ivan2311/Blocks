package net.apps.blocks.model.stage;

import net.apps.blocks.model.Bonus;
import net.apps.blocks.model.matrix.Matrix;
import net.apps.blocks.model.matrix.generator.MatrixGenerator;

import java.util.List;

public abstract class Stage {

    private MatrixGenerator generator;

    public Stage(MatrixGenerator generator) {
        this.generator = generator;
    }

    public void setGenerator(MatrixGenerator generator) {
        this.generator = generator;
    }

    public Matrix getMatrix() {
        return generator.generateMatrix();
    }

    public abstract int getBackground();

    public abstract List<Bonus> getBonuses();

    public int[] getTypes() {
        return generator.getTypes();
    }

}
