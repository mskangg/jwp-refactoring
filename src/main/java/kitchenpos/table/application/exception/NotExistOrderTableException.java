package kitchenpos.table.application.exception;

import kitchenpos.common.error.exception.NotExistException;

public class NotExistOrderTableException extends NotExistException {
    public NotExistOrderTableException() {
        super("해당하는 주문 테이블을 찾을 수 없습니다.");
    }

    public NotExistOrderTableException(String message) {
        super(message);
    }
}
