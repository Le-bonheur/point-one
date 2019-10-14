package com.ssc.one.service;

import com.ssc.one.entity.UsersAts;
import com.ssc.one.repository.UsersAtsRepository;
import com.ssc.one.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户service类
 *
 * @author Lebonheur
 * @version v1.0
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UsersAtsRepository usersAtsRepository;

    @Autowired
    public UserService(UserRepository userRepository, UsersAtsRepository usersAtsRepository) {
        this.userRepository = userRepository;
        this.usersAtsRepository = usersAtsRepository;
    }

    /**
     * 根据用户ID返回推荐的列表
     *
     * @param userId 用户ID
     * @return 推荐列表
     */
    public List<String> getUsersByUserId(String userId) {
        //获取其他被别的用户@过的用户
        List<UsersAts> usersAtsList = usersAtsRepository.getUserIdAts(userId);

        List<UsersAts> atWithMeList = new ArrayList<>();
        List<UsersAts> atWithoutMeList = new ArrayList<>();

        //按推文ID分组，map的key为tweetId,value为对象集合
        Map<String, List<UsersAts>> stringListMap = usersAtsList.stream().collect(Collectors.groupingBy(UsersAts::getTweetId));
        stringListMap.forEach((tweetId, list) -> {
            if(list.size() > 1 && list.stream().anyMatch(user -> user.getUserIdAt().equals(userId))) {
                //如果@该用户同时也@了其他用户，则放到一个list里
                atWithMeList.addAll(list);
            } else {
                //只@了其他用户的放到另一个list
                atWithoutMeList.addAll(list);
            }
        });

        //把@了该用户的list按日期排序
        List<UsersAts> usersAts = atWithMeList.stream()
                .sorted(Comparator.comparing(UsersAts::getCreateTime).reversed())
                .collect(Collectors.toList());

        //把没@该用户的list按日期排序并加到@了该用户的list中
        usersAts.addAll(atWithoutMeList.stream()
                .sorted(Comparator.comparing(UsersAts::getCreateTime).reversed())
                .collect(Collectors.toList()));

        //过滤自己的ID，生成ID列表，并去重
        return usersAts.stream().filter(item -> !item.getUserIdAt().equals(userId))
                .map(UsersAts::getUserIdAt)
                .distinct()
                .collect(Collectors.toList());
    }

}
