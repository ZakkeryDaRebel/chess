package chess;

import java.util.Collection;

public class ValidMoves {

    private Collection<ChessMove>  validMoves;

    public ValidMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
            BishopValidMoves bBM = new BishopValidMoves(board, myPosition, validMoves);
            validMoves = bBM.getBishopMoves();
        } else {

        }
    }

    public Collection<ChessMove> getValidMoves() {
        return validMoves;
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
        validMoves.add(newMove);
    }
}
