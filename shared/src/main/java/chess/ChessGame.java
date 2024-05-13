package chess;

import java.util.Collection;
import java.util.HashMap;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private TeamColor colorTurn;
    private ChessBoard newGame;
    public ChessGame() {
        colorTurn = TeamColor.WHITE;
        newGame = new ChessBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() { return colorTurn; }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) { colorTurn = team; }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if (newGame.getPiece(startPosition) == null) {
            return null;
        } else {
            return newGame.getPiece(startPosition).pieceMoves(newGame,startPosition);
        }
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        //First check to see if the move is valid
        ChessPosition checkPos = move.getEndPosition();
        if((checkPos.getRow() < 1) || (checkPos.getRow() > 8) || (checkPos.getColumn() < 1) || (checkPos.getColumn() > 8)) {
            throw new InvalidMoveException();
        }
        //Get a copy of the piece at the starting position
        ChessBoard moveBoard = getBoard();
        ChessPiece piece = moveBoard.getPiece(move.getStartPosition());
        //Set the starting position to null
        moveBoard.addPiece(move.getStartPosition(), piece);
        //If the promotion is null, then set the new position to the copy of the piece
        if(move.getPromotionPiece() == null) {
            moveBoard.addPiece(move.getEndPosition(), piece);
        }
        //If the promotion isn't null, then set the new position to the promotion piece
        else {
            ChessPiece promotedPiece = new ChessPiece(piece.getTeamColor(), move.getPromotionPiece());
            moveBoard.addPiece(move.getEndPosition(), promotedPiece);
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        if(teamColor == TeamColor.WHITE) {
            Collection<ChessMove> blackMoves = getBoard().teamValidMoves("B");
            ChessPosition whiteKingPos = getBoard().getWhiteKing();
            for(ChessMove move : blackMoves) {
                if(move.getEndPosition() == whiteKingPos)
                    return true;
            }
            return false;
        } else {
            Collection<ChessMove> whiteMoves = getBoard().teamValidMoves("W");
            ChessPosition blackKingPos = getBoard().getBlackKing();
            for(ChessMove move : whiteMoves) {
                if(move.getEndPosition() == blackKingPos)
                    return true;
            }
            return false;
        }
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        //Check to see which team color to check
        if(teamColor == TeamColor.WHITE)
        {
            //Loop through all the pieces
            HashMap<String, ChessPosition> whiteTeam = newGame.getWhiteTeam();
            for (var p : whiteTeam.entrySet()) {
                if(p.getKey().startsWith("Pawn")) {
                    if(newGame.getPiece(p.getValue()).pieceMoves(newGame, p.getValue()) != null)
                        return false;
                }
            }
            return true;
            //If a piece has any valid moves, return false
            //If I reach the end and no piece has any valid moves, return true
            // ** Flaw is if the king is in check, then it would be Checkmate, not Stalemate
        } else {
            //Loop through all the pieces
            HashMap<String, ChessPosition> blackTeam = newGame.getBlackTeam();
            //If a piece has any valid moves, return false
            //If I reach the end and no piece has any valid moves, return true
            // ** Flaw is if the king is in check, then it would be Checkmate, not Stalemate
        }

        return false;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        newGame = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return newGame;
    }
}
