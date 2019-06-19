package com.task.taskmanager.metric;

import com.task.taskmanager.config.MongoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class MetricService {
    @Autowired
    MongoConfig mongoConfig;

    public List<MetricCount> selectAndGroup() throws Exception{
        Aggregation agg = newAggregation(
                group("method").count().as("total"),
                project("total").and("method").previousOperation()
        );

        //Convert the aggregation result into a List
        AggregationResults<MetricCount> groupResults = mongoConfig.mongoTemplate().aggregate(agg,Metric.class, MetricCount.class);
        List<MetricCount> result = groupResults.getMappedResults();
        return result;
    }
}
