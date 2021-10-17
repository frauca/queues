package frauca.queues.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@Slf4j
public class ErrorAplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorAplication.class);
    }

    @Bean
    public Consumer<String> couldBeAnError(){
        return message -> {
            if("bye".equals(message)){
                throw new RuntimeException("Could not accept this message");
            }else{
                log.info("this time is not an error {}",message);
            }
        };
    }
}
