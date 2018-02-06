package api.exceptions;

public class IncompletePlanningFormException extends ApiException {

    private static final long serialVersionUID = -1344640670884801234L;

    public static final String DESCRIPTION = "Introduce el mes y el año para mostrar el planning";

    public static final int CODE = 10;

    public IncompletePlanningFormException() {
        this("");
    }

    public IncompletePlanningFormException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
