package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * @author Igor Shirokov (freelancerigor@yandex.ru)
 * @version $Id$
 * @since 17.10.2018
 */
public class QeenBlack implements Figure {
    /**
     * Поле номер позиции фигуры
     */
    private final Cell position;

    /**
     * Конструктор
     *
     * @param position типа Cell
     */
    public QeenBlack(final Cell position) {
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
        Cell[] steps;
        int deltaX, deltaY, cells;
        int absDeltaX = abs(dest.x - source.x);
        int absDeltaY = abs(dest.y - source.y);
        if (!((absDeltaX == absDeltaY) || (absDeltaX == 0 | absDeltaY == 0))) {
            //throw new ImpossibleMoveException("Ферзь не может так ходить!");
            throw new ImpossibleMoveException(String.format("%s (королева) не может так ходить!", this.getClass().getSimpleName()));
        }
        cells = max(absDeltaX, absDeltaY);
        steps = new Cell[cells];
        deltaY = dest.y - source.y;
        deltaY = (deltaY > 0) ? 1 : ((deltaY < 0) ? -1 : deltaY);
        deltaX = dest.x - source.x;
        deltaX = (deltaX > 0) ? 1 : ((deltaX < 0) ? -1 : deltaX);

        for (int i = 0; i < cells; i++) {
            steps[i] = Cell.values()[8 * (source.x + deltaX * (i + 1)) + (source.y + deltaY * (i + 1))];
        }
        return steps;
//        return Arrays.copyOf(steps, cells);
    }

    /**
     * Метод устанавливает новую позицию фигуры
     *
     * @param dest типа Cell
     * @return типа Cell
     */
    @Override
    public Figure copy(Cell dest) {
        return new QeenBlack(dest);
    }
}
