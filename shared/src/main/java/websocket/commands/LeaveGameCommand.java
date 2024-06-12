package websocket.commands;

public class LeaveGameCommand extends UserGameCommand{

    private Integer gameID;

    public LeaveGameCommand(String authToken, Integer gameID) {
        super(authToken);
        this.commandType = CommandType.LEAVE;
        this.gameID = gameID;
    }

    public Integer getGameID() {
        return gameID;
    }
}
