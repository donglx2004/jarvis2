package com.mogujie.jarvis.dao;

import com.mogujie.jarvis.dto.Job;
import com.mogujie.jarvis.dto.JobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int countByExample(JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int deleteByExample(JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int deleteByPrimaryKey(Integer jobId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int insert(Job record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int insertSelective(Job record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    List<Job> selectByExample(JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    Job selectByPrimaryKey(Integer jobId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int updateByPrimaryKeySelective(Job record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbggenerated Mon Aug 31 14:35:07 CST 2015
     */
    int updateByPrimaryKey(Job record);
}