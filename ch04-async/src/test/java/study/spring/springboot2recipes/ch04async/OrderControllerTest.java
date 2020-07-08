package study.spring.springboot2recipes.ch04async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import study.spring.springboot2recipes.ch04async.domains.Order;
import study.spring.springboot2recipes.ch04async.service.OrderService;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
  
  @Autowired
  MockMvc mvc;
  
  @MockBean
  OrderService service;
  
  @Test
  void shouldGetOrders() throws Exception {
    given(service.createOrders()).willReturn(Arrays.asList(new Order("test", BigDecimal.ONE)));
  
    final MvcResult result = mvc.perform(get("/orders"))
        .andExpect(status().isOk())
        .andReturn();
  }
  
}
