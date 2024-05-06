package chess;

import java.util.Collection;
import java.util.ArrayList;

public class BishopValidMoves {

    Collection<ChessMove> possibleMoves;

    public BishopValidMoves(ChessBoard board, ChessPosition myPosition, ValidMoves validMoves) {

        //Up and to the right
        boolean valid = true;
        int i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
            if (((myPosition.getRow() + i) > 7) || ((myPosition.getColumn() + i) > 7)) {
                valid = false;
            } else {
                valid = validMoves.testMove(board, myPosition, testPos);
            }
            i++;
        }

        //Up and to the left
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i);
            if (((myPosition.getRow() + i) > 7) || ((myPosition.getColumn() - i) < 0)) {
                valid = false;
            } else {
                valid = validMoves.testMove(board, myPosition, testPos);
            }
            i++;
        }

        //Down and to the right
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i);
            if (((myPosition.getRow() - i) < 0) || ((myPosition.getColumn() + i) > 7)) {
                valid = false;
            } else {
                valid = validMoves.testMove(board, myPosition, testPos);
            }
            i++;
        }

        //Down and to the left
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i);
            if (((myPosition.getRow() - i) < 0) || ((myPosition.getColumn() - i) < 0)) {
                valid = false;
            } else {
                valid = validMoves.testMove(board, myPosition, testPos);
            }
            i++;
        }
    }

    public Collection<ChessMove> getBishopMoves() {
        return possibleMoves;
    }
}
