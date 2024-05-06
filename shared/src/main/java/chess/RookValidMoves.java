package chess;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

public class RookValidMoves {

    Collection<ChessMove> validMoves;

    public RookValidMoves(ChessBoard board, ChessPosition myPosition) {

        validMoves = new ArrayList<>();

        //Right

        //Left

        //Down

        //Up
        boolean valid = true;
        int i = 1;
        while(valid) {
            ChessPosition testPos = new ChessPosition( myPosition.getRow() + i, myPosition.getColumn());
            if((myPosition.getRow() + i) > 8) {
                valid = false;
            } else {
                //valid = testMove(board, myPosition, testPos);
            }
            i++;
        }
    }
}
