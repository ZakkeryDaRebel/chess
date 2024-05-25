package result;

import com.google.gson.JsonObject;

import java.util.Map;

public class ClearAllResult extends ParentResult {

    public ClearAllResult(Boolean success, String message) {
        super(success, message);
    }
}
