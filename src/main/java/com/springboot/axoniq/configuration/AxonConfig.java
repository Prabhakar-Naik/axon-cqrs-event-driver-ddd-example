package com.springboot.axoniq.configuration;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.queryhandling.SimpleQueryBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author prabhakar, @Date 08-08-2024
 */
@Configuration
public class AxonConfig {


    @Bean
    public Executor commandBusExecutor() {
        return Executors.newFixedThreadPool(10); // Customize the thread pool size
    }


    @Bean
    public ThreadPoolExecutor commandBusExecutor1() {
        return new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    @Bean
    public SimpleCommandBus commandBus(ThreadPoolExecutor executor) {
        return SimpleCommandBus.builder()
                .build();
    }

    @Bean
    public SimpleQueryBus queryBus(ThreadPoolExecutor executor) {
        return SimpleQueryBus.builder()
                .build();
    }


}
