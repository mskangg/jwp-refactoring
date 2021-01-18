package kitchenpos.table.dto;

import kitchenpos.table.domain.TableGroup;

import java.util.List;
import java.util.stream.Collectors;

public class TableGroupResponse {
    private final long id;
    private final List<OrderTableResponse> orderTables;

    public TableGroupResponse(long id, List<OrderTableResponse> orderTables) {
        this.id = id;
        this.orderTables = orderTables;
    }

    public long getId() {
        return id;
    }

    public List<OrderTableResponse> getOrderTables() {
        return orderTables;
    }

    public static TableGroupResponse of(TableGroup save) {
        List<OrderTableResponse> orderTableResponses = save.getOrderTables()
                .stream()
                .map(OrderTableResponse::of)
                .collect(Collectors.toList());
        return new TableGroupResponse(save.getId(), orderTableResponses);
    }
}