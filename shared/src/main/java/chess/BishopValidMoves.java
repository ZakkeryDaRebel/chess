package chess;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

public class BishopValidMoves {

    Collection<ChessMove> validMoves;

    public BishopValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();
        ValidMoves testClass = new ValidMoves();

        //Up and to the right
        boolean valid = true;
        int i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
            valid = testClass.loopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Down and to the right
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i);
            valid = testClass.loopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Down and to the left
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i);
            valid = testClass.loopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Up and to the left
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i);
            valid = testClass.loopTest(validMoves, board, myPosition, testPos);
            i++;
        }
    }

    public Collection<ChessMove> getBishopMoves() {
        return validMoves;
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
}
