package ru.job4j.chess.figures.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KnightWhite implements Figure {
    private final Cell position;

    public KnightWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Knight can move one square along any rank or file and then at an angle.
     * The knight´s movement can also be viewed as an “L” or “7″ laid out at any horizontal or vertical angle.
     *
     * @param source initial square
     * @param dest   desired square
     * @return true if move is possible, false otherwise
     */
    @Override
    public boolean isMovePossible(Cell source, Cell dest) {
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        return (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2) || (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isMovePossible(source, dest)) {
            throw new ImpossibleMoveException(String.format("Move is impossible for %s: %s -> %s", this.getClass().getSimpleName(), source, dest));
        }
        return new Cell[]{dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
