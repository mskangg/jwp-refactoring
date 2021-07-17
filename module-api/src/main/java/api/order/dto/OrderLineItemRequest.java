package api.order.dto;

public class OrderLineItemRequest {
    private Long menuId;
    private long quantity;

    protected OrderLineItemRequest() {
    }

    private OrderLineItemRequest(Long menuId, long quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public static OrderLineItemRequest of(Long menuId, long quantity) {
        return new OrderLineItemRequest(menuId, quantity);
    }

    public Long getMenuId() {
        return menuId;
    }

    public long getQuantity() {
        return quantity;
    }
}
