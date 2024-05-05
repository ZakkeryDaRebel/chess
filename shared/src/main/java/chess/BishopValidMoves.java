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
            } if(board.getPiece(testPos) == null) {
                ChessMove newMove = new ChessMove(myPosition, testPos, null);
                possibleMoves.add(newMove);
            } else if (board.getPiece(testPos).getPieceType() == board.getPiece(myPosition).getPieceType()) {
                valid = false;
            } else {
                ChessMove newMove = new ChessMove(myPosition, testPos, null);
                possibleMoves.add(newMove);
            }
            i++;
        }

        //Up and to the left
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i);
            if (((myPosition.getRow() + i) > 7) || ((myPosition.getColumn() - i) > 7)) {
                valid = false;
            } if(board.getPiece(testPos) == null) {
                ChessMove newMove = new ChessMove(myPosition, testPos, null);
                possibleMoves.add(newMove);
            } else if (board.getPiece(testPos).getPieceType() == board.getPiece(myPosition).getPieceType()) {
                valid = false;
            } else {
                ChessMove newMove = new ChessMove(myPosition, testPos, null);
                possibleMoves.add(newMove);
            }
            i++;
        }

        //Down and to the right
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i);
            if (((myPosition.getRow() - i) > 7) || ((myPosition.getColumn() + i) > 7)) {
                valid = false;
            } if(board.getPiece(testPos) == null) {
                ChessMove newMove = new ChessMove(myPosition, testPos, null);
                possibleMoves.add(newMove);
            } else if (board.getPiece(testPos).getPieceType() == board.getPiece(myPosition).getPieceType()) {
                valid = false;
            } else {
                ChessMove newMove = new ChessMove(myPosition, testPos, null);
                possibleMoves.add(newMove);
            }
            i++;
        }

        //Down and to the left
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i);
            if (((myPosition.getRow() - i) > 7) || ((myPosition.getColumn() - i) > 7)) {
                valid = false;
            } if(board.getPiece(testPos) == null) {
                ChessMove newMove = new ChessMove(myPosition, testPos, null);
                possibleMoves.add(newMove);
            } else if (board.getPiece(testPos).getPieceType() == board.getPiece(myPosition).getPieceType()) {
                valid = false;
            } else {
                ChessMove newMove = new ChessMove(myPosition, testPos, null);
                possibleMoves.add(newMove);
            }
            i++;
        }
    }

    public Collection<ChessMove> getBishopMoves() {
        return possibleMoves;
    }
}
