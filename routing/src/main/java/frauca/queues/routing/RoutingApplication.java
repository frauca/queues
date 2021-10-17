package frauca.queues.routing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@Slf4j
public class RoutingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutingApplication.class);
    }

    @Bean
    public Consumer<String> even(){
        return message -> log.info("even logger {}", message);
    }

    @Bean
    public Consumer<String> odd(){
        return message -> log.info("odd logger {}", message);
    }
}
