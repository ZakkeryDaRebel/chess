import chess.*;

import java.util.Scanner;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Client: " + piece);
        Scanner scan = new Scanner(System.in);
        String input = "start";
        boolean login = false;
        while(!(input.equalsIgnoreCase("quit") || input.equals("2"))) {
            if(input.equals("start")) {
                chessClientStartUp();
            } else if(input.equals("help")) {

            }

            if(login) {

            } else { //login = false

            }
            listOptions(login);
            input = scan.nextLine();
        }
    }

    static void chessClientStartUp() {
        System.out.println("~ Hi! Welcome to the Chess Server! ~");
        System.out.println("Please input the number or name of what you would like to do.");
        System.out.println("If you have any questions, please enter 1\n"
                         + "  or Help for assistance and explanations.");
    }

    static void listOptions(boolean loggedIn) {
        System.out.println(" 1. Help");
        System.out.println(" 2. Quit");
        if(!loggedIn) {
            System.out.println(" 3. Register");
            System.out.println(" 4. Login");
        } else {
            System.out.println(" 3. Logout");
            System.out.println(" 4. Create Game");
            System.out.println(" 5. List Games");
            System.out.println(" 6. Play Game");
            System.out.println(" 7. Observe Game");
        }
    }
}