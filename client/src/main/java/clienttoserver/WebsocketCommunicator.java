package clienttoserver;

import com.google.gson.Gson;
import websocket.messages.ErrorMessage;
import websocket.messages.ServerMessage;

public class WebsocketCommunicator {

/*
    public void onMessage(String message) {
        try {
            ServerMessage serverMessage = Gson.fromJson(message, ServerMessage.class);
            observer.notify(message);
        } catch(Exception ex) {
            observer.notify(new ErrorMessage(ex.getMessage()));
        }
    }
*/
}
