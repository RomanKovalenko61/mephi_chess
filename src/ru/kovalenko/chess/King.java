package ru.kovalenko.chess;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int diffLine = Math.abs(line - toLine);
        int diffColumn = Math.abs(column - toColumn);
        return diffLine == 0 && diffColumn == 1 || diffLine == 1 && diffColumn == 0 || diffLine == 1 && diffColumn == 1;
    }

    @Override
    String getSymbol() {
        return "K";
    }

    boolean isUnderAttack(ChessBoard board, int line, int column) {
        return false;
    }
}
