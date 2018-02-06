package api.exceptions;

public class IncompleteBookingException extends ApiException {

    private static final long serialVersionUID = -1344640670884814385L;

    public static final String DESCRIPTION = "Por favor, rellene todos los campos";

    public static final int CODE = 24;

    public IncompleteBookingException() {
        this("");
    }

    public IncompleteBookingException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
