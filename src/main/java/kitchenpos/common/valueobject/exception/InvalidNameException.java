package kitchenpos.common.valueobject.exception;

import kitchenpos.common.error.exception.BusinessException;

public class InvalidNameException extends BusinessException {
    public InvalidNameException() {
        super("이름을 지정해야합니다.");
    }

    public InvalidNameException(String message) {
        super(message);
    }
}
