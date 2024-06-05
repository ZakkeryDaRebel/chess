package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameBoardUI {

    ChessPiece[][] board = new ChessPiece[8][8];
    String[] letters;

    public GameBoardUI(ChessPiece[][] board) {
        this.board = board;
        letters = new String[] {" a ", " b ", " c ", " d ", " e ", " f ", " g ", " h "};
    }

    public void printWhiteSideBoard() {
        /*
            a   b   c   d   e   f   g   h
         8  r   n   b   q   k   b   n   r   8
         7  p   p   p   p   p   p   p   p   7
         6                                  6
         5                                  5
         4                                  4
         3                                  3
         2  P   P   P   P   P   P   P   P   2
         1  R   N   B   Q   K   B   N   R   1
            a   b   c   d   e   f   g   h
         */
    }

    public void printBlackSideBoard() {
        /*
            h   g   f   e   d   c   b   a
         1  R   N   B   K   Q   B   N   R   1
         2  P   P   P   P   P   P   P   P   2
         3                                  3
         4                                  4
         5                                  5
         6                                  6
         7  p   p   p   p   p   p   p   p   7
         8  r   n   b   k   q   b   n   r   8
            h   g   f   e   d   c   b   a
         */
    }

    public void printLettersWhite() {
        System.out.printf(EscapeSequences.SET_TEXT_COLOR_BLACK);
        System.out.printf(EscapeSequences.SET_BG_COLOR_LIGHT_GREY);
        System.out.print("   ");
        for(String letter : letters) {
            System.out.print(letter);
        }
        System.out.print("   ");
        System.out.printf(EscapeSequences.RESET_TEXT_COLOR);
        System.out.printf(EscapeSequences.RESET_BG_COLOR);
    }

    public void printLettersBlack() {

    }
}
