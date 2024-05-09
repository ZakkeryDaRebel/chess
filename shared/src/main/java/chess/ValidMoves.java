package chess;

import java.util.Collection;

public class ValidMoves {

    public ValidMoves() {

    }

    public boolean LoopTest(Collection<ChessMove> validMoves, ChessBoard board, ChessPosition startPos, ChessPosition testPos) {
        if(inBoard(testPos))
            return testMove(validMoves, board, startPos, testPos);
        else
            return false;
    }

    public boolean testMove(Collection<ChessMove> validMoves, ChessBoard board, ChessPosition startPos, ChessPosition testPosition) {
        if(board.getPiece(testPosition) == null) {
            addNewMove( validMoves, startPos, testPosition);
            return true;
        } else if (board.getPiece(testPosition).getTeamColor() == board.getPiece(startPos).getTeamColor()) {
            return false;
        } else {
            addNewMove(validMoves, startPos, testPosition);
            return false;
        }
    }

    public void addNewMove(Collection<ChessMove> validMoves, ChessPosition curPos, ChessPosition newPos) {
        ChessMove newMove = new ChessMove(curPos, newPos, null);
        validMoves.add(newMove);
    }

    public boolean inBoard(ChessPosition testPos) {
        return (testPos.getRow() <= 8) && (testPos.getRow() >= 1) && (testPos.getColumn() >= 1) && (testPos.getColumn() <= 8);
    }
}
