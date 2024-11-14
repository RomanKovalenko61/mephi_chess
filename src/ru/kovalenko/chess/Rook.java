package ru.kovalenko.chess;

public class Rook extends ChessPiece {
    public Rook(String color) {
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
        if (diffColumn == 0 && diffLine == 0 || diffLine > 0 && column != toColumn || diffColumn > 0 && line != toLine) {
            return false;
        }

        //Определяем направление движение фигуры
        Direction direction;
        if (line - toLine > 0) {
            direction = Direction.DOWN;
        } else if (line - toLine < 0) {
            direction = Direction.UP;
        } else if (column - toColumn > 0) {
            direction = Direction.LEFT;
        } else {
            direction = Direction.RIGHT;
        }

        return ChessUtils.canMoveToPos(chessBoard, this, direction, line, toLine, column, toColumn);
    }
    @Override
    String getSymbol() {
        return "R";
    }
}
