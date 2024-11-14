package ru.kovalenko.chess;

import java.util.Objects;

public class ChessUtils {
    public static boolean canMoveToPos(ChessBoard chessBoard, ChessPiece current, Direction direction,
                                       int line, int toLine, int column, int toColumn) {
        switch (direction) {
            case UP:
                for (int x = line + 1; x <= toLine; x++) {
                    if (!canDoNextStep(chessBoard, current, x, toLine, column, toColumn))
                        return false;
                }
                break;
            case DOWN:
                for (int x = line - 1; x >= toLine; x--) {
                    if (!canDoNextStep(chessBoard, current, x, toLine, column, toColumn))
                        return false;
                }
                break;
            case LEFT:
                for (int y = column - 1; y >= toColumn; y--) {
                    if (!canDoNextStep(chessBoard, current, line, toLine, y, toColumn))
                        return false;
                }
                break;
            case RIGHT:
                for (int y = column + 1; y <= toColumn; y++) {
                    if (!canDoNextStep(chessBoard, current, line, toLine, y, toColumn))
                        return false;
                }
                break;
            case QUAD1:
                for (int x1 = line + 1, x2 = toLine, y1 = column + 1, y2 = toColumn; x1 <= x2 && y1 <= y2; x1++, y1++) {
                    if (!canDoNextStep(chessBoard, current, x1, x2, y1, y2))
                        return false;
                }
                break;
            case QUAD2:
                for (int x1 = line + 1, x2 = toLine, y1 = column - 1, y2 = toColumn; x1 <= x2 && y1 >= y2; x1++, y1--) {
                    if (!canDoNextStep(chessBoard, current, x1, x2, y1, y2))
                        return false;
                }
                break;
            case QUAD3:
                for (int x1 = line - 1, x2 = toLine, y1 = column - 1, y2 = toColumn; x1 >= x2 && y1 >= y2; x1--, y1--) {
                    if (!canDoNextStep(chessBoard, current, x1, x2, y1, y2))
                        return false;
                }
                break;
            case QUAD4:
                for (int x1 = line - 1, x2 = toLine, y1 = column + 1, y2 = toColumn; x1 >= x2 && y1 <= y2; x1--, y1++) {
                    if (!canDoNextStep(chessBoard, current, x1, x2, y1, y2))
                        return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private static boolean canDoNextStep(ChessBoard chessBoard, ChessPiece current, int x1, int x2, int y1, int y2) {
        ChessPiece forward = chessBoard.board[x1][y1];
        // Проверка мы в конце пути?
        if (x1 == x2 && y1 == y2) {
            return forward == null || !Objects.equals(current.getColor(), forward.getColor());
        }
        return forward == null;
    }
}
