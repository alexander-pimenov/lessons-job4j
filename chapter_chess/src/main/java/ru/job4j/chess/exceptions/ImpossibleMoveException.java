package ru.job4j.chess.exceptions;

public class ImpossibleMoveException extends RuntimeException {
    public ImpossibleMoveException(String massage) {
        super(massage);
    }
}
