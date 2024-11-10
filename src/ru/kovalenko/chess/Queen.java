package ru.kovalenko.chess;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int diffLine = Math.abs(line - toLine);
        int diffColumn = Math.abs(column - toColumn);
        if (diffLine == diffColumn && diffLine != 0) {
            return true;
        }
        return diffLine == 0 && diffColumn > 0 || diffLine > 0 && diffColumn == 0;
    }

    @Override
    String getSymbol() {
        return "Q";
    }
}
