package ru.kovalenko.chess;

import java.util.Objects;

public class King extends ChessPiece {
    public King(String color) {
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
        if (diffLine == 0 && diffColumn == 0 || diffLine > 1 || diffColumn > 1
                || isUnderAttack(chessBoard, toLine, toColumn)) {
            return false;
        }
        ChessPiece attacked = chessBoard.board[toLine][toColumn];
        return attacked == null || !Objects.equals(this.getColor(), attacked.getColor());
    }

    @Override
    String getSymbol() {
        return "K";
    }

    boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        ChessPiece enemy;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                enemy = chessBoard.board[i][j];
                if (enemy != null && !Objects.equals(this.getColor(), enemy.getColor()) && !(enemy instanceof King)) {
                    boolean canBeAttack = enemy.canMoveToPosition(chessBoard, i, j, line, column);
                    if (canBeAttack) {
                        System.out.printf("Король %s line: %d column :%d под атакой фигуры с позиции line: %d column :%d ",
                                this.getColor(), line, column, i, j);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
