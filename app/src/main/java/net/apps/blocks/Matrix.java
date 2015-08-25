package net.apps.blocks;

import net.apps.blocks.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Korisnik on 8/24/2015.
 */
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
        elements = new Element[rows+2][cols+2];

        for (int row=0; row<rows+2; row++) {
            elements[row][0] = new Element();
            elements[row][cols+1] = new Element();
        }

        for (int col=1; col<cols+1; col++) {
            elements[0][col] = new Element();
            elements[rows+1][col] = new Element();
        }
    }

    public Element getElement(int row, int col) {
        return elements[row+1][col+1];
    }

    public Element getElementByPosition(int position) {
        int row = position / cols;
        int col = position % cols;
        return elements[row+1][col+1];
    }

    public void setElement(int row, int col, Element element) {
        elements[row+1][col+1] = element;

    }

    public int getCount() {
        return rows*cols;
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

    public List<String> getPath(int pos1, int pos2) {
        int x1 = pos1 / cols;
        int y1 = pos1 % cols;
        int x2 = pos2 / cols;
        int y2 = pos2 % cols;

        return getPath(x1,y1,x2,y2);
    }

    public List<String> getPath(int x1, int y1, int x2, int y2) {

        List<String> path = new ArrayList<>();

        if (!elements[x1+1][y1+1].equals(elements[x2+1][y2+1])) {
            path.add("ILLEGAL");
            return path;
        }

        List<Direction> directions = getPath(x1, y1, x2, y2, new ArrayList<Direction>(), null, 0);

        if (directions != null) {
            for (Direction dir : directions) {
                path.add(dir.name());
            }
        } else {
            path.add("ILLEGAL!!!");
        }

        return path;
    }


    private List<Direction> getPath(int x1, int y1, int x2, int y2, List<Direction> directions, Direction lastDir, int countTurn) {
        if (lastDir != null) {
            if (x1<-1 || x1 > rows || y1<-1 || y1 > cols) {
                return null;
            }

            if (countTurn > 2) return null;

            if (x1 == x2 && y1 == y2) {
                return directions;
            }

            if (!elements[x1+1][y1+1].isEmpty()) return null;

        }

        Direction newDir;

        //check UP
        if (lastDir != Direction.DOWN) {
            newDir = Direction.UP;
            directions.add(newDir);
            if (lastDir == null || lastDir == Direction.UP) {
                List<Direction> newDirections = getPath(x1-1, y1, x2, y2, directions, newDir, countTurn);
                if (newDirections == null) {
                    directions.remove(directions.size()-1);
                } else {
                    return newDirections;
                }
            } else {
                List<Direction> newDirections = getPath(x1-1, y1, x2, y2, directions, newDir, countTurn+1);
                if (newDirections == null) {
                    directions.remove(directions.size()-1);
                } else {
                    return newDirections;
                }
            }
        }

        //check DOWN
        if (lastDir != Direction.UP) {
            newDir = Direction.DOWN;
            directions.add(newDir);
            if (lastDir == null || lastDir == Direction.DOWN) {
                List<Direction> newDirections = getPath(x1+1, y1, x2, y2, directions, newDir, countTurn);
                if (newDirections == null) {
                    directions.remove(directions.size()-1);
                } else {
                    return newDirections;
                }
            } else {
                List<Direction> newDirections = getPath(x1+1, y1, x2, y2, directions, newDir, countTurn+1);
                if (newDirections == null) {
                    directions.remove(directions.size()-1);
                } else {
                    return newDirections;
                }
            }
        }
        //check LEFT
        if (lastDir != Direction.RIGHT) {
            newDir = Direction.LEFT;
            directions.add(newDir);
            if (lastDir == null || lastDir == Direction.LEFT) {
                List<Direction> newDirections = getPath(x1, y1-1, x2, y2, directions, newDir, countTurn);
                if (newDirections == null) {
                    directions.remove(directions.size()-1);
                } else {
                    return newDirections;
                }
            } else {
                List<Direction> newDirections = getPath(x1, y1-1, x2, y2, directions, newDir, countTurn+1);
                if (newDirections == null) {
                    directions.remove(directions.size()-1);
                } else {
                    return newDirections;
                }
            }
        }
        //check RIGHT
        if (lastDir != Direction.LEFT) {
            newDir = Direction.RIGHT;
            directions.add(newDir);
            if (lastDir == null || lastDir == Direction.RIGHT) {
                List<Direction> newDirections = getPath(x1, y1+1, x2, y2, directions, newDir, countTurn);
                if (newDirections == null) {
                    directions.remove(directions.size()-1);
                } else {
                    return newDirections;
                }
            } else {
                List<Direction> newDirections = getPath(x1, y1+1, x2, y2, directions, newDir, countTurn+1);
                if (newDirections == null) {
                    directions.remove(directions.size()-1);
                } else {
                    return newDirections;
                }
            }
        }
        return null;
    }

}
