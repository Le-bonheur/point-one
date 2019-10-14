package com.ssc.one.controller;

import com.ssc.one.service.UserService;
import com.ssc.one.service.UsersAtsService;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * user控制器，包含参数校验
 *
 * @author Lebonheur
 * @version v1.0
 */
@Controller
@RequestMapping(path = "/point-one")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    private final UsersAtsService usersAtsService;

    @Autowired
    public UserController(UserService userService, UsersAtsService usersAtsService) {
        this.userService = userService;
        this.usersAtsService = usersAtsService;
    }

    /**
     * 记录@用户
     *
     * @param userID 用户id
     * @param userAt 被@用户id集合
     * @return string
     */
    @ResponseBody
    @PostMapping("/postWeibo")
    public String postWeibo(@RequestParam(value = "userID") String userID,
                            @RequestParam(value = "userAt") List<String> userAt) {
        if(Strings.isEmpty(userID) || userID.length() != 10) {
            logger.error("用户ID数据非法");
            return null;
        }
        if(userAt == null || userAt.size() == 0) {
            logger.error("被@用户列表不能为空");
            return null;
        }
        for (String userAtId : userAt) {
            if(Strings.isEmpty(userAtId) || userAtId.length() != 10) {
                logger.error("被@用户ID数据非法");
                return null;
            }
        }
        return usersAtsService.saveUserAt(userID, userAt);
    }

    /**
     * 返回推荐用户ID列表
     *
     * @param userID 用户ID
     * @return 推荐列表
     */
    @ResponseBody
    @PostMapping("/suggest")
    public List<String> suggest(@RequestParam(value = "userID") String userID) {
        if(Strings.isEmpty(userID) || userID.length() != 10) {
            logger.error("用户ID数据非法");
            return null;
        }
        return userService.getUsersByUserId(userID);
    }
}
