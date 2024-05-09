package chess;

import java.util.Collection;
import java.util.ArrayList;

public class QueenValidMoves {

    Collection<ChessMove> validMoves;

    public QueenValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();
        ValidMoves testClass = new ValidMoves();

        //Up
        boolean valid = true;
        int i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn());
            valid = testClass.LoopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Up and Right
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() + i);
            valid = testClass.LoopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Right
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow(), myPosition.getColumn() + i);
            valid = testClass.LoopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Down and Right
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() + i);
            valid = testClass.LoopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Down
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn());
            valid = testClass.LoopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Down and Left
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() - i, myPosition.getColumn() - i);
            valid = testClass.LoopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Left
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow(), myPosition.getColumn() - i);
            valid = testClass.LoopTest(validMoves, board, myPosition, testPos);
            i++;
        }

        //Up and Right
        valid = true;
        i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition(myPosition.getRow() + i, myPosition.getColumn() - i);
            valid = testClass.LoopTest(validMoves, board, myPosition, testPos);
            i++;
        }
    }

    public Collection<ChessMove> getQueenMoves() {
        return validMoves;
    }
}
