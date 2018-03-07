package com.mycompany.sspier.code.rest.test.unit;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.sspier.code.rest.Application;

@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public abstract class AbstractFrame {

    private static ObjectMapper mapper = new ObjectMapper();

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

    /*protected <T> T getLastPostContentAs(TypeReference<T> type) throws Exception {
        return deserializeResponse(getLastPostResponse(), type);
    }

    private static <T> T deserializeResponse(MockHttpServletResponse response, TypeReference<T> type) throws Exception {
        return deserialize(response.getContentAsString(), type);
    }
*/
    protected MockHttpServletResponse getLastPostResponse() {
        return lastPostResponse;
    }
/*
    protected static <T> T deserialize(String json, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(json, type);
    }

    protected static <T> T deserialize(String json, TypeReference<T> type) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(json, type);
    }
*/
    public int getLastStatusCode() {
        return lastStatusCode;
    }
}
