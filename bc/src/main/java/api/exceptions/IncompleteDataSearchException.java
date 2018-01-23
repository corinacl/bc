package api.exceptions;

public class IncompleteDataSearchException extends ApiException {

    private static final long serialVersionUID = -1344640670884801234L;

    public static final String DESCRIPTION = "Por favor, complete los campos de búsqueda";

    public static final int CODE = 51;

    public IncompleteDataSearchException() {
        this("");
    }

    public IncompleteDataSearchException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
