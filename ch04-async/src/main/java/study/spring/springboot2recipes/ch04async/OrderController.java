package study.spring.springboot2recipes.ch04async;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import study.spring.springboot2recipes.ch04async.domains.Order;
import study.spring.springboot2recipes.ch04async.service.OrderService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService service;
  
  @GetMapping
  public ResponseBodyEmitter orders() {
    final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
      for (Order order : service.findAll()) {
        try {
          TimeUnit.MILLISECONDS.sleep(500);
          emitter.send(order);
        }
        catch (IOException | InterruptedException e) {
          emitter.completeWithError(e);
        }
      }
    });

    executorService.shutdown();
    return emitter;
  }
  
  
}
