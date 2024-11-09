package ru.kovalenko.chess;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }

    @Override
    String getSymbol() {
        return "K";
    }

    boolean isUnderAttack(ChessBoard board, int line, int column) {
        return false;
    }
}
