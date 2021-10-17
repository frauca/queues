package frauca.queues.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
@Slf4j
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }

    @Bean
    Function<Flux<String>, Mono<Void>> justReactiveLog(){
        return flux -> flux
                .doOnNext(message -> log.info("We got a message but with reactive {}", message))
                .then();
    }

    @Bean
    Consumer<String> justLog(){
        return message -> log.info("We got a nice message {}",message);
    }


}
