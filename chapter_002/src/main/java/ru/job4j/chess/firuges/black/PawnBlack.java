package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack implements Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    public boolean isMovementPossible(Cell source, Cell dest) {
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        return ((deltaX == 0) & ((deltaY == -1) || ((source.y == 6) & (dest.y == source.y - 2))));//(deltaX == 0 && deltaY == -1)
    }

    @Override
//    public Cell[] way(Cell source, Cell dest) {
//        Cell[] steps;
//        if (this.impossibilityToMove(source, dest)) {
//            throw new ImpossibleMoveException(String.format("The movement is impossible for %s", this.getClass().getSimpleName(), source, dest));//или такое сообщение:("Impossible movement!");
//        }
//        steps = new Cell[]{dest};
//        return steps;
//    }

    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps;
        if (!isMovementPossible(source, dest)) {
            throw new ImpossibleMoveException(String.format("Move is impossible for %s", this.getClass().getSimpleName()));
        }
        steps = new Cell[]{dest};
        return steps;
    }

    private boolean impossibilityToMove(Cell source, Cell dest) {
        boolean rst = false;
        if (!(source.y == dest.y + 1 && source.x == dest.x)) {
            rst = true;
        }
        return rst;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}