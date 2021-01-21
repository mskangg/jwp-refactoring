package kitchenpos.ui;

import kitchenpos.product.dto.ProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("상품 Controller Test")
class ProductRestControllerTest extends RestControllerTest {

    public static final String PRODUCTS_API_URL = "/api/products";

    @DisplayName("상품을 등록할 수 있다.")
    @Test
    void create() throws Exception {
        //given
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("볶음밥");
        productRequest.setPrice(new BigDecimal(7000));

        //when
        mockMvc.perform(
                post(PRODUCTS_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(productRequest))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrlPattern(PRODUCTS_API_URL + "/*"))
                .andExpect(jsonPath("$['name']", is("볶음밥")));

        //then
    }

    @DisplayName("상품의 목록을 조회할 수 있다.")
    @Test
    void list() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get(PRODUCTS_API_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(6)))
                .andExpect(jsonPath("$[0]['name']", is("후라이드")))
                .andExpect(jsonPath("$[1]['name']", is("양념치킨")))
                .andExpect(jsonPath("$[2]['name']", is("반반치킨")))
                .andExpect(jsonPath("$[3]['name']", is("통구이")))
                .andExpect(jsonPath("$[4]['name']", is("간장치킨")))
                .andExpect(jsonPath("$[5]['name']", is("순살치킨")));
    }
}