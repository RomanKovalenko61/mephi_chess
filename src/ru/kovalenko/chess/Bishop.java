package ru.kovalenko.chess;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка пределов хода
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }
        // Проверка, можем ли теоретически попасть в точку по координатам
        int diffLine = Math.abs(line - toLine);
        int diffColumn = Math.abs(column - toColumn);
        if (diffLine != diffColumn || diffLine == 0) {
            return false;
        }

        // Определение в какой квадрант идем относительно текущей точки
        Direction direction = null;
        if (toLine > line && toColumn > column) {
            direction = Direction.QUAD1;
        } else if (toLine > line && toColumn < column) {
            direction = Direction.QUAD2;
        } else if (toLine < line && toColumn < column) {
            direction = Direction.QUAD3;
        } else if (toLine < line && toColumn > column) {
            direction = Direction.QUAD4;
        }

        return ChessUtils.canMoveToPos(chessBoard, this, direction, line, toLine, column, toColumn);
    }

    @Override
    String getSymbol() {
        return "B";
    }
}
