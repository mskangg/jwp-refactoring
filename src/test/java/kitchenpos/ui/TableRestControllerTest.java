package kitchenpos.ui;

import kitchenpos.table.presentation.dto.OrderTableRequest;
import kitchenpos.table.presentation.dto.OrderTableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("주문 테이블 통합테스트")
class TableRestControllerTest extends IntegrationSupport {
    private static final String URI = "/api/tables";

    @DisplayName("주문 테이블을 추가한다.")
    @Test
    void create() throws Exception {
        //when
        ResultActions actions = mockMvc.perform(postAsJson(URI, OrderTableRequest.of(true)));

        //then
        actions.andExpect(status().isCreated());
        OrderTableResponse response = toObject(actions.andReturn(), OrderTableResponse.class);
        //and then
        assertThat(response.getId()).isNotNull();
        assertThat(response.getTableGroupId()).isNull();
        assertThat(response.getNumberOfGuests()).isEqualTo(0);
        assertThat(response.isEmpty()).isTrue();
    }

    @DisplayName("주문 테이블을 모두 조회한다.")
    @Test
    void list() throws Exception {
        //when
        ResultActions actions = mockMvc.perform(get(URI));

        //then
        actions.andExpect(status().isOk());
        //and then
        List<OrderTableResponse> response = toList(actions.andReturn(), OrderTableResponse.class);
        assertThat(response).isNotEmpty();
    }

    @DisplayName("특정 주문 테이블의 상태를 변경한다.")
    @Test
    void changeEmpty() throws Exception {
        //when
        ResultActions actions = mockMvc.perform(putAsJson(URI + "/1/empty", OrderTableRequest.of(false)));

        //then
        actions.andExpect(status().isOk());
        //and then
        OrderTableResponse response = toObject(actions.andReturn(), OrderTableResponse.class);
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.isEmpty()).isFalse();
    }

    @DisplayName("특정 테이블의 인원 수를 예약한다.")
    @Test
    void changeNumberOfGuests() throws Exception {
        //given
        mockMvc.perform(putAsJson("/api/tables/1/empty", OrderTableRequest.of(false)));

        //when
        ResultActions actions = mockMvc.perform(putAsJson(URI + "/1/number-of-guests", OrderTableRequest.of(4)));

        //then
        actions.andExpect(status().isOk());
        //and then
        OrderTableResponse response = toObject(actions.andReturn(), OrderTableResponse.class);
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getNumberOfGuests()).isEqualTo(4);
        assertThat(response.isEmpty()).isFalse();
    }
}
