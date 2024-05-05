package chess;

import java.util.Collection;

public class BishopValidMoves {

    Collection<ChessMove> possibleMoves;

    public BishopValidMoves(ChessBoard board, ChessPosition myPosition) {
        //Up and to the right
        boolean valid = true;
        int i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
            if (((myPosition.getRow() + i) > 7) || ((myPosition.getColumn() + i) > 7)) {
                valid = false;
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
