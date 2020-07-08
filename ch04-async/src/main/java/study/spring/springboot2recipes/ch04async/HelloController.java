package study.spring.springboot2recipes.ch04async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class HelloController {
  
  @GetMapping
  public CompletableFuture<String> hello() {
    
    log.info("called hello()");
    
    return CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(5);
      }
      catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      
      log.info("should return hello()");
      
      return "Hello!";
    });
  }
  
  @GetMapping("/hold")
  public void hold(@RequestParam(required = false) boolean doIt) throws InterruptedException {
    log.info("start hold");

    if (doIt) {
      log.info("thread hold");
      TimeUnit.SECONDS.sleep(10);
    }
    
    log.info("finish hold");
  }
  
}
