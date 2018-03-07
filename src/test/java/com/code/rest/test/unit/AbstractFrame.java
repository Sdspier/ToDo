package com.code.rest.test.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.code.rest.Application;

@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public abstract class AbstractFrame {

    @Autowired
    private MockMvc mvc;

    private MockHttpServletResponse lastPostResponse;
    private int lastStatusCode;

    protected void post(String url, String body, Object... urlVariables) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(url, urlVariables)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(result -> {
                    lastPostResponse = result.getResponse();
                    lastStatusCode = lastPostResponse.getStatus();
                });
    }

    protected MockHttpServletResponse getLastPostResponse() {
        return lastPostResponse;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }
}
