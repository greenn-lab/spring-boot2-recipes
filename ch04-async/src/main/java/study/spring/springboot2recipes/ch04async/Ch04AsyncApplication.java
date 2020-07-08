package study.spring.springboot2recipes.ch04async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
public class Ch04AsyncApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Ch04AsyncApplication.class, args);
	}
  
  @Override
  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    configurer.setTaskExecutor(taskExecutor());
  }
  
  @Bean
  public ThreadPoolTaskExecutor taskExecutor() {
	  final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	  executor.setThreadNamePrefix("mvc-task");
	  return executor;
  }
}
