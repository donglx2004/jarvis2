package com.mogujie.jarvis.dao;

import com.mogujie.jarvis.dto.App;
import com.mogujie.jarvis.dto.AppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int countByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int deleteByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int deleteByPrimaryKey(Integer appId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int insert(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int insertSelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    List<App> selectByExample(AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    App selectByPrimaryKey(Integer appId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int updateByExample(@Param("record") App record, @Param("example") AppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int updateByPrimaryKeySelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Wed Sep 30 09:48:07 CST 2015
     */
    int updateByPrimaryKey(App record);
}