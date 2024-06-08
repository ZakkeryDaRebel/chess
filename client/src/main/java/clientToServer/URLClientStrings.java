package clientToServer;

public class URLClientStrings {

    String urlPath;
    String requestMethod;

    public URLClientStrings(String path, String method) {
        urlPath = path;
        requestMethod = method;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }
}
