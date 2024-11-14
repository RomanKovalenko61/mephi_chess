package ru.kovalenko.chess;

import java.util.Objects;

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
            } else if (toLine > line && toColumn < column) {
                direction = Direction.QUAD2;
            } else if (toLine < line && toColumn < column) {
                direction = Direction.QUAD3;
            } else if (toLine < line && toColumn > column) {
                direction = Direction.QUAD4;
            }
        } else {
            return false;
        }
        // Определяем есть препятствие на пути или атакуем цель
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
            case QUAD1:
                for (int x1 = line + 1, x2 = toLine, y1 = column + 1, y2 = toColumn; x1 <= x2 && y1 <= y2; x1++, y1++) {
                    if (!canDoNextStep(chessBoard, x1, x2, y1, y2))
                        return false;
                }
                break;
            case QUAD2:
                for (int x1 = line + 1, x2 = toLine, y1 = column - 1, y2 = toColumn; x1 <= x2 && y1 >= y2; x1++, y1--) {
                    if (!canDoNextStep(chessBoard, x1, x2, y1, y2))
                        return false;
                }
                break;
            case QUAD3:
                for (int x1 = line - 1, x2 = toLine, y1 = column - 1, y2 = toColumn; x1 >= x2 && y1 >= y2; x1--, y1--) {
                    if (!canDoNextStep(chessBoard, x1, x2, y1, y2))
                        return false;
                }
                break;
            case QUAD4:
                for (int x1 = line - 1, x2 = toLine, y1 = column + 1, y2 = toColumn; x1 >= x2 && y1 <= y2; x1--, y1++) {
                    if (!canDoNextStep(chessBoard, x1, x2, y1, y2))
                        return false;
                }
                break;
            default:
                return false;
        }

        return true;
    }

    private boolean canDoNextStep(ChessBoard chessBoard, int x1, int x2, int y1, int y2) {
        ChessPiece forward = chessBoard.board[x1][y1];
        // Проверка мы в конце пути?
        if (x1 == x2 && y1 == y2) {
            return forward == null || !Objects.equals(this.getColor(), forward.getColor());
        }
        return forward == null;
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT, QUAD1, QUAD2, QUAD3, QUAD4
    }

    @Override
    String getSymbol() {
        return "Q";
    }
}
