package kitchenpos.common.valueobject;

import kitchenpos.common.valueobject.exception.NegativePriceException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Price {
    private static final long MIN_PRICE = 0;

    @Column(name = "price", nullable = false)
    private final BigDecimal value;

    protected Price() {
        this.value = BigDecimal.ZERO;
    }

    private Price(BigDecimal value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(BigDecimal value) {
        if (Objects.isNull(value) || value.compareTo(BigDecimal.valueOf(MIN_PRICE)) < 0) {
            throw new NegativePriceException(MIN_PRICE);
        }
    }

    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    public BigDecimal getValue() {
        return BigDecimal.valueOf(value.longValue());
    }

    public BigDecimal calculateByQuantity(Quantity quantity) {
        return value.multiply(BigDecimal.valueOf(quantity.getValue()));
    }

    public Price add(Price price) {
        return Price.of(value.add(price.getValue()));
    }

    public boolean isMoreExpensiveThan(Price price) {
        return this.value.compareTo(price.getValue()) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(value, price.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
