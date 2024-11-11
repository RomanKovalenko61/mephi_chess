package ru.kovalenko.chess;

import java.util.Objects;

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

        switch (direction) {
            case UP:
                for (int x = line + 1; x <= toLine; x++) {
                    if (!canDoNextStep(chessBoard, x, toLine, column, toColumn))
                        return false;
                }
                break;
            case DOWN:
                for (int x = line - 1; x >= toLine; x--) {
                    if (!canDoNextStep(chessBoard, x, toLine, column, toColumn))
                        return false;
                }
                break;
            case LEFT:
                for (int y = column - 1; y >= toColumn; y--) {
                    if (!canDoNextStep(chessBoard, line, toLine, y, toColumn))
                        return false;
                }
                break;
            case RIGHT:
                for (int y = column + 1; y <= toColumn; y++) {
                    if (!canDoNextStep(chessBoard, line, toLine, y, toColumn))
                        return false;
                }
                break;
            default:
                return false;
        }

        return true;
    }

    // TODO: можно вынести в утильные методы?
    // Рассчитываем есть ли на пути препятствия или враг в конце пути
    private boolean canDoNextStep(ChessBoard chessBoard, int x1, int x2, int y1, int y2) {
        ChessPiece forward = chessBoard.board[x1][y1];
        // Проверка мы в конце пути?
        if (x1 == x2 && y1 == y2) {
            return forward == null || !Objects.equals(this.getColor(), forward.getColor());
        }
        return forward == null;
    }

    @Override
    String getSymbol() {
        return "R";
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
