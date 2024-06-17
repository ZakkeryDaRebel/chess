package ui;

import chess.ChessGame;
import chess.ChessPiece;

public class GameBoardUI {

    private ChessPiece[][] board;
    private final String[] letters;

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

        printLettersWhite();
        System.out.println();
        for(int i = 8; i >= 1; i--) {
            printRowWhite(i);
            System.out.println();
        }
        printLettersWhite();
        System.out.println();
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
        printLettersBlack();
        System.out.println();
        for(int i = 1; i <= 8; i++) {
            printRowBlack(i);
            System.out.println();
        }
        printLettersBlack();
        System.out.println();
    }

    public void setBorderSquare() {
        System.out.printf(EscapeSequences.SET_TEXT_COLOR_BLACK + EscapeSequences.SET_BG_COLOR_LIGHT_GREY);
    }

    public void setSquareToNormal() {
        System.out.printf(EscapeSequences.RESET_TEXT_COLOR + EscapeSequences.RESET_BG_COLOR);
    }

    public void setBackGroundDark() {
        System.out.printf(EscapeSequences.SET_BG_COLOR_BLACK);
    }

    public void setBackGroundLight() {
        System.out.printf(EscapeSequences.SET_BG_COLOR_WHITE);
    }

    public void setBackGroundColor(int row, int col) {
        if(((row + col) % 2) == 0)
            setBackGroundDark();
        else
            setBackGroundLight();
    }

    public void printEmptySpace() {
        System.out.print("   ");
    }

    public void setTextWhiteTeam() {
        System.out.print(EscapeSequences.SET_TEXT_COLOR_RED);
    }

    public void setTextBlackTeam() {
        System.out.print(EscapeSequences.SET_TEXT_COLOR_BLUE);
    }

    public void setTextColor(int row, int col) {
        if(board[row-1][col-1] != null) {
            if(board[row-1][col-1].getTeamColor() == ChessGame.TeamColor.WHITE) {
                setTextWhiteTeam();
            } else {
                setTextBlackTeam();
            }
        }
    }

    public void printLettersWhite() {
        setBorderSquare();
        printEmptySpace();
        for(int i = 0; i < 8; i++) {
            System.out.print(letters[i]);
        }
        printEmptySpace();
        setSquareToNormal();
    }

    public void printRowWhite(int row) {
        printEdge(row);
        for(int i = 1; i <= 8; i++) {
            setBackGroundColor(row, i);
            setTextColor(row, i);
            printPieceType(row, i);
        }
        printEdge(row);
        setSquareToNormal();
    }

    public void printRowBlack(int row) {
        printEdge(row);
        for(int i = 8; i >= 1; i--) {
            setBackGroundColor(row, i);
            setTextColor(row, i);
            printPieceType(row, i);
        }
        printEdge(row);
        setSquareToNormal();
    }

    public void printEdge(int row) {
        setBorderSquare();
        System.out.print(" " + row + " ");
    }

    public void printLettersBlack() {
        setBorderSquare();
        printEmptySpace();
        for(int i = 7; i >= 0; i--) {
            System.out.print(letters[i]);
        }
        printEmptySpace();
        setSquareToNormal();
    }

    public void printPieceType(int row, int col) {
        if(board[row-1][col-1] == null) {
            printEmptySpace();
        } else if(board[row-1][col-1].getPieceType() == ChessPiece.PieceType.PAWN) {
            System.out.print(" P ");
        } else if(board[row-1][col-1].getPieceType() == ChessPiece.PieceType.KNIGHT) {
            System.out.print(" N ");
        } else if(board[row-1][col-1].getPieceType() == ChessPiece.PieceType.ROOK) {
            System.out.print(" R ");
        } else if(board[row-1][col-1].getPieceType() == ChessPiece.PieceType.BISHOP) {
            System.out.print(" B ");
        } else if(board[row-1][col-1].getPieceType() == ChessPiece.PieceType.QUEEN) {
            System.out.print(" Q ");
        } else {
            System.out.print(" K ");
        }
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public void setBoard(ChessPiece[][] board) {
        this.board = board;
    }
}
