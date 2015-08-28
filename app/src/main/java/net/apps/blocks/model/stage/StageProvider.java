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
            position = 0;
        }
        return stages[position];
    }

    public static boolean hasStage(int position) {
        return stages.length > position;
    }
}
