package ru.kovalenko.chess;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }
        if (line - 1 == toLine || line + 1 == toLine) {
            return column - 2 == toColumn || column + 2 == toColumn;
        }
        if (line - 2 == toLine || line + 2 == toLine) {
            return column - 1 == toColumn || column + 1 == toColumn;
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "H";
    }
}
