package ru.kovalenko.chess;

import java.util.Objects;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка пределов хода
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        ChessPiece attacked;
        if (Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2 ||
                Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1) {
            attacked = chessBoard.board[toLine][toColumn];
            return attacked == null || !Objects.equals(attacked.getColor(), this.getColor());
        }

        return false;
    }

    @Override
    String getSymbol() {
        return "H";
    }
}
