package com.expensetracker.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCacheInvalidationService {

    private final StringRedisTemplate redisTemplate;

    public void evictUserCachesAfterCommit(
            Long userId,
            String... cacheNames
    ) {

        if (userId == null || cacheNames == null
                || cacheNames.length == 0) {
            return;
        }

        Runnable evictionTask = () ->
                evictUserCaches(userId, cacheNames);

        if (TransactionSynchronizationManager.isActualTransactionActive()
                && TransactionSynchronizationManager.isSynchronizationActive()) {

            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {

                        @Override
                        public void afterCommit() {
                            evictionTask.run();
                        }
                    }
            );

        } else {
            evictionTask.run();
        }
    }

    private void evictUserCaches(
            Long userId,
            String... cacheNames
    ) {

        for (String cacheName : cacheNames) {

            String pattern = "smart-expense-tracker::"
                    + cacheName + "::"
                    + userId + ":*";

            try {

                Set<String> matchingKeys = redisTemplate.execute(
                        (RedisCallback<Set<String>>) connection -> {

                            Set<String> keys = new HashSet<>();

                            ScanOptions options = ScanOptions.scanOptions()
                                    .match(pattern)
                                    .count(500)
                                    .build();

                            try (Cursor<byte[]> cursor =
                                         connection.scan(options)) {

                                while (cursor.hasNext()) {

                                    String key = new String(
                                            cursor.next(),
                                            StandardCharsets.UTF_8
                                    );

                                    keys.add(key);
                                }
                            }

                            return keys;
                        }
                );

                if (matchingKeys != null && !matchingKeys.isEmpty()) {

                    Long deletedCount = redisTemplate.delete(matchingKeys);

                    log.info(
                            "Invalidated {} entries from cache '{}' for user {}",
                            deletedCount,
                            cacheName,
                            userId
                    );
                }

            } catch (Exception e) {

                log.error(
                        "Failed to invalidate cache '{}' for user {}",
                        cacheName,
                        userId,
                        e
                );
            }
        }
    }
}
