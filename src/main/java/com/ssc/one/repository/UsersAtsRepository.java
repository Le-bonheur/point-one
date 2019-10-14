package com.ssc.one.repository;

import com.ssc.one.entity.UsersAts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Lebonheur
 * @version v1.0
 */
public interface UsersAtsRepository extends CrudRepository<UsersAts, Long> {

    /**
     * 按userId查找@过的用户
     *
     * @param userIDAt 被@的用户
     * @return 用户列表
     */
    List<UsersAts> getUserIdAtsByUserIdAt(String userIDAt);

    /**
     * 查找365天内其他被别的ID@过的人，最多返回500条
     *
     * @param userIDAt 被@的用户
     * @return 用户列表
     */
    @Query(value = "  SELECT * FROM users_ats u "
                 + "   WHERE u.user_id IN "
                 + " (SELECT u_sub.user_id FROM users_ats u_sub "
                 + "   WHERE u_sub.user_id_at = :userIDAt) "
                 + "     AND TO_DAYS(NOW()) - TO_DAYS(u.create_time) <= 365 "
                 + "   LIMIT 500 ", nativeQuery = true)
    List<UsersAts> getUserIdAts(@Param(value = "userIDAt") String userIDAt);

}
