package chess;

import java.util.Collection;
import java.util.ArrayList;

public class PawnValidMoves {

    Collection<ChessMove> validMoves;

    public PawnValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();
        ValidMoves testClass = new ValidMoves();
        ChessGame.TeamColor pieceColor = board.getPiece(myPosition).getTeamColor();
        boolean valid = true;

        //If White
        if(pieceColor == ChessGame.TeamColor.WHITE) {
            //Move 1
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
            if(testClass.inBoard(testPos))
                valid = pawnTest(board, myPosition, testPos);
            //If I can move 1, and at starting position test move 2
            if((myPosition.getRow() == 2) && valid) {
                testPos = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn());
                pawnTest(board, myPosition, testPos);
            }
            //Capture Left
            testPos = new ChessPosition( myPosition.getRow() + 1, myPosition.getColumn() - 1);
            if(testClass.inBoard(testPos))
                pawnCaptureTest(board, myPosition, testPos);
            //Capture Right
            testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
            if(testClass.inBoard(testPos))
                pawnCaptureTest(board, myPosition, testPos);
        }
        //Else If Black
        else if (pieceColor == ChessGame.TeamColor.BLACK) {
            //Move 1
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
            if(testClass.inBoard(testPos))
                valid = pawnTest(board, myPosition, testPos);
            //If I can move 1, and at starting position test move 2
            if((myPosition.getRow() == 7) && valid) {
                testPos = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn());
                pawnTest(board, myPosition, testPos);
            }
            //Capture Right
            testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
            if(testClass.inBoard(testPos))
                pawnCaptureTest(board, myPosition, testPos);
            //Capture Left
            testPos = new ChessPosition( myPosition.getRow() - 1, myPosition.getColumn() + 1);
            if(testClass.inBoard(testPos))
                pawnCaptureTest(board, myPosition, testPos);
        } else {
            validMoves = null;
        }
    }

    public Collection<ChessMove> getPawnMoves() {
        return validMoves;
    }

    public boolean pawnTest(ChessBoard board, ChessPosition startPos, ChessPosition testPos) {
        if((board.getPiece(testPos) == null)) {
            addNewMove(startPos, testPos);
            return true;
        } else
            return false;
    }

    public void pawnCaptureTest(ChessBoard board, ChessPosition startPos, ChessPosition testPos) {
        if((board.getPiece(testPos) != null) && (board.getPiece(testPos).getTeamColor() != board.getPiece(startPos).getTeamColor()))
            addNewMove(startPos, testPos);
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