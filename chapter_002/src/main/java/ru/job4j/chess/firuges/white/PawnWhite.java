package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite implements Figure {
    private final Cell position;

    public PawnWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    private boolean impossibilityToMove(Cell source, Cell dest) {
        boolean rst = false;
        if (!(source.y == dest.y - 1 && source.x == dest.x)) {
            rst = true;
        }
        return rst;
    }
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps;
        if (this.impossibilityToMove(source, dest)) {
            throw new ImpossibleMoveException(String.format("The movement is impossible for %s", this.getClass().getSimpleName(), source, dest));//или такое сообщение:("Impossible movement!");
        }
        steps = new Cell[]{dest};
        return steps;
    }



//    @Override
//    public Cell[] way(Cell source, Cell dest) {
//        return new Cell[] {
//                dest
//        };
//    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}