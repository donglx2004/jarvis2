/*
 * 蘑菇街 Inc. 
 * Copyright (c) 2010-2015 All Rights Reserved.
 *
 * Author: wuya
 * Create Date: 2015年10月9日 下午5:14:53
 */

package com.mogujie.jarvis.server.actor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.guice.transactional.Transactional;

import java.util.ArrayList;
import java.util.List;

import akka.actor.Props;
import akka.actor.UntypedActor;

import com.mogujie.jarvis.core.domain.MessageType;
import com.mogujie.jarvis.dto.generate.Alarm;
import com.mogujie.jarvis.protocol.AlarmProtos.RestCreateAlarmRequest;
import com.mogujie.jarvis.protocol.AlarmProtos.ServerCreateAlarmResponse;
import com.mogujie.jarvis.protocol.AlarmProtos.RestModifyAlarmRequest;
import com.mogujie.jarvis.protocol.AlarmProtos.ServerModifyAlarmResponse;
import com.mogujie.jarvis.protocol.AlarmProtos.RestDeleteAlarmRequest;
import com.mogujie.jarvis.protocol.AlarmProtos.ServerDeleteAlarmResponse;
import com.mogujie.jarvis.server.domain.ActorEntry;
import com.mogujie.jarvis.server.guice.Injectors;
import com.mogujie.jarvis.server.service.AlarmService;
import com.mogujie.jarvis.server.service.ConvertValidService;

public class AlarmActor extends UntypedActor {

    private ConvertValidService convertValidService = Injectors.getInjector().getInstance(ConvertValidService.class);
    private AlarmService alarmService = Injectors.getInjector().getInstance(AlarmService.class);
    private Logger logger = LogManager.getLogger();

    public static Props props() {
        return Props.create(AlarmActor.class);
    }

    public static List<ActorEntry> handledMessages() {
        List<ActorEntry> list = new ArrayList<>();
        list.add(new ActorEntry(RestCreateAlarmRequest.class, ServerCreateAlarmResponse.class, MessageType.SYSTEM));
        list.add(new ActorEntry(RestModifyAlarmRequest.class, ServerModifyAlarmResponse.class, MessageType.SYSTEM));
        list.add(new ActorEntry(RestDeleteAlarmRequest.class, ServerDeleteAlarmResponse.class, MessageType.SYSTEM));
        return list;
    }

    @Override
    public void onReceive(Object obj) throws Exception {
        if (obj instanceof RestCreateAlarmRequest) {
            createAlarm((RestCreateAlarmRequest) obj);
        } else if (obj instanceof RestModifyAlarmRequest) {
            modifyAlarm((RestModifyAlarmRequest) obj);
        } else if (obj instanceof RestDeleteAlarmRequest) {
            deleteAlarm((RestDeleteAlarmRequest) obj);
        } else {
            unhandled(obj);
        }
    }

    @Transactional
    private void createAlarm(RestCreateAlarmRequest request) {
        ServerCreateAlarmResponse response;
        try {
            Alarm alarm = convertValidService.convert2AlarmByCheck(request);
            alarmService.insert(alarm);
            response = ServerCreateAlarmResponse.newBuilder().setSuccess(true).build();
            getSender().tell(response, getSelf());
        } catch (Exception ex) {
            response = ServerCreateAlarmResponse.newBuilder().setSuccess(false).setMessage(ex.getMessage()).build();
            getSender().tell(response, getSelf());
            logger.error("", ex);
            throw ex;
        }
    }

    @Transactional
    private void modifyAlarm(RestModifyAlarmRequest request) {
        ServerModifyAlarmResponse response;
        try {
            Alarm alarm = convertValidService.convert2AlarmByCheck(request);
            alarmService.upsetByJobId(alarm);
            response = ServerModifyAlarmResponse.newBuilder().setSuccess(true).build();
            getSender().tell(response, getSelf());
        } catch (Exception ex) {
            response = ServerModifyAlarmResponse.newBuilder().setSuccess(false).setMessage(ex.getMessage()).build();
            getSender().tell(response, getSelf());
            logger.error("", ex);
            throw ex;
        }
    }

    @Transactional
    private void deleteAlarm(RestDeleteAlarmRequest request) {
        ServerDeleteAlarmResponse response;
        try {
            Alarm alarm = convertValidService.convert2AlarmByCheck(request);
            alarmService.deleteByJobId(alarm.getJobId());
            response = ServerDeleteAlarmResponse.newBuilder().setSuccess(true).build();
            getSender().tell(response, getSelf());
        } catch (Exception ex) {
            response = ServerDeleteAlarmResponse.newBuilder().setSuccess(false).setMessage(ex.getMessage()).build();
            getSender().tell(response, getSelf());
            logger.error("", ex);
            throw ex;
        }
    }


}