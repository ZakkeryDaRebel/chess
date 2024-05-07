package chess;

import java.util.Collection;
import java.util.ArrayList;

public class KnightValidMoves {

    Collection<ChessMove> validMoves;

    public KnightValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();
        ValidMoves testClass = new ValidMoves();

        boolean valid = true;

        //Up two right one
        ChessPosition testPos = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() + 1);
        if((testPos.getRow() <= 8) && (testPos.getColumn() <= 8)) {
            valid = testClass.testMove(validMoves, board, myPosition, testPos);
        }

        //Up one right two
        testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 2);
        if((testPos.getRow() <= 8) && (testPos.getColumn() <= 8)) {
            valid = testClass.testMove(validMoves, board, myPosition, testPos);
        }

        //Down one right two
        testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 2);
        if((testPos.getRow() >= 1) && (testPos.getColumn() <= 8)) {
            valid = testClass.testMove(validMoves, board, myPosition, testPos);
        }

        //Down two right one
        testPos = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() + 1);
        if((testPos.getRow() >= 1) && (testPos.getColumn() <= 8)) {
            valid = testClass.testMove(validMoves, board, myPosition, testPos);
        }

        //Down two left one
        testPos = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() - 1);
        if((testPos.getRow() >= 1) && (testPos.getColumn() >=1)) {
            valid = testClass.testMove(validMoves, board, myPosition, testPos);
        }

        //Down one left two
        testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 2);
        if((testPos.getRow() >= 1) && (testPos.getColumn() >= 1)) {
            valid = testClass.testMove(validMoves, board, myPosition, testPos);
        }

        //Up one left two
        testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 2);
        if((testPos.getRow() <= 8) && (testPos.getColumn() >= 1)) {
            valid = testClass.testMove(validMoves, board, myPosition, testPos);
        }

        //Up two left one
        testPos = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() - 1);
        if((testPos.getRow() <= 8) && (testPos.getColumn() >= 1)) {
            valid = testClass.testMove(validMoves, board, myPosition, testPos);
        }
    }

    public Collection<ChessMove> getKnightMoves() {
        return validMoves;
    }
}
