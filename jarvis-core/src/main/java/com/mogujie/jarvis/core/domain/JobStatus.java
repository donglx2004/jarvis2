/*
 * 蘑菇街 Inc.
 * Copyright (c) 2010-2015 All Rights Reserved.
 *
 * Author: wuya
 * Create Date: 2015年6月12日 下午4:56:39
 */
package com.mogujie.jarvis.core.domain;

/**
 * @author wuya
 *
 */
public enum JobStatus {

    UNKNOWN(0), //未知
    WAITING(1), //等待（条件未满足）
    READY(2),   //准备（分发中）
    RUNNING(3), //执行中
    SUCCESS(4), //成功
    FAILED(5),  //失败
    KILLED(6);  //killed

    private int value;

    JobStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static JobStatus getInstance(int value) {
        JobStatus[] statusList = JobStatus.values();
        JobStatus status = JobStatus.UNKNOWN;
        for (JobStatus s : statusList) {
            if (s.getValue() == value) {
                status = s;
                break;
            }
        }

        return status;
    }
}
