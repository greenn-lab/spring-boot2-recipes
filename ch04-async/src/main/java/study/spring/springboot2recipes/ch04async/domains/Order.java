package study.spring.springboot2recipes.ch04async.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@ToString
public class Order {
  
  private String id;
  private BigDecimal amount;
  
}
