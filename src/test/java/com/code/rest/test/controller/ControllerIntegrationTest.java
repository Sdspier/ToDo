package com.code.rest.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.mock.web.MockHttpServletResponse;

@AutoConfigureMockMvc
public class ControllerIntegrationTest {

    public MockHttpServletResponse lastPostResponse;
    public int lastStatusCode;

    @Autowired
    public MockMvc mvc;

    protected ResultActions get(String url, Object... urlVariables) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(url, urlVariables).accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions post(String url, String content, Object... urlVariables) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(url, urlVariables)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(result -> {
                    lastPostResponse = result.getResponse();
                    lastStatusCode = lastPostResponse.getStatus();
                });
    }

    public ResultActions delete(String url, Object... urlVariables) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.delete(url, urlVariables).accept(MediaType.APPLICATION_JSON));
    }

    public ResultActions put(String url, Object content, Object... urlVariables) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.put(url, urlVariables)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(content))
        );
    }

    public static String toJsonString(final Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
