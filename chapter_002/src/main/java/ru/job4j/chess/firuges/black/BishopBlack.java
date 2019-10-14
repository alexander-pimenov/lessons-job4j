package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int lengthOfWay = Math.abs(source.y - dest.y);
        Cell[] steps = new Cell[lengthOfWay];
        int deltaX = (dest.x - source.x) < 0 ? -1 : 1;
        int deltaY = (dest.y - source.y) < 0 ? -1 : 1;
        if (this.impossibilityToMove(source, dest)) {
            throw new ImpossibleMoveException("Impossible movement!");
        }
            for (int i = 0; i < steps.length; i++) {
                steps[i] = Cell.values()[(source.x + deltaX * (i + 1)) * 8 + (source.y + deltaY * (i + 1))];
            }
        return steps;
    }
//    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
//        if (!isMovementPossible(source, dest)) {
//            throw new ImpossibleMoveException(String.format("Move is impossible for %s", this.getClass().getSimpleName()));
//        }
//        int deltaX = dest.x - source.x;
//        int deltaY = dest.y - source.y;
//        int length = Math.max(Math.abs(deltaX), Math.abs(deltaY));
//        Cell[] steps = new Cell[length];
//        for (int i = 0; i < steps.length; i++) {
//            steps[i] = Cell.values()[(source.x + deltaX * (i + 1)) * 8 + (source.y + deltaY * (i + 1))];
//        }
//        return steps;
//    }

    private boolean impossibilityToMove(Cell source, Cell dest) {
        boolean rst = false;
        if (Math.abs(source.x - dest.x) != Math.abs(source.y - dest.y)) {
            rst = true;
        }
        return rst;
    }

    public boolean isMovementPossible(Cell source, Cell dest) {
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        return Math.abs(deltaX) == Math.abs(deltaY);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
