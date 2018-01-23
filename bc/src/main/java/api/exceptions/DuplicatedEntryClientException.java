package api.exceptions;

public class DuplicatedEntryClientException extends ApiException {

    private static final long serialVersionUID = -1344640670884342385L;

    public static final String DESCRIPTION = "Dato duplicado";

    public static final int CODE = 54;

    public DuplicatedEntryClientException() {
        this("");
    }

    public DuplicatedEntryClientException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
