package net.apps.blocks.model.stage;

import net.apps.blocks.model.Bonus;
import net.apps.blocks.model.matrix.generator.FruitsInSquareMatrixGenerator;
import net.apps.blocks.model.matrix.generator.MatrixGenerator;

import java.util.List;

public class SecondStage extends Stage {

    private static final MatrixGenerator DEFAULT_GENERATOR = new FruitsInSquareMatrixGenerator();

    public SecondStage() {
        super(DEFAULT_GENERATOR);
    }

    public SecondStage(MatrixGenerator generator) {
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
