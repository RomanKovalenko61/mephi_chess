package ru.kovalenko.chess;

import java.util.Objects;

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
        int quadrant = 0;
        if (line - toLine < 0 && column - toColumn < 0) {
            quadrant = 1;
        } else if (line - toLine < 0 && column - toColumn > 0) {
            quadrant = 2;
        } else if (line - toLine > 0 && column - toColumn > 0) {
            quadrant = 3;
        } else if (line - toLine > 0 && column - toColumn < 0) {
            quadrant = 4;
        }

        switch (quadrant) {
            case 1:
                for (int x1 = line + 1, x2 = toLine, y1 = column + 1, y2 = toColumn; x1 <= x2 && y1 <= y2; x1++, y1++) {
                    if (!canDoNextStep(chessBoard, x1, x2, y1, y2))
                        return false;
                }
                break;
            case 2:
                for (int x1 = line + 1, x2 = toLine, y1 = column - 1, y2 = toColumn; x1 <= x2 && y1 >= y2; x1++, y1--) {
                    if (!canDoNextStep(chessBoard, x1, x2, y1, y2))
                        return false;
                }
                break;
            case 3:
                for (int x1 = line - 1, x2 = toLine, y1 = column - 1, y2 = toColumn; x1 >= x2 && y1 >= y2; x1--, y1--) {
                    if (!canDoNextStep(chessBoard, x1, x2, y1, y2))
                        return false;
                }
                break;
            case 4:
                for (int x1 = line - 1, x2 = toLine, y1 = column + 1, y2 = toColumn; x1 >= x2 && y1 <= y2; x1--, y1++) {
                    if (!canDoNextStep(chessBoard, x1, x2, y1, y2))
                        return false;
                }
                break;
        }
        return true;
    }

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
        return "B";
    }
}
