package com.ssc.one.service;

import com.ssc.one.entity.UsersAts;
import com.ssc.one.repository.UsersAtsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户@关系service
 *
 * @author Lebonheur
 * @version v1.0
 */
@Service
public class UsersAtsService {

    private final UsersAtsRepository usersAtsRepository;

    @Autowired
    public UsersAtsService(UsersAtsRepository usersAtsRepository) {
        this.usersAtsRepository = usersAtsRepository;
    }

    /**
     * 持久化@用户
     *
     * @param userId   用户ID
     * @param userIdAt 被@的用户ID集合
     * @return String
     */
    @Transactional(rollbackFor = Exception.class)
    public String saveUserAt(String userId, List<String> userIdAt) {
        //获取时间nano值作为tweetId
        int nano = LocalDateTime.now().getNano();

        List<UsersAts> usersAtsList = new ArrayList<>();

        userIdAt.forEach(item -> {
            UsersAts usersAtsDb = new UsersAts(
                    String.valueOf(nano),
                    userId,
                    item,
                    Date.from(Instant.now())
            );

            usersAtsList.add(usersAtsDb);
        });

        usersAtsRepository.saveAll(usersAtsList);

        return "success";
    }

}
