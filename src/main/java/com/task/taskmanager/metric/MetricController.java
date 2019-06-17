package com.task.taskmanager.metric;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/metrics", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MetricController {

    @RequestMapping(path="/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public void selectAll() {
    }
}
