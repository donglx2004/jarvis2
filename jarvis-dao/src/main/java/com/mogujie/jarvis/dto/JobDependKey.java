package com.mogujie.jarvis.dto;

public class JobDependKey {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * job_depend.jobId
     *
     * @mbggenerated Mon Sep 28 17:40:28 CST 2015
     */
    private Long jobId;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * job_depend.preJobId
     *
     * @mbggenerated Mon Sep 28 17:40:28 CST 2015
     */
    private Long preJobId;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column job_depend.jobId
     *
     * @return the value of job_depend.jobId
     *
     * @mbggenerated Mon Sep 28 17:40:28 CST 2015
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database
     * column job_depend.jobId
     *
     * @param jobId
     *            the value for job_depend.jobId
     *
     * @mbggenerated Mon Sep 28 17:40:28 CST 2015
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column job_depend.preJobId
     *
     * @return the value of job_depend.preJobId
     *
     * @mbggenerated Mon Sep 28 17:40:28 CST 2015
     */
    public Long getPreJobId() {
        return preJobId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database
     * column job_depend.preJobId
     *
     * @param preJobId
     *            the value for job_depend.preJobId
     *
     * @mbggenerated Mon Sep 28 17:40:28 CST 2015
     */
    public void setPreJobId(Long preJobId) {
        this.preJobId = preJobId;
    }
}