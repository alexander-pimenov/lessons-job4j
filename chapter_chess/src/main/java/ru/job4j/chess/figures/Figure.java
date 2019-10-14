package ru.job4j.chess.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

public interface Figure {
    Cell position();

    Cell[] way(Cell source, Cell Dest) throws ImpossibleMoveException;

    boolean isMovePossible(Cell source, Cell dest);

    default String icon() {
        return String.format(
                "%.png", this.getClass().getSimpleName()
        );
    }

    Figure copy(Cell dest);
}
