/*
 * 蘑菇街 Inc. Copyright (c) 2010-2015 All Rights Reserved.
 *
 * Author: guangming Create Date: 2015年9月29日 下午4:42:28
 */

package com.mogujie.jarvis.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.common.base.Preconditions;

import java.util.Date;

import com.mogujie.jarvis.core.domain.*;
import org.joda.time.DateTime;
import com.mogujie.jarvis.core.expression.*;
import com.mogujie.jarvis.core.util.ExpressionUtils;
import com.mogujie.jarvis.core.JarvisConstants;

import com.mogujie.jarvis.dto.generate.Alarm;
import com.mogujie.jarvis.dto.generate.App;
import com.mogujie.jarvis.dto.generate.Job;
import com.mogujie.jarvis.dto.generate.JobDepend;
import com.mogujie.jarvis.protocol.AppAuthProtos.AppAuth;
import com.mogujie.jarvis.protocol.DependencyEntryProtos.DependencyEntry;
import com.mogujie.jarvis.protocol.ScheduleExpressionEntryProtos.ScheduleExpressionEntry;
import com.mogujie.jarvis.protocol.JobProtos.RestSubmitJobRequest;
import com.mogujie.jarvis.protocol.JobProtos.RestModifyJobRequest;
import com.mogujie.jarvis.protocol.JobProtos.RestModifyJobScheduleExpRequest;
import com.mogujie.jarvis.protocol.JobProtos.RestModifyJobDependRequest;
import com.mogujie.jarvis.protocol.JobProtos.RestModifyJobStatusRequest;
import com.mogujie.jarvis.protocol.AlarmProtos.RestCreateAlarmRequest;
import com.mogujie.jarvis.protocol.AlarmProtos.RestModifyAlarmRequest;
import com.mogujie.jarvis.protocol.AlarmProtos.RestDeleteAlarmRequest;
import com.mogujie.jarvis.server.domain.JobEntry;
import com.mogujie.jarvis.server.domain.CommonStrategy;

/**
 * @author guangming, muming
 */

@Singleton
public class ConvertValidService {

    private enum CheckMode {
        ADD, //追加
        EDIT, //修改
        EDIT_STATUS, //修改_状态
        DELETE; //删除

        /**
         * 是否在scope中
         *
         * @param scope
         * @return
         */
        public Boolean isIn(CheckMode... scope) {

            for (CheckMode member : scope) {
                if (ordinal() == member.ordinal()) {
                    return true;
                }
            }
            return false;
        }

    }

    @Inject
    private AppService appService;
    @Inject
    private JobService jobService;
    @Inject
    private AlarmService alarmService;


    //--------------------------------------- job ---------------------------------

    public Job convert2JobByCheck(RestSubmitJobRequest msg) {
        Job job = msg2Job(msg);
        checkJob(CheckMode.ADD, job);
        return job;
    }

    public Job convert2JobByCheck(RestModifyJobRequest msg) {
        Job job = msg2Job(msg);
        checkJob(CheckMode.EDIT, job);
        return job;
    }

    public Job convert2JobByCheck(RestModifyJobStatusRequest msg) {
        Job job = msg2Job(msg);
        checkJob(CheckMode.EDIT_STATUS, job);
        return job;
    }

    /**
     * @param job
     */
    private void checkJob(CheckMode mode, Job job) {

        //为空检查
        if (mode == CheckMode.ADD) {
            Preconditions.checkNotNull(job.getJobName(), "jobName不能为空");
            Preconditions.checkNotNull(job.getWorkerGroupId(), "workGroupId不能为空");
            Preconditions.checkNotNull(job.getContent(), "job内容不能为空");
            Preconditions.checkNotNull(job.getStatus(), "status不能为空");
        }

        Job oldJob = null;
        if (mode == CheckMode.EDIT || mode == CheckMode.EDIT_STATUS) {
            Preconditions.checkArgument(job.getJobId() != null && job.getJobId() != 0, "jobId不能为空");
            JobEntry oldJobEntry = jobService.get(job.getJobId());
            Preconditions.checkNotNull(oldJobEntry, "job不存在");
            oldJob = oldJobEntry.getJob();

            if (mode == CheckMode.EDIT_STATUS) {
                Preconditions.checkNotNull(job.getStatus(), "status不能为空");
            }
        }

        //内容检查
        Preconditions.checkArgument(job.getJobName() == null || !job.getJobName().isEmpty(), "jobName不能为空");
        Preconditions.checkArgument(job.getWorkerGroupId() == null || job.getWorkerGroupId() > 0, "workGroupId不能为空");
        Preconditions.checkArgument(job.getContent() == null || !job.getContent().isEmpty(), "job内容不能为空");
        Preconditions.checkArgument(job.getStatus() == null || JobStatus.isValid(job.getStatus()), "status内容不正确。value:" + job.getStatus());
        if (job.getActiveStartDate() != null && job.getActiveEndDate() != null) {
            Preconditions.checkArgument(job.getActiveStartDate().getTime() <= job.getActiveEndDate().getTime(), "有效开始日不能大于有效结束日");
        } else if (job.getActiveStartDate() != null && job.getActiveEndDate() == null) {
            Preconditions.checkArgument(job.getActiveStartDate().getTime() <= oldJob.getActiveEndDate().getTime(), "有效开始日不能大于有效结束日");
        } else if (job.getActiveStartDate() == null && job.getActiveEndDate() != null) {
            Preconditions.checkArgument(oldJob.getActiveStartDate().getTime() <= job.getActiveEndDate().getTime(), "有效开始日不能大于有效结束日");
        }

    }

    private Job msg2Job(RestSubmitJobRequest msg) {
        Job job = new Job();
        job.setAppId(analysisAppId(msg.getAppAuth(), msg.getAppName()));
        job.setJobName(msg.getJobName());
        job.setContent(msg.getContent());
        job.setParams(msg.getParameters());
        job.setPriority(msg.getPriority());
        job.setStatus(msg.getStatus());
        job.setJobType(msg.getJobType());
        job.setWorkerGroupId(msg.getWorkerGroupId());
        if (msg.hasActiveStartTime() && msg.getActiveStartTime() != 0) {
            job.setActiveStartDate(new DateTime(msg.getActiveStartTime()).toDate());
        } else {
            job.setActiveStartDate(JarvisConstants.DATETIME_MIN.toDate());
        }
        if (msg.hasActiveEndTime() && msg.getActiveEndTime() != 0) {
            job.setActiveEndDate(new DateTime(msg.getActiveEndTime()).toDate());
        } else {
            job.setActiveEndDate(JarvisConstants.DATETIME_MAX.toDate());
        }

        job.setExpiredTime(msg.getExpiredTime());
        job.setFailedAttempts(msg.getFailedAttempts());
        job.setFailedInterval(msg.getFailedInterval());
        job.setSubmitUser(msg.getUser());
        job.setUpdateUser(msg.getUser());

        DateTime now = DateTime.now();
        job.setCreateTime(now.toDate());
        job.setUpdateTime(now.toDate());

        return job;
    }

    private Job msg2Job(RestModifyJobRequest msg) {
        Job job = new Job();
        job.setJobId(msg.getJobId());
        job.setAppId(analysisAppId(msg.getAppAuth(), msg.getAppName()));
        if (msg.hasJobName()) {
            job.setJobName(msg.getJobName());
        }
        if (msg.hasJobType()) {
            job.setJobType(msg.getJobType());
        }
        if (msg.hasContent()) {
            job.setContent(msg.getContent());
        }
        if (msg.hasParameters()) {
            job.setParams(msg.getParameters());
        }
        if (msg.hasWorkerGroupId()) {
            job.setWorkerGroupId(msg.getWorkerGroupId());
        }
        if (msg.hasPriority()) {
            job.setPriority(msg.getPriority());
        }
        if (msg.hasActiveStartTime()) {
            job.setActiveStartDate(new Date(msg.getActiveStartTime()));
        }
        if (msg.hasActiveEndTime()) {
            job.setActiveEndDate(new Date(msg.getActiveEndTime()));
        }
        if (msg.hasExpiredTime()) {
            job.setExpiredTime(msg.getExpiredTime());
        }
        if (msg.hasFailedAttempts()) {
            job.setFailedAttempts(msg.getFailedAttempts());
        }
        if (msg.hasFailedInterval()) {
            job.setFailedInterval(msg.getFailedInterval());
        }

        job.setUpdateUser(msg.getUser());
        Date currentTime = DateTime.now().toDate();
        job.setUpdateTime(currentTime);
        return job;
    }

    public Job msg2Job(RestModifyJobStatusRequest msg) {
        Job job = new Job();
        job.setJobId(msg.getJobId());
        job.setStatus(msg.getStatus());
        return job;
    }

    //--------------------------------------- job 依赖 ---------------------------------

    /**
     * 转化为_jobDepend
     *
     * @param jobId
     * @param user
     * @param entry
     * @param time
     * @return
     */
    public JobDepend convert2JobDepend(Long jobId, DependencyEntry entry, String user, DateTime time) {
        JobDepend jobDepend = new JobDepend();
        jobDepend.setJobId(jobId);
        jobDepend.setPreJobId(entry.getJobId());
        jobDepend.setCommonStrategy(entry.getCommonDependStrategy());
        jobDepend.setOffsetStrategy(entry.getOffsetDependStrategy());
        jobDepend.setUpdateTime(time.toDate());
        jobDepend.setUpdateUser(user);

        if (entry.getOperator() == OperationMode.ADD.getValue()) {
            jobDepend.setCreateTime(time.toDate());
        }
        return jobDepend;
    }

    /**
     * 检查-job依赖
     *
     * @param msg
     * @return
     */
    public void CheckJobDependency(RestModifyJobDependRequest msg) throws IllegalArgumentException {

        Preconditions.checkArgument(msg.getDependencyEntryList() != null && !msg.getDependencyEntryList().isEmpty()
                , "依赖对象不能为空");

        long jobId = msg.getJobId();
        JobEntry job = jobService.get(jobId);
        Preconditions.checkArgument(job != null, "jobId对象不存在");

        for (DependencyEntry entry : msg.getDependencyEntryList()) {

            int mode = entry.getOperator();
            Preconditions.checkArgument(OperationMode.isValid(mode), "操作模式不对");

            long preJobId = entry.getJobId();
            Preconditions.checkArgument(preJobId != 0, "依赖JobId不能为空");

            if (mode == OperationMode.ADD.getValue() || mode == OperationMode.EDIT.getValue()) {
                int commonStrategy = entry.getCommonDependStrategy();
                Preconditions.checkArgument(CommonStrategy.isValid(commonStrategy), "依赖的通用策略不对");

                //偏移策略可以为空，表示runtime模式。
                String offsetStrategy = entry.getOffsetDependStrategy();
                if (offsetStrategy == null || offsetStrategy.equals("")) {
                    // TODO: 16/1/8
                } else {
                    Preconditions.checkArgument(new TimeOffsetExpression(offsetStrategy).isValid(), "依赖的偏移策略不对");
                }
            }
        }
    }

    //--------------------------------------- job 计划表达式 ---------------------------------

    /**
     * 检查-job计划表达式
     *
     * @param msg
     * @return
     */
    public void Check2JobScheduleExp(RestModifyJobScheduleExpRequest msg) {

        Preconditions.checkArgument(msg.getExpressionEntryList() != null && !msg.getExpressionEntryList().isEmpty()
                , "计划表达式不能为空");

        long jobId = msg.getJobId();
        JobEntry job = jobService.get(jobId);
        Preconditions.checkArgument(job != null, "jobId对象不存在");

        for (ScheduleExpressionEntry entry : msg.getExpressionEntryList()) {
            int mode = entry.getOperator();
            Preconditions.checkArgument(OperationMode.isValid(mode), "操作模式不对. mode:" + mode);

            //追加与新建模式做检查,删除模式不做检查
            if (mode == OperationMode.ADD.getValue() || mode == OperationMode.EDIT.getValue()) {
                ExpressionUtils.checkExpression(entry.getExpressionType(), entry.getScheduleExpression());
            }

        }
    }

    //------------------------ Alarm ----------------------

    public Alarm convert2AlarmByCheck(RestCreateAlarmRequest msg) {
        Alarm alarm = msg2Alarm(msg);
        checkAlarm(CheckMode.ADD, alarm);
        return alarm;
    }

    public Alarm convert2AlarmByCheck(RestModifyAlarmRequest msg) {
        Alarm alarm = msg2Alarm(msg);
        checkAlarm(CheckMode.EDIT, alarm);
        return alarm;
    }

    public Alarm convert2AlarmByCheck(RestDeleteAlarmRequest msg) {
        Alarm alarm = msg2Alarm(msg);
        checkAlarm(CheckMode.DELETE, alarm);
        return alarm;
    }

    /**
     * @param mode
     * @param alarm
     */
    private void checkAlarm(CheckMode mode, Alarm alarm) {

        Long jobId = alarm.getJobId();
        Preconditions.checkArgument(!mode.isIn(CheckMode.ADD, CheckMode.EDIT, CheckMode.DELETE)
                || (jobId != null && jobId != 0), "jobId不能为空。");

        if (mode.isIn(CheckMode.ADD, CheckMode.EDIT)) {
            JobEntry job = jobService.get(jobId);
            Preconditions.checkNotNull(job, "job对象不存在。jobId:" + jobId);
        }

        if(mode.isIn(CheckMode.ADD)){
            Alarm  cur = alarmService.getAlarmByJobId(jobId);
            Preconditions.checkArgument(cur == null,"alarm对象已经存在,不能增加。jobId:" + jobId);
        }

        String type = alarm.getAlarmType();
        Preconditions.checkArgument(!mode.isIn(CheckMode.ADD) || type != null, "alarmType不能为空。");
        Preconditions.checkArgument(type == null || AlarmType.isValid(type), "alarmType不对。 value:" + type);

        Preconditions.checkArgument(!mode.isIn(CheckMode.ADD) || alarm.getReceiver() != null, "receiver不能为空。");

        Integer status = alarm.getStatus();
        Preconditions.checkArgument(!mode.isIn(CheckMode.ADD) || status != null, "status不能为空。");
        Preconditions.checkArgument(status == null || AlarmStatus.isValid(status), "status类型不对。value:" + status);

    }


    private Alarm msg2Alarm(RestCreateAlarmRequest msg) {
        DateTime now = DateTime.now();
        Alarm alarm = new Alarm();
        alarm.setJobId(msg.getJobId());
        alarm.setAlarmType(msg.getAlarmType());
        alarm.setReceiver(msg.getReciever());
        alarm.setStatus(msg.getStatus());

        alarm.setCreateTime(now.toDate());
        alarm.setUpdateTime(now.toDate());
        alarm.setUpdateUser(msg.getUser());
        return alarm;
    }

    private Alarm msg2Alarm(RestModifyAlarmRequest msg) {
        DateTime now = DateTime.now();
        Alarm alarm = new Alarm();
        alarm.setJobId(msg.getJobId());
        if (msg.hasAlarmType()) {
            alarm.setAlarmType(msg.getAlarmType());
        }
        if (msg.hasReciever()) {
            alarm.setReceiver(msg.getReciever());
        }
        if (msg.hasStatus()) {
            alarm.setStatus(msg.getStatus());
        }
        alarm.setUpdateTime(now.toDate());
        alarm.setUpdateUser(msg.getUser());
        return alarm;
    }

    private Alarm msg2Alarm(RestDeleteAlarmRequest msg) {
        Alarm alarm = new Alarm();
        alarm.setJobId(msg.getJobId());
        return alarm;
    }


    //------------------------ 其他 ----------------------

    /**
     * 分析获得appId
     *
     * @param appAuth
     * @param appName
     * @return
     */
    private int analysisAppId(AppAuth appAuth, String appName) {
        int appId;
        App app = appService.getAppByName(appAuth.getName());
        if (app.getAppType() == AppType.NORMAL.getValue()) {
            appId = app.getAppId();
        } else {
            if (appName != null) {
                appId = appService.getAppIdByName(appName);
            } else {
                appId = app.getAppId();
            }
        }
        return appId;
    }

}
