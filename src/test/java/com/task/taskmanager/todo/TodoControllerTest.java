package com.task.taskmanager.todo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TodoControllerTest {

    Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .setLenient()
            .setPrettyPrinting().create();
    String pattern = "yyyyMMdd";
    DateFormat df = new SimpleDateFormat(pattern);

    @Autowired
    private MockMvc mvc;

    @Test
    public void create() throws Exception{
        Todo transaction = new Todo("1", "my first task");
        String json = gson.toJson(transaction);
        this.mvc.perform(post("/todo/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void update() throws Exception{
        Todo transaction = new Todo("2", "my second task");
        String json = gson.toJson(transaction);
        this.mvc.perform(put("/todo/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void selectSpecific() throws Exception {
        Todo transaction = new Todo("3", "third");
        String json = gson.toJson(transaction);
        this.mvc.perform(post("/todo/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andDo(print());
        this.mvc.perform(get("/todo/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andDo(print());
    }

    @Test
    public void selectAll() throws Exception {
        this.mvc.perform(get("/todo/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andDo(print());
    }

    @Test
    public void deleteSpecific()throws Exception {
        Todo transaction = new Todo("4", "forth");
        String json = gson.toJson(transaction);
        this.mvc.perform(post("/todo/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andDo(print());
        MvcResult mr = this.mvc.perform(delete("/todo/4").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andReturn();
        Todo todo = gson.fromJson(mr.getResponse().getContentAsString(), Todo.class);
        Assert.assertNull(todo);
    }
}