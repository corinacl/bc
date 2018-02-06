package api.exceptions;

public class IncompleteFieldException extends ApiException {

    private static final long serialVersionUID = -1344640670884801234L;

    public static final String DESCRIPTION = "Introduzca todos los campos";

    public static final int CODE = 59;

    public IncompleteFieldException() {
        this("");
    }

    public IncompleteFieldException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
