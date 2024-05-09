package chess;

import java.util.Collection;
import java.util.ArrayList;

public class KingValidMoves {

    Collection<ChessMove> validMoves;

    public KingValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();
        ValidMoves testClass = new ValidMoves();

        //Up
        ChessPosition testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
        if(testClass.inBoard(testPos))
            testClass.testMove(validMoves, board, myPosition, testPos);

        //Up and to the right
        testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
        if(testClass.inBoard(testPos))
            testClass.testMove(validMoves, board, myPosition, testPos);

        //Right
        testPos = new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1);
        if(testClass.inBoard(testPos))
            testClass.testMove(validMoves, board, myPosition, testPos);

        //Down and Right
        testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
        if(testClass.inBoard(testPos))
            testClass.testMove(validMoves, board, myPosition, testPos);

        //Down
        testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
        if(testClass.inBoard(testPos))
            testClass.testMove(validMoves, board, myPosition, testPos);

        //Down and Left
        testPos = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
        if(testClass.inBoard(testPos))
            testClass.testMove(validMoves, board, myPosition, testPos);

        //Left
        testPos = new ChessPosition(myPosition.getRow(), myPosition.getColumn() - 1);
        if(testClass.inBoard(testPos))
            testClass.testMove(validMoves, board, myPosition, testPos);

        //Up and Left
        testPos = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
        if(testClass.inBoard(testPos))
            testClass.testMove(validMoves, board, myPosition, testPos);
    }

    public Collection<ChessMove> getKingMoves() {
        return validMoves;
    }
}
