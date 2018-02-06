package api.exceptions;

public class ApiException extends Exception {

    private static final long serialVersionUID = 1613504205825647750L;

    private static final String URL = "https://github.com/corinacl/bc/wiki/Exceptions#";

    private String url;

    public ApiException(String description, int code) {
        super(description);
        this.url = URL + code;
    }

    public String getUrl() {
        return url;
    }

}
