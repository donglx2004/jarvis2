package com.mogujie.jarvis.dto;

import java.util.Date;

public class Crontab {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column crontab.cronId
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    private Integer cronId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column crontab.jobId
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    private Long jobId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column crontab.cronType
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    private Integer cronType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column crontab.cronExpression
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    private String cronExpression;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column crontab.createTime
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column crontab.updateTime
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column crontab.cronId
     *
     * @return the value of crontab.cronId
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public Integer getCronId() {
        return cronId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column crontab.cronId
     *
     * @param cronId the value for crontab.cronId
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public void setCronId(Integer cronId) {
        this.cronId = cronId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column crontab.jobId
     *
     * @return the value of crontab.jobId
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column crontab.jobId
     *
     * @param jobId the value for crontab.jobId
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column crontab.cronType
     *
     * @return the value of crontab.cronType
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public Integer getCronType() {
        return cronType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column crontab.cronType
     *
     * @param cronType the value for crontab.cronType
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public void setCronType(Integer cronType) {
        this.cronType = cronType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column crontab.cronExpression
     *
     * @return the value of crontab.cronExpression
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column crontab.cronExpression
     *
     * @param cronExpression the value for crontab.cronExpression
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column crontab.createTime
     *
     * @return the value of crontab.createTime
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column crontab.createTime
     *
     * @param createTime the value for crontab.createTime
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column crontab.updateTime
     *
     * @return the value of crontab.updateTime
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column crontab.updateTime
     *
     * @param updateTime the value for crontab.updateTime
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}