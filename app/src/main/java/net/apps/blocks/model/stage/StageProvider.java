package net.apps.blocks.model.stage;

import net.apps.blocks.model.matrix.generator.SquaresInRectangleMatrixGenerator;

public class StageProvider {

    private static Stage[] stages = new Stage[] {
            new FirstStage(),
            new FirstStage(new SquaresInRectangleMatrixGenerator()),
            new SecondStage()
    };

    public static Stage getStage(int position) {
        if (stages.length > position) {
            return stages[position];
        }
        return null;
    }

}
