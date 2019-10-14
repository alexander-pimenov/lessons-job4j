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
public class KingBlack implements Figure {
    private final Cell position;

    public KingBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    public boolean isMovementPossible(Cell source, Cell dest) {
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        return Math.abs(deltaX) <= 1 && Math.abs(deltaY) <= 1;
    }


    @Override
//    public Cell[] way(Cell source, Cell dest) {
//        return new Cell[] {
//                dest
//        };
//    }
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isMovementPossible(source, dest)){
            throw new ImpossibleMoveException(String.format("Move is impossible for %s", this.getClass().getSimpleName()));
        }
        return new Cell[] {
                dest
        };
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}