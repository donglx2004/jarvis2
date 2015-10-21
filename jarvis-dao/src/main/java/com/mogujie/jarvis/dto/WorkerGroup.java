package com.mogujie.jarvis.dto;

import java.io.Serializable;
import java.util.Date;

public class WorkerGroup implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column worker_group.id
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column worker_group.name
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column worker_group.authKey
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    private String authKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column worker_group.status
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column worker_group.createTime
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column worker_group.updateTime
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column worker_group.updateUser
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table worker_group
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column worker_group.id
     *
     * @return the value of worker_group.id
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column worker_group.id
     *
     * @param id the value for worker_group.id
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column worker_group.name
     *
     * @return the value of worker_group.name
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column worker_group.name
     *
     * @param name the value for worker_group.name
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column worker_group.authKey
     *
     * @return the value of worker_group.authKey
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public String getAuthKey() {
        return authKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column worker_group.authKey
     *
     * @param authKey the value for worker_group.authKey
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column worker_group.status
     *
     * @return the value of worker_group.status
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column worker_group.status
     *
     * @param status the value for worker_group.status
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column worker_group.createTime
     *
     * @return the value of worker_group.createTime
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column worker_group.createTime
     *
     * @param createTime the value for worker_group.createTime
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column worker_group.updateTime
     *
     * @return the value of worker_group.updateTime
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column worker_group.updateTime
     *
     * @param updateTime the value for worker_group.updateTime
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column worker_group.updateUser
     *
     * @return the value of worker_group.updateUser
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column worker_group.updateUser
     *
     * @param updateUser the value for worker_group.updateUser
     *
     * @mbggenerated Wed Oct 21 10:58:49 CST 2015
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}