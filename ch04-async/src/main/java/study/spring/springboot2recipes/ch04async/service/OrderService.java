package study.spring.springboot2recipes.ch04async.service;

import org.springframework.stereotype.Service;
import study.spring.springboot2recipes.ch04async.domains.Order;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {
  
  private final List<Order> orders = new ArrayList<>();
  
  @PostConstruct
  public void setup() {
    createOrders();
  }
  
  public Iterable<Order> findAll() {
    return Collections.unmodifiableList(orders);
  }
  
  public Iterable<Order> createOrders() {
    for (int i = 0; i < 25; i++)
      orders.add(createOrder());
    
    return orders;
  }
  
  private Order createOrder() {
    final String id = UUID.randomUUID().toString();
    final double amount = ThreadLocalRandom.current().nextDouble(1_000.00D);
    return new Order(id, BigDecimal.valueOf(amount));
    
  }
  
}
