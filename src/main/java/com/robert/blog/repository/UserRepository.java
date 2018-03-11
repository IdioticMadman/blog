package com.robert.blog.repository;

import com.robert.blog.domain.User;

import java.util.List;

/**
 * 用户仓库
 */
public interface UserRepository {
    /**
     * 插入或者更新数据
     */
    User insertOrUpdate(User user);

    /**
     * 删除用户
     *
     * @param userId 用户标识
     */
    void delete(Long userId);

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    User getUser(Long userId);

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getAllUser();
}
