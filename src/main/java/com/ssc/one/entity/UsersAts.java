package com.ssc.one.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户@关系对象
 *
 * @author Lebonheur
 * @version v1.0
 */
@Entity
public class UsersAts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tweetId;

    private String userId;

    private String userIdAt;

    private Date createTime;

    public UsersAts() {
    }

    public UsersAts(String tweetId, String userId, String userIdAt, Date createTime) {
        this.tweetId = tweetId;
        this.userId = userId;
        this.userIdAt = userIdAt;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdAt() {
        return userIdAt;
    }

    public void setUserIdAt(String userIdAt) {
        this.userIdAt = userIdAt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UsersAts{" +
                "id=" + id +
                ", tweetId='" + tweetId + '\'' +
                ", userId='" + userId + '\'' +
                ", userIdAt='" + userIdAt + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
