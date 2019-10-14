package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.*;
import ru.job4j.chess.figures.white.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class LogicTest {
    private Logic logic;

    @Before
    public void mockOutput() {
        logic = new Logic();
        logic.add(new PawnWhite(Cell.B4));
        logic.add(new RookWhite(Cell.A1));
        logic.add(new KnightWhite(Cell.B1));
        logic.add(new BishopWhite(Cell.C1));
        logic.add(new QueenWhite(Cell.D1));
        logic.add(new KingWhite(Cell.E1));

        logic.add(new PawnBlack(Cell.G5));
        logic.add(new RookBlack(Cell.A8));
        logic.add(new KnightBlack(Cell.B8));
        logic.add(new BishopBlack(Cell.C8));
        logic.add(new QueenBlack(Cell.D8));
        logic.add(new KingBlack(Cell.E8));
    }

    @Test
    public void moveFigureNotFoundException() {
        assertThrows(FigureNotFoundException.class, () -> logic.move(Cell.C3, Cell.D3));
    }

    @Test
    public void moveOccupiedWayException() {
        assertThrows(OccupiedWayException.class, () -> logic.move(Cell.D1, Cell.C1));
    }

    @Test
    public void movePawnWhite() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: 1-cell forward move
        assertTrue(logic.move(Cell.B4, Cell.B5));
        // Not allowed: backward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.B5, Cell.B4));
        // Not allowed: side move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.B5, Cell.C5));
        // Not allowed: diagonal move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.B5, Cell.C6));
    }

    @Test
    public void movePawnBlack() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: 1-cell forward move
        assertTrue(logic.move(Cell.G5, Cell.G4));
        // Not allowed: backward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.G4, Cell.G5));
        // Not allowed: side move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.G4, Cell.F4));
        // Not allowed: diagonal move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.G4, Cell.F3));
    }

    @Test
    public void moveBishopWhite() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: diagonal move
        assertTrue(logic.move(Cell.C1, Cell.A3));
        // Not allowed: forward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.A3, Cell.A4));
        // Not allowed: side move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.A3, Cell.B3));
        // Not allowed: backward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.A3, Cell.A2));
    }

    @Test
    public void moveBishopBlack() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: diagonal move
        assertTrue(logic.move(Cell.C8, Cell.E6));
        // Not allowed: forward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E6, Cell.E5));
        // Not allowed: side move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E6, Cell.F6));
        // Not allowed: backward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E6, Cell.E7));
    }

    @Test
    public void moveKingWhite() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: 1-cell forward move
        assertTrue(logic.move(Cell.E1, Cell.E2));
        // Allowed: 1-cell diagonal move
        assertTrue(logic.move(Cell.E2, Cell.F3));
        // Not allowed: > 1-cell backward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.F3, Cell.F1));
        // Allowed: 1-cell backward move
        assertTrue(logic.move(Cell.F3, Cell.F2));
        // Allowed: 1-cell side move
        assertTrue(logic.move(Cell.F2, Cell.E2));
        // Not allowed: > 1-cell forward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E2, Cell.E4));
        // Not allowed: > 1-cell diagonal move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E2, Cell.G4));
        // Not allowed: > 1-cell side move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E2, Cell.G2));
    }

    @Test
    public void moveKingBlack() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: 1-cell forward move
        assertTrue(logic.move(Cell.E8, Cell.E7));
        // Allowed: 1-cell diagonal move
        assertTrue(logic.move(Cell.E7, Cell.F6));
        // Not allowed: > 1-cell backward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.F6, Cell.F8));
        // Allowed: 1-cell backward move
        assertTrue(logic.move(Cell.F6, Cell.F7));
        // Allowed: 1-cell side move
        assertTrue(logic.move(Cell.F7, Cell.E7));
        // Not allowed: > 1-cell forward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E7, Cell.E5));
        // Not allowed: > 1-cell diagonal move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E7, Cell.C5));
        // Not allowed: > 1-cell side move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E7, Cell.G7));
    }

    @Test
    public void moveKnightWhite() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: 2 forward + 1 to the side
        assertTrue(logic.move(Cell.B1, Cell.C3));
        // Allowed: 2 to the side + 1 forward
        assertTrue(logic.move(Cell.C3, Cell.E4));
        // Not allowed: forward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E4, Cell.E5));
        // Not allowed: side move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E4, Cell.D4));
        // Not allowed: backward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E4, Cell.E3));
        // Not allowed: diagonal move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E4, Cell.F5));
    }

    @Test
    public void moveKnightBlack() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: 2 forward + 1 to the side
        assertTrue(logic.move(Cell.B8, Cell.C6));
        // Allowed: 2 to the side + 1 forward
        assertTrue(logic.move(Cell.C6, Cell.E5));
        // Not allowed: forward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E5, Cell.E4));
        // Not allowed: side move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E5, Cell.D5));
        // Not allowed: backward move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E5, Cell.E6));
        // Not allowed: diagonal move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.E5, Cell.F4));
    }

    @Test
    public void moveQueenWhite() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: any number of squares forward.
        assertTrue(logic.move(Cell.D1, Cell.D7));
        // Allowed: any number of squares backward.
        assertTrue(logic.move(Cell.D7, Cell.D2));
        // Allowed: any number of squares to the side.
        assertTrue(logic.move(Cell.D2, Cell.H2));
        // Allowed: any number of squares diagonally.
        assertTrue(logic.move(Cell.H2, Cell.D6));
        // Not allowed: 2 to the side + 1 forward
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.D6, Cell.B7));
    }

    @Test
    public void moveQueenBlack() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: any number of squares forward.
        assertTrue(logic.move(Cell.D8, Cell.D2));
        // Allowed: any number of squares backward.
        assertTrue(logic.move(Cell.D2, Cell.D7));
        // Allowed: any number of squares to the side.
        assertTrue(logic.move(Cell.D7, Cell.H7));
        // Allowed: any number of squares diagonally.
        assertTrue(logic.move(Cell.H7, Cell.D3));
        // Not allowed: 2 to the side + 1 forward
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.D3, Cell.B2));
    }

    @Test
    public void moveRookWhite() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: any number of squares forward.
        assertTrue(logic.move(Cell.A1, Cell.A7));
        // Allowed: any number of squares backward.
        assertTrue(logic.move(Cell.A7, Cell.A2));
        // Allowed: any number of squares to the side.
        assertTrue(logic.move(Cell.A2, Cell.F2));
        // Not allowed: diagonal move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.F2, Cell.E3));
    }

    @Test
    public void moveRookBlack() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        // Allowed: any number of squares forward.
        assertTrue(logic.move(Cell.A8, Cell.A2));
        // Allowed: any number of squares backward.
        assertTrue(logic.move(Cell.A2, Cell.A7));
        // Allowed: any number of squares to the side.
        assertTrue(logic.move(Cell.A7, Cell.F7));
        // Not allowed: diagonal move
        assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.F7, Cell.E6));
    }

}
