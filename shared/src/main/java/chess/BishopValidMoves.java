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
            } else if(board.getPiece(testPos) == null) {
                addNewMove(myPosition, testPos);
            } else if (board.getPiece(testPos).getPieceType() == board.getPiece(myPosition).getPieceType()) {
                valid = false;
            } else {
                addNewMove(myPosition, testPos);
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
            } else if(board.getPiece(testPos) == null) {
                addNewMove(myPosition, testPos);
            } else if (board.getPiece(testPos).getPieceType() == board.getPiece(myPosition).getPieceType()) {
                valid = false;
            } else {
                addNewMove(myPosition, testPos);
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
            } else if(board.getPiece(testPos) == null) {
                addNewMove(myPosition, testPos);
            } else if (board.getPiece(testPos).getPieceType() == board.getPiece(myPosition).getPieceType()) {
                valid = false;
            } else {
                addNewMove(myPosition, testPos);
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
            } else if(board.getPiece(testPos) == null) {
                addNewMove(myPosition, testPos);
            } else if (board.getPiece(testPos).getPieceType() == board.getPiece(myPosition).getPieceType()) {
                valid = false;
            } else {
                addNewMove(myPosition, testPos);
            }
            i++;
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
