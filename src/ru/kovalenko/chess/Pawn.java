package ru.kovalenko.chess;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chessBoard.board[line][column].getColor().equals("White")) {
            return line == 1 && toLine == 3 && column == toColumn || line + 1 == toLine && column == toColumn;
        }
        if (chessBoard.board[line][column].getColor().equals("Black")) {
            return line == 6 && toLine == 4 && column == toColumn || line - 1 == toLine && column == toColumn;
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }
}
