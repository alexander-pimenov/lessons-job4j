package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class Logic {
    /**
     * Поле массива всех фигур
     */
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    /**
     * Метод добавляет фигуру в массив
     *
     * @param figure типа Figure
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Метод проверяет занятые клетки и перемещает фигуру
     *
     * @param source типа Cell
     * @param dest   типа Cell
     * @return результат перемещения типа boolean
     */

    public boolean move(Cell source, Cell dest) throws OccupiedWayException, FigureNotFoundException, ImpossibleMoveException {
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Фигура не найдена!");
        }
        Cell[] steps = this.figures[index].way(source, dest);
        for (Cell step : steps) {
            if (this.findBy(step) != -1) {
                throw new OccupiedWayException("Путь заблокирован!");
            }
        }
        boolean rst = false;
        if (steps.length > 0) {
            this.figures[index] = this.figures[index].copy(dest);
            rst = true;
        }
        return rst;
    }


    /**
     * Метод расставляет фигуры в начало
     */
    public void clean() {
        for (int position = 0; position < this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Метод номер позиции фигуры
     *
     * @param cell типа Cell
     * @return типа int
     */
//    private int findBy(Cell cell) {
    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index < this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
