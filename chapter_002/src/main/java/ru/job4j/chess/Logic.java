package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws FigureNotFoundException, OccupiedWayException, ImpossibleMoveException {
        boolean rst = false;
        try {
            int index = this.findBy(source);
            Cell[] steps = this.figures[index].way(source, dest);
            this.exceptions(source, steps, dest);
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        } catch (FigureNotFoundException fnfe) {
            System.out.println(fnfe);
        } catch (OccupiedWayException owe) {
            System.out.println(owe);
        } catch (ImpossibleMoveException ime) {
            System.out.println(ime);
        }
        return rst;
    }

    private void exceptions(Cell sours, Cell[] steps, Cell dest) {
        if (this.findBy(sours) == -1) {
            throw new FigureNotFoundException("A figure are not found!");
        }
        for (int i = 0; i < steps.length; i++) {
            if (this.findBy(steps[i]) != -1) {
                throw new OccupiedWayException("The way is occupied!");
            }
        }
        for (int i = 0; i < this.figures.length; i++) {
            if (figures[i].position().equals(dest)) {
                throw new ImpossibleMoveException("Impossible movement!");
            }
        }
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
