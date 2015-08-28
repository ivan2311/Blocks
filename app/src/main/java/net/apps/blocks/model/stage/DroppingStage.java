package net.apps.blocks.model.stage;

import net.apps.blocks.model.Bonus;
import net.apps.blocks.model.matrix.Matrix;
import net.apps.blocks.model.matrix.generator.MatrixGenerator;
import net.apps.blocks.model.matrix.generator.SquaresInSquareMatrixGenerator;

import java.util.List;

public class DroppingStage extends Stage {

    private static final MatrixGenerator DEFAULT_GENERATOR = new SquaresInSquareMatrixGenerator();

    public DroppingStage() {
        super(DEFAULT_GENERATOR);
    }

    public DroppingStage(MatrixGenerator generator) {
        super(generator);
    }

    @Override
    public int getBackground() {
        return 0;
    }

    @Override
    public List<Bonus> getBonuses() {
        return null;
    }

}
