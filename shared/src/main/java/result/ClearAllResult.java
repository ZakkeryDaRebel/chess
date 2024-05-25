package result;

import com.google.gson.JsonObject;

import java.util.Map;

public class ClearAllResult extends ParentResult {

    public ClearAllResult(boolean success, String message) {
        super(success, message);
    }
}
