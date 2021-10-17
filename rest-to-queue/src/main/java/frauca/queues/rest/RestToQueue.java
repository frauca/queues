package frauca.queues.rest;

import frauca.queues.rest.interceptor.LogMeInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestToQueue {

    private final StreamBridge queue;

    public RestToQueue(StreamBridge queue) {
        this.queue = queue;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestToQueue.class);
    }

    @Bean
    @GlobalChannelInterceptor(patterns = "*")
    public ChannelInterceptor interceptor() {
        return new LogMeInterceptor();
    }

    @GetMapping(value = "hello")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestParam String message) {
        queue.send("justLog", message);
    }

}
