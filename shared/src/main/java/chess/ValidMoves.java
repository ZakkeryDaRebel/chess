package chess;

import java.util.Collection;
import java.util.ArrayList;

public class ValidMoves {

    private Collection<ChessMove>  validMoves;

    public ValidMoves() {

    }

    /*public Collection<ChessMove> getValidMoves() {
        return validMoves;
    }*/

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
}
