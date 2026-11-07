package com.expensetracker.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisHealthCheck implements ApplicationRunner {

    private final RedisConnectionFactory connectionFactory;

    @Override
    public void run(ApplicationArguments args) {

        try (RedisConnection connection =
                     connectionFactory.getConnection()) {

            String response = connection.ping();

            if ("PONG".equalsIgnoreCase(response)) {
                log.info("==================================");
                log.info("REDIS CONNECTION SUCCESSFUL");
                log.info("Redis response: {}", response);
                log.info("==================================");
            }

        } catch (Exception exception) {
            log.error("REDIS CONNECTION FAILED", exception);
        }
    }
}