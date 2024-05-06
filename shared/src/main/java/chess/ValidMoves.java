package chess;

import java.util.Collection;
import java.util.ArrayList;

public class ValidMoves {

    private Collection<ChessMove>  validMoves;

    public ValidMoves(ChessBoard board, ChessPosition myPosition) {
        validMoves = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);
        if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
            BishopValidMoves bBM = new BishopValidMoves(board, myPosition);
            validMoves = bBM.getBishopMoves();
        } else {

        }
    }

    public Collection<ChessMove> getValidMoves() {
        return validMoves;
    }

    public boolean testMove(ChessBoard board, ChessPosition startPos, ChessPosition testPosition) {
        if(board.getPiece(testPosition) == null) {
            addNewMove(startPos, testPosition);
            return true;
        } else if (board.getPiece(testPosition).getTeamColor() == board.getPiece(startPos).getTeamColor()) {
            return false;
        } else {
            addNewMove(startPos, testPosition);
            return false;
        }
    }

    public void addNewMove(ChessPosition curPos, ChessPosition newPos) {
        ChessMove newMove = new ChessMove(curPos, newPos, null);
        validMoves.add(newMove);
    }
}
