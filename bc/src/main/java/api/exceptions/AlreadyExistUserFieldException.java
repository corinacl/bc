package api.exceptions;

public class AlreadyExistUserFieldException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "El usuario ya existe. Elija otro nombre de usuario";

    public static final int CODE = 1;

    public AlreadyExistUserFieldException() {
        this("");
    }

    public AlreadyExistUserFieldException(String detail) {
        super(DESCRIPTION + detail, CODE);
    }

}
