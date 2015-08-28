package net.apps.blocks.model.matrix;

import net.apps.blocks.model.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrix {

    private enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    private int rows;
    private int cols;
    private Element[][] elements;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        initElements();
    }

    private void initElements() {
        elements = new Element[rows + 2][cols + 2];

        for (int row = 0; row < rows + 2; row++) {
            elements[row][0] = new Element();
            elements[row][cols + 1] = new Element();
        }

        for (int col = 1; col < cols + 1; col++) {
            elements[0][col] = new Element();
            elements[rows + 1][col] = new Element();
        }
    }

    public Element getElement(int row, int col) {
        return elements[row + 1][col + 1];
    }

    public Element getElementAtPosition(int position) {
        int row = position / cols;
        int col = position % cols;
        return elements[row + 1][col + 1];
    }

    public void setElement(int row, int col, Element element) {
        elements[row + 1][col + 1] = element;

    }

    public int getCount() {
        return rows * cols;
    }

    public int getNumCols() {
        return cols;
    }

    public int getNumRows() {
        return rows;
    }

    public int getRowByPosition(int position) {
        return position / cols;
    }

    public int getColByPosition(int position) {
        return position % cols;
    }

    public boolean isFinished() {
        for (int row = 1; row < rows + 1; row++) {
            for (int col = 1; col < cols + 1; col++) {
                if (elements[row][col].getStatus() != Element.STATUS_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> getPath(int pos1, int pos2, boolean illegalConnect) {
        int x1 = pos1 / cols + 1;
        int y1 = pos1 % cols + 1;
        int x2 = pos2 / cols + 1;
        int y2 = pos2 % cols + 1;

        return getPath(x1, y1, x2, y2, illegalConnect);
    }

    public List<String> getPath(int x1, int y1, int x2, int y2, boolean illegalConnect) {

        List<String> path = new ArrayList<>();

        if (!elements[x1][y1].equals(elements[x2][y2])) {
            return path;
        }

        List<Direction> directions = getPath(x1, y1, x2, y2, new ArrayList<Direction>(), null, 0, illegalConnect);

        if (directions != null) {
            for (Direction dir : directions) {
                path.add(dir.name());
            }
        }

        return path;
    }


    private List<Direction> getPath(int x1, int y1, int x2, int y2, List<Direction> directions, Direction lastDir, int countTurn, boolean illegalConnect) {
        if (lastDir != null) {
            if (x1 < 0 || x1 > rows || y1 < 0 || y1 > cols) {
                return null;
            }

            if (countTurn > 2) return null;

            if (x1 == x2 && y1 == y2) {
                return directions;
            }

            if (!illegalConnect && elements[x1][y1].getStatus() != Element.STATUS_EMPTY) {
                return null;
            }

        }

        Direction newDir;

        //check UP
        if (lastDir != Direction.DOWN) {
            newDir = Direction.UP;
            directions.add(newDir);
            int turn = 1;
            if (lastDir == null || lastDir == Direction.UP) {
                turn = 0;
            }
            List<Direction> newDirections = getPath(x1 - 1, y1, x2, y2, directions, newDir, countTurn + turn, illegalConnect);
            if (newDirections == null) {
                directions.remove(directions.size() - 1);
            } else {
                return newDirections;
            }
        }

        //check DOWN
        if (lastDir != Direction.UP) {
            newDir = Direction.DOWN;
            directions.add(newDir);
            int turn = 1;
            if (lastDir == null || lastDir == Direction.DOWN) {
                turn = 0;
            }
            List<Direction> newDirections = getPath(x1 + 1, y1, x2, y2, directions, newDir, countTurn + turn, illegalConnect);
            if (newDirections == null) {
                directions.remove(directions.size() - 1);
            } else {
                return newDirections;
            }
        }
        //check LEFT
        if (lastDir != Direction.RIGHT) {
            newDir = Direction.LEFT;
            directions.add(newDir);
            int turn = 1;
            if (lastDir == null || lastDir == Direction.LEFT) {
                turn = 0;
            }
            List<Direction> newDirections = getPath(x1, y1 - 1, x2, y2, directions, newDir, countTurn + turn, illegalConnect);
            if (newDirections == null) {
                directions.remove(directions.size() - 1);
            } else {
                return newDirections;
            }
        }
        //check RIGHT
        if (lastDir != Direction.LEFT) {
            newDir = Direction.RIGHT;
            directions.add(newDir);
            int turn = 1;
            if (lastDir == null || lastDir == Direction.RIGHT) {
                turn = 0;
            }
            List<Direction> newDirections = getPath(x1, y1 + 1, x2, y2, directions, newDir, countTurn + turn, illegalConnect);
            if (newDirections == null) {
                directions.remove(directions.size() - 1);
            } else {
                return newDirections;
            }
        }
        return null;
    }

    public int getPair(int pos1) {

        if (getStatusElementAtPosition(pos1) == Element.STATUS_EMPTY) {
            return -1;
        }

        int count = getCount();

        for (int pos2 = 0; pos2 < count; pos2++) {
            if (pos1 != pos2 && getStatusElementAtPosition(pos2) == Element.STATUS_FULL) {
                List<String> path = getPath(pos1, pos2, false);
                if (!path.isEmpty()) {
                    return pos2;
                }
            }
        }

        return -1;
    }


    public void setStatusElementAtPosition(int position, int status) {
        Element element = getElementAtPosition(position);
        element.setStatus(status);
    }

    public int getStatusElementAtPosition(int position) {
        Element element = getElementAtPosition(position);
        return element.getStatus();
    }

    public List<Integer> getElementPositionsByType(int type) {
        List<Integer> positions = new ArrayList<>();
        int count = getCount();
        for (int pos = 0; pos < count; pos++) {
            Element element = getElementAtPosition(pos);
            if (element.getType() == type && element.getStatus() == Element.STATUS_FULL) {
                positions.add(pos);
            }
        }
        return positions;
    }

    public List<Integer> getElementPositionsByRandomType() {
        Element element = getRandomElement();
        return getElementPositionsByType(element.getType());
    }

    private Element getRandomElement() {
        Random rand = new Random();
        int pos = rand.nextInt(getCount());
        while (true) {
            Element element = getElementAtPosition(pos);
            if (element.getStatus() == Element.STATUS_FULL) {
                return element;
            }
            pos = (pos + 1) % getCount();
        }
    }


}
