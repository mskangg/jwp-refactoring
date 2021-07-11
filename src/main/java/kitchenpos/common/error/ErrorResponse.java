package kitchenpos.common.error;

public class ErrorResponse {
    private final String message;

    protected ErrorResponse() {
        this.message = "";
    }

    private ErrorResponse(String message) {
        this.message = message;
    }

    public static ErrorResponse of(String message){
        return new ErrorResponse(message);
    }

    public String getMessage() {
        return message;
    }
}
