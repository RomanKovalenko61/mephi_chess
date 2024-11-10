package ru.kovalenko.chess;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int diffLine = Math.abs(line - toLine);
        int diffColumn = Math.abs(column - toColumn);
        return diffLine > 0 && column == toColumn || diffColumn > 0 && line == toLine;
    }

    @Override
    String getSymbol() {
        return "R";
    }
}
