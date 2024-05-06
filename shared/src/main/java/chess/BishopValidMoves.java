package chess;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

public class BishopValidMoves {

    Collection<ChessMove> validMoves;

    public BishopValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();

        //Up and to the right
        boolean valid = true;
        int i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
            if (((myPosition.getRow() + i) > 8) || ((myPosition.getColumn() + i) > 8)) {
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
            if (((myPosition.getRow() - i) < 1) || ((myPosition.getColumn() + i) > 8)) {
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
            if (((myPosition.getRow() - i) < 1) || ((myPosition.getColumn() - i) < 1)) {
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
            if (((myPosition.getRow() + i) > 8) || ((myPosition.getColumn() - i) < 1)) {
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
    public boolean testMove(ChessBoard board, ChessPosition startPos, ChessPosition testPosition) {
        if(board.getPiece(testPosition) == null) {
            addNewMove(startPos, testPosition);
            return true;
        } else if (board.getPiece(testPosition).getPieceType() == board.getPiece(startPos).getPieceType()) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BishopValidMoves that = (BishopValidMoves) o;
        return Objects.equals(validMoves, that.validMoves);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(validMoves);
    }

    @Override
    public String toString() {
        String output = "BishopValidMoves{" +
                "validMoves= ";
        for (ChessMove move : validMoves) {
            output += "{ " + move.getStartPosition() + "," + move.getEndPosition() + "} ";
        }
        return output;
    }
}
