package chess;

import java.util.Collection;
import java.util.ArrayList;

public class BishopValidMoves {

    Collection<ChessMove> possibleMoves;

    public BishopValidMoves(ChessBoard board, ChessPosition myPosition) {
        possibleMoves =  new ArrayList<ChessMove>();

        //Up and to the right
        boolean valid = true;
        int i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
            if (((myPosition.getRow() + i) > 7) || ((myPosition.getColumn() + i) > 7)) {
                valid = false;
            } else {
                valid = testMove(board, myPosition, testPos);
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
                valid = testMove(board, myPosition, testPos);
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
                valid = testMove(board, myPosition, testPos);
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
                valid = testMove(board, myPosition, testPos);
            }
            i++;
        }
    }

    public boolean testMove(ChessBoard board, ChessPosition myPosition, ChessPosition testPosition) {
        if(board.getPiece(testPosition) == null) {
            addNewMove(myPosition, testPosition);
            return true;
        } else if (board.getPiece(testPosition).getPieceType() == board.getPiece(myPosition).getPieceType()) {
            return false;
        } else {
            addNewMove(myPosition, testPosition);
            return false;
        }
    }

    public void addNewMove(ChessPosition curPos, ChessPosition newPos) {
        ChessPosition correctPos = new ChessPosition(newPos.getRow() + 1, newPos.getColumn() + 1);
        ChessMove newMove = new ChessMove(curPos, correctPos, null);
        possibleMoves.add(newMove);
    }

    public Collection<ChessMove> getBishopMoves() {
        return possibleMoves;
    }
}
