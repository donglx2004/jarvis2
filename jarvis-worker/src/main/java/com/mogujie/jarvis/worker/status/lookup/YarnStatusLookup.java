/*
 * 蘑菇街 Inc.
 * Copyright (c) 2010-2016 All Rights Reserved.
 *
 * Author: wuya
 * Create Date: 2016年1月6日 下午5:06:51
 */

package com.mogujie.jarvis.worker.status.lookup;

import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Lists;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mogujie.jarvis.core.domain.TaskDetail;
import com.mogujie.jarvis.core.domain.TaskStatus;
import com.mogujie.jarvis.worker.status.TaskStatusLookup;

public abstract class YarnStatusLookup implements TaskStatusLookup {

    private int activeUriIndex = 0;
    private List<Object> yarnRestApiUris = null;
    private static final String YARN_STATUS_LOOPUP_REST_URIS = "yarn.status.loopup.rest.uris";
    private static final Logger LOGGER = LogManager.getLogger();

    abstract public boolean accept(TaskDetail taskDetail, String appName);

    @Override
    public void init(Configuration conf) {
        yarnRestApiUris = conf.getList(YARN_STATUS_LOOPUP_REST_URIS);
        if (yarnRestApiUris == null || yarnRestApiUris.size() < 1) {
            LOGGER.error("The value of " + YARN_STATUS_LOOPUP_REST_URIS + " is invalid");
        }
    }

    @Override
    public int lookup(TaskDetail taskDetail) {
        for (int i = 0, len = yarnRestApiUris.size(); i < len; i++) {
            List<Integer> statusList = Lists.newArrayList();
            try {
                HttpResponse<JsonNode> response = Unirest.get(yarnRestApiUris.get(activeUriIndex).toString()).asJson();
                JSONArray appsJson = response.getBody().getObject().getJSONObject("apps").getJSONArray("app");
                for (int j = 0, jlen = appsJson.length(); j < jlen; j++) {
                    JSONObject appJson = appsJson.getJSONObject(j);
                    String appName = appJson.getString("name");
                    if (accept(taskDetail, appName)) {
                        String finalStatus = appJson.getString("finalStatus");
                        switch (finalStatus) {
                            case "SUCCEEDED":
                                statusList.add(TaskStatus.SUCCESS.getValue());
                                break;
                            case "FAILED":
                                statusList.add(TaskStatus.FAILED.getValue());
                                break;
                            case "KILLED":
                                statusList.add(TaskStatus.KILLED.getValue());
                                break;
                            case "RUNNING":
                                statusList.add(TaskStatus.RUNNING.getValue());
                                break;
                            default:
                                statusList.add(TaskStatus.UNKNOWN.getValue());
                                break;
                        }
                    }
                }

                boolean allSuccess = true;
                for (int status : statusList) {
                    if (status != TaskStatus.SUCCESS.getValue()) {
                        allSuccess = false;
                        if (status != TaskStatus.UNKNOWN.getValue()) {
                            return status;
                        }
                        break;
                    }
                }

                if (allSuccess) {
                    return TaskStatus.SUCCESS.getValue();
                } else {
                    return TaskStatus.UNKNOWN.getValue();
                }
            } catch (UnirestException | JSONException e) {
                activeUriIndex = ++activeUriIndex % len;
            }
        }

        return TaskStatus.UNKNOWN.getValue();
    }

    @Override
    public void close() {
        // ignore
    }

}
