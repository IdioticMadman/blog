package com.robert.blog.repository.impl;

import com.robert.blog.domain.User;
import com.robert.blog.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用户仓储实现
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final ConcurrentMap<Long, User> userCache = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public User insertOrUpdate(User user) {
        Long userId = user.getId();
        if (userId == null) {
            userId = counter.incrementAndGet();
            user.setId(userId);
        }
        userCache.put(userId, user);
        return user;
    }

    @Override
    public void delete(Long userId) {

    }

    @Override
    public User getUser(Long userId) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }
}
