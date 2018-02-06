package api.exceptions;

public class NotFoundClientIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encuentra el identificador de cliente utilizado";

    public static final int CODE = 4;

    public NotFoundClientIdException() {
        this("");
    }

    public NotFoundClientIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
