package com.mogujie.jarvis.dao;

import com.mogujie.jarvis.dto.TaskDepend;
import com.mogujie.jarvis.dto.TaskDependExample;
import com.mogujie.jarvis.dto.TaskDependKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskDependMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int countByExample(TaskDependExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int deleteByExample(TaskDependExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int deleteByPrimaryKey(TaskDependKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int insert(TaskDepend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int insertSelective(TaskDepend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    List<TaskDepend> selectByExample(TaskDependExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    TaskDepend selectByPrimaryKey(TaskDependKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int updateByExampleSelective(@Param("record") TaskDepend record, @Param("example") TaskDependExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int updateByExample(@Param("record") TaskDepend record, @Param("example") TaskDependExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int updateByPrimaryKeySelective(TaskDepend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_depend
     *
     * @mbggenerated Mon Nov 16 16:37:21 CST 2015
     */
    int updateByPrimaryKey(TaskDepend record);
}