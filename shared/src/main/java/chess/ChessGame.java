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
        newGame.resetBoard();
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

    public void changeTeamTurn() {
        if(this.colorTurn == TeamColor.WHITE)
            setTeamTurn(TeamColor.BLACK);
        else
            setTeamTurn(TeamColor.WHITE);
    }

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
        if(move == null)
            throw new InvalidMoveException();

        //First check to see if the move is valid
        ChessPosition checkPos = move.getEndPosition();
        if((checkPos.getRow() < 1) || (checkPos.getRow() > 8) || (checkPos.getColumn() < 1) || (checkPos.getColumn() > 8)) {
            throw new InvalidMoveException();
        }
        //Get a copy of the piece at the starting position
        ChessBoard moveBoard = getCopy();
        ChessPiece piece = moveBoard.getPiece(move.getStartPosition());

        if(moveBoard.getPiece(move.getStartPosition()) == null)
            throw new InvalidMoveException();

        if(moveBoard.getPiece(move.getStartPosition()).getTeamColor() != this.getTeamTurn())
            throw new InvalidMoveException();

        //Set the starting position to null
        if(piece.pieceMoves(moveBoard, move.getStartPosition()).contains(move)) {
            moveBoard.addPiece(move.getStartPosition(), null);
            //If the promotion is null, then set the new position to the copy of the piece
            if(move.getPromotionPiece() == null) {
                moveBoard.addPiece(move.getEndPosition(), piece);
            }
            //If the promotion isn't null, then set the new position to the promotion piece
            else {
                ChessPiece promotedPiece = new ChessPiece(piece.getTeamColor(), move.getPromotionPiece());
                moveBoard.addPiece(move.getEndPosition(), promotedPiece);
                String oldName = "";
                HashMap<String, ChessPosition> copyWhiteTeam = newGame.getWhiteTeam();
                for(HashMap.Entry<String, ChessPosition> pieceMap : copyWhiteTeam.entrySet()) {
                    if(pieceMap.getKey().contains("Pawn") && pieceMap.getValue().equals(move.getStartPosition())) {
                        oldName = pieceMap.getKey();
                        break;
                    }
                }
                if(oldName == null)
                    throw new InvalidMoveException();
                String name = oldName.charAt(0) + "";
                if(promotedPiece.getPieceType() == ChessPiece.PieceType.QUEEN) {
                    name += "Queen";
                } else if(promotedPiece.getPieceType() == ChessPiece.PieceType.ROOK) {
                    name += "Rook";
                } else if(promotedPiece.getPieceType() == ChessPiece.PieceType.BISHOP) {
                    name += "Bishop";
                } else if(promotedPiece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
                    name += "Knight";
                } else {
                    throw new InvalidMoveException();
                }
                if(piece.getTeamColor() == TeamColor.WHITE) {
                    HashMap<String, ChessPosition> newWhiteTeam = moveBoard.getWhiteTeam();
                    newWhiteTeam.remove(oldName);
                    newWhiteTeam.put(name,move.getEndPosition());
                    moveBoard.setWhiteTeam(newWhiteTeam);
                } else {
                    HashMap<String, ChessPosition> newBlackTeam = moveBoard.getBlackTeam();
                    newBlackTeam.remove(oldName);
                    newBlackTeam.put(name,move.getEndPosition());
                    moveBoard.setBlackTeam(newBlackTeam);
                }
            }
        } else {
            throw new InvalidMoveException();
        }


        setBoard(moveBoard);
        if(!isInCheck(moveBoard.getPiece(move.getEndPosition()).getTeamColor())) {
            setBoard(moveBoard);
            changeTeamTurn();
        } else
            throw new InvalidMoveException();
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
            if(blackMoves.isEmpty()) {
                ChessBoard moveBoard = getCopy();
                for(int i = 1; i < 9; i++) {
                    for(int j = 1; j < 9; j++) {
                        if((moveBoard.getPiece(new ChessPosition(i,j)) != null)) {
                            if((moveBoard.getPiece(new ChessPosition(i,j)).getPieceType() == ChessPiece.PieceType.KING) && (moveBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == TeamColor.WHITE)) {
                                whiteKingPos = new ChessPosition(i,j);
                            } else if(moveBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == TeamColor.BLACK) {
                                blackMoves.addAll(moveBoard.getPiece(new ChessPosition(i,j)).pieceMoves(moveBoard, new ChessPosition(i,j)));
                            }
                        }
                    }
                }
            }
            for (ChessMove move : blackMoves) {
                if (move.getEndPosition().equals(whiteKingPos))
                    return true;
            }
            return false;
        } else {
            Collection<ChessMove> whiteMoves = getBoard().teamValidMoves("W");
            ChessPosition blackKingPos = getBoard().getBlackKing();
            if(whiteMoves.isEmpty()) {
                ChessBoard moveBoard = getCopy();
                for(int i = 1; i < 9; i++) {
                    for(int j = 1; j < 9; j++) {
                        if((moveBoard.getPiece(new ChessPosition(i,j)) != null)) {
                            if((moveBoard.getPiece(new ChessPosition(i,j)).getPieceType() == ChessPiece.PieceType.KING) && (moveBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == TeamColor.BLACK)) {
                                blackKingPos = new ChessPosition(i,j);
                            } else if(moveBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == TeamColor.WHITE) {
                                whiteMoves.addAll(moveBoard.getPiece(new ChessPosition(i,j)).pieceMoves(moveBoard, new ChessPosition(i,j)));
                            }
                        }
                    }
                }
                for(ChessMove attack : whiteMoves) {
                    if(attack.getEndPosition().equals(blackKingPos))
                        return true;
                }
            }
            for (ChessMove move : whiteMoves) {
                if (move.getEndPosition().equals(blackKingPos))
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
        if(!isInCheck(teamColor))
            return false;
        ChessBoard moveBoard = getCopy();
        if(teamColor == TeamColor.WHITE) {
            //For loop to find all the possible moves white has
            Collection<ChessMove> whiteMoves = getBoard().teamValidMoves("W");
            ChessPosition whiteKingPos = getBoard().getWhiteKing();
            if(whiteMoves.isEmpty()) {
                for(int i = 1; i < 9; i++) {
                    for(int j = 1; j < 9; j++) {
                        if((moveBoard.getPiece(new ChessPosition(i,j)) != null)) {
                            if((moveBoard.getPiece(new ChessPosition(i,j)).getPieceType() == ChessPiece.PieceType.KING) && (moveBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == TeamColor.WHITE)) {
                                whiteKingPos = new ChessPosition(i,j);
                                whiteMoves.addAll(moveBoard.getPiece(whiteKingPos).pieceMoves(moveBoard, whiteKingPos));
                            } else if(moveBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == TeamColor.WHITE) {
                                whiteMoves.addAll(moveBoard.getPiece(new ChessPosition(i,j)).pieceMoves(moveBoard, new ChessPosition(i,j)));
                            }
                        }
                    }
                }
            }
            for (ChessMove move : whiteMoves) {
                ChessGame testMove = new ChessGame();
                ChessBoard testBoard = this.getCopy();
                testMove.setBoard(testBoard);
                testMove.setTeamTurn(colorTurn);
                try {
                    testMove.makeMove(move);
                } catch(InvalidMoveException e) {
                    continue;
                }
                if(!testMove.isInCheck(TeamColor.WHITE))
                    return false;
            }
            return true;
        } else {
            Collection<ChessMove> blackMoves = getBoard().teamValidMoves("B");
            ChessPosition blackKingPos = getBoard().getBlackKing();
            if(blackMoves.isEmpty()) {
                for(int i = 1; i < 9; i++) {
                    for(int j = 1; j < 9; j++) {
                        if((moveBoard.getPiece(new ChessPosition(i,j)) != null)) {
                            if((moveBoard.getPiece(new ChessPosition(i,j)).getPieceType() == ChessPiece.PieceType.KING) && (moveBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == TeamColor.BLACK)) {
                                blackKingPos = new ChessPosition(i,j);
                                blackMoves.addAll(moveBoard.getPiece(blackKingPos).pieceMoves(moveBoard, blackKingPos));
                            } else if(moveBoard.getPiece(new ChessPosition(i,j)).getTeamColor() == TeamColor.BLACK) {
                                blackMoves.addAll(moveBoard.getPiece(new ChessPosition(i,j)).pieceMoves(moveBoard, new ChessPosition(i,j)));
                            }
                        }
                    }
                }
            }
            for (ChessMove move : blackMoves) {
                ChessGame testMove = new ChessGame();
                ChessBoard testBoard = this.getCopy();
                testMove.setBoard(testBoard);
                testMove.setTeamTurn(colorTurn);
                try {
                    testMove.makeMove(move);
                } catch(InvalidMoveException e) {
                    continue;
                }
                if(!testMove.isInCheck(TeamColor.BLACK))
                    return false;
            }
            return true;
        }
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
        if(isInCheck(teamColor))
            return false;

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

    public ChessBoard getCopy() {
        try {
            return newGame.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "colorTurn=" + colorTurn +
                ", newGame=" + newGame +
                '}';
    }
}
