package com.task.taskmanager.metric;

import org.springframework.data.annotation.Id;

import java.util.List;

public class MetricCount {
    private String method;
    private String route;
    private Integer total;
    private Integer time;
    private double average;
}
