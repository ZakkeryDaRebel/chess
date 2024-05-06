package chess;

import java.util.Collection;
import java.util.ArrayList;

public class BishopValidMoves {

    Collection<ChessMove> validMoves;

    public BishopValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();

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

    public Collection<ChessMove> getBishopMoves() {
        return validMoves;
    }


    //May delete later to put into a different more broad class, but for now it's here to test the Bishop moves
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
        validMoves.add(newMove);
    }
}
