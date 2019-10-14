package com.ssc.one.repository;

import com.ssc.one.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Lebonheur
 * @version v1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * 按userID查询用户
     *
     * @param userID 用户ID
     * @return User对象
     */
    User getUserByUserId(@Param(value = "userID") String userID);

    /**
     * 按userID查询用户（批量）
     *
     * @param userIdList 用户ID列表
     * @return User对象列表
     */
    List<User> getUsersByUserIdIn(List<String> userIdList);
}
