package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static java.lang.Math.abs;

/**
 * @author Igor Shirokov (freelancerigor@yandex.ru)
 * @version $Id$
 * @since 17.10.2018
 */

public class BishopBlack implements Figure {
    /**
     * Поле номер позиции фигуры
     */
    private final Cell position;

    /**
     * Конструктор
     *
     * @param position типа Cell
     */
    public BishopBlack(final Cell position) {
        this.position = position;
    }

    /**
     * Метод возвращает позицию фигуры
     *
     * @return Cell
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод возвращает массив позиций передвижения фигуры
     *
     * @param source типа Cell
     * @param dest   типа Cell
     * @return типа Cell[]
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps = new Cell[abs(source.y - dest.y)];
        int deltaX, deltaY;
        int absDeltaX = abs(dest.x - source.x);
        if (absDeltaX != abs(dest.y - source.y)) {
//            throw new ImpossibleMoveException("Слон так не ходит");
            throw new ImpossibleMoveException(String.format("%s (слон) не может так ходить!", this.getClass().getSimpleName()));

        }
        deltaY = (dest.y - source.y > 0) ? 1 : -1;
        deltaX = (dest.x - source.x > 0) ? 1 : -1;
        for (int i = 0; i < absDeltaX; i++) {
            steps[i] = Cell.values()[8 * (source.x + deltaX * (i + 1)) + (source.y + deltaY * (i + 1))];
        }
        return steps;
//        return Arrays.copyOf(steps, absDeltaX);
    }

    /**
     * Метод устанавливает новую позицию фигуры
     *
     * @param dest типа Cell
     * @return типа Cell
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
