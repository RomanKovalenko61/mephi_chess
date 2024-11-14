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
        int diffLine = Math.abs(line - toLine);
        int diffColumn = Math.abs(column - toColumn);
        if (diffLine > 2 || diffColumn > 1 || diffLine == 0 && diffColumn == 0) {
            return false;
        }

        if (chessBoard.board[line][column].getColor().equals("White")) {
            ChessPiece forward = chessBoard.board[line + 1][column];
            if (line == 1 && toLine == 3 && column == toColumn) {
                ChessPiece forward2 = chessBoard.board[line + 2][column];
                return forward == null && forward2 == null;
            } else if (diffLine == 1 && column == toColumn) {
                return forward == null;
            } else if (diffLine == 1 && diffColumn == 1) {
                ChessPiece attacked = chessBoard.board[line + 1][toColumn];
                return attacked != null && attacked.getColor().equals("Black");
            } else {
                return false;
            }
        }

        if (chessBoard.board[line][column].getColor().equals("Black")) {
            ChessPiece forward = chessBoard.board[line - 1][column];
            if (line == 6 && toLine == 4 && column == toColumn) {
                ChessPiece forward2 = chessBoard.board[line - 2][column];
                return forward == null && forward2 == null;
            } else if (diffLine == 1 && column == toColumn) {
                return forward == null;
            } else if (diffLine == 1 && diffColumn == 1) {
                ChessPiece attacked = chessBoard.board[line - 1][toColumn];
                return attacked != null && attacked.getColor().equals("White");
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }
}
