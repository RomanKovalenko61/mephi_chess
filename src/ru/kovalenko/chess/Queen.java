package ru.kovalenko.chess;

public class Queen extends ChessPiece {
    public Queen(String color) {
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
        if (diffLine == 0 && diffColumn == 0) {
            return false;
        }
        // Если перемещение возможно надо определить как именно ходим ()
        Direction direction = null;
        if (line == toLine || column == toColumn || diffLine == diffColumn) {
            if (column == toColumn && toLine > line) {
                direction = Direction.UP;
            } else if (column == toColumn && toLine < line) {
                direction = Direction.DOWN;
            } else if (line == toLine && toColumn > column) {
                direction = Direction.RIGHT;
            } else if (line == toLine && toColumn < column) {
                direction = Direction.LEFT;
            } else if (toLine > line && toColumn > column) {
                direction = Direction.QUAD1;
            } else if (toLine > line) {
                direction = Direction.QUAD2;
            } else if (toLine < line && toColumn < column) {
                direction = Direction.QUAD3;
            } else if (toLine < line) {
                direction = Direction.QUAD4;
            }
        } else {
            return false;
        }
        // Определяем есть препятствие на пути или атакуем цель
        return ChessUtils.canMoveToPos(chessBoard, this, direction, line, toLine, column, toColumn);
    }


    @Override
    String getSymbol() {
        return "Q";
    }
}
