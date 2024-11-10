package ru.kovalenko.chess;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int diffLine = Math.abs(line - toLine);
        int diffColumn = Math.abs(column - toColumn);
        return diffLine == diffColumn && diffLine != 0;
    }

    @Override
    String getSymbol() {
        return "B";
    }
}
