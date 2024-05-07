package chess;

import java.util.Collection;
import java.util.ArrayList;

public class PawnValidMoves {

    Collection<ChessMove> validMoves;

    public PawnValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();
        ChessGame.TeamColor pieceColor = board.getPiece(myPosition).getTeamColor();
        boolean valid = true;

        //If White
        if(pieceColor == ChessGame.TeamColor.WHITE) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
            valid = pawnTest(board, myPosition, testPos);
            if((myPosition.getRow() == 2) && valid) {
                testPos = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn());
                valid = pawnTest(board, myPosition, testPos);
            }
            testPos = new ChessPosition( myPosition.getRow() + 1, myPosition.getColumn() - 1);
            valid = pawnCaptureTest(board, myPosition, testPos);
            testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
            valid = pawnCaptureTest(board, myPosition, testPos);
        }
        //Else If Black
        else if (pieceColor == ChessGame.TeamColor.BLACK) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
            valid = pawnTest(board, myPosition, testPos);
            if((myPosition.getRow() == 7) && valid) {
                testPos = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn());
                valid = pawnTest(board, myPosition, testPos);
            }
            testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
            valid = pawnCaptureTest(board, myPosition, testPos);
            testPos = new ChessPosition( myPosition.getRow() - 1, myPosition.getColumn() + 1);
            valid = pawnCaptureTest(board, myPosition, testPos);
        } else {
            validMoves = null;
        }
    }

    public Collection<ChessMove> getPawnMoves() {
        return validMoves;
    }

    public boolean pawnTest(ChessBoard board, ChessPosition startPos, ChessPosition testPos) {
        if((testPos.getRow() <= 8) && (testPos.getRow() >= 1) && (testPos.getColumn() >= 1) && (testPos.getColumn() <= 8) && (board.getPiece(testPos) == null)) {
            addNewMove(startPos, testPos);
            return true;
        } else
            return false;
    }

    public boolean pawnCaptureTest(ChessBoard board, ChessPosition startPos, ChessPosition testPos) {
        if((testPos.getRow() <= 8) && (testPos.getRow() >= 1) && (testPos.getColumn() >= 1) && (testPos.getColumn() <= 8) && (board.getPiece(testPos) != null) && (board.getPiece(testPos).getTeamColor() != board.getPiece(startPos).getTeamColor())) {
            addNewMove(startPos, testPos);
            return true;
        } else
            return false;
    }

    public void addNewMove(ChessPosition curPos, ChessPosition newPos) {
        if((newPos.getRow() == 8) || (newPos.getRow() == 1)) {
            ChessMove newQueenMove = new ChessMove(curPos, newPos, ChessPiece.PieceType.QUEEN);
            validMoves.add(newQueenMove);
            ChessMove newRookMove = new ChessMove(curPos, newPos, ChessPiece.PieceType.ROOK);
            validMoves.add(newRookMove);
            ChessMove newBishopMove = new ChessMove(curPos, newPos, ChessPiece.PieceType.BISHOP);
            validMoves.add(newBishopMove);
            ChessMove newKnightMove = new ChessMove(curPos, newPos, ChessPiece.PieceType.KNIGHT);
            validMoves.add(newKnightMove);
        } else {
            ChessMove newMove = new ChessMove(curPos, newPos, null);
            validMoves.add(newMove);
        }

    }
}