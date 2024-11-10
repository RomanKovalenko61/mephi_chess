package ru.kovalenko.chess;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка пределов хода
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        // Белая пешка
        if (chessBoard.board[line][column].getColor().equals("White")) {
            // Рывок
            ChessPiece forward = chessBoard.board[line + 1][column];
            if (line == 1 && toLine == 3 && column == toColumn) {
                ChessPiece forward2 = chessBoard.board[line + 2][column];
                return forward == null && forward2 == null;
            }
            // Ход только на одну линию
            if (line + 1 != toLine) {
                return false;
            } else {
                // Простой ход вперед или атака
                if (column == toColumn) {
                    return forward == null;
                } else if (Math.abs(column - toColumn) == 1) {
                    ChessPiece attacked = chessBoard.board[toLine][toColumn];
                    return attacked.getColor().equals("Black");
                }
            }
            return false;
        }
        // Черная пешка
        if (chessBoard.board[line][column].getColor().equals("Black")) {
            // Рывок
            ChessPiece forward = chessBoard.board[line - 1][column];
            if (line == 6 && toLine == 4 && column == toColumn) {
                ChessPiece forward2 = chessBoard.board[line - 2][column];
                return forward == null && forward2 == null;
            }
            // Ход только на одну линию
            if (line - 1 != toLine) {
                return false;
            } else {
                // Простой ход вперед или атака
                if (column == toColumn) {
                    return forward == null;
                } else if (Math.abs(column - toColumn) == 1) {
                    ChessPiece attacked = chessBoard.board[toLine][toColumn];
                    return attacked.getColor().equals("White");
                }
            }
            return false;
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }
}
