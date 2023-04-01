package com.warehouse.controller;

import com.warehouse.Controller.SocksController;
import com.warehouse.Service.SocksService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(SocksController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocksControllerTest {
    @InjectMocks
    SocksController socksController;
     @MockBean
     SocksService socksService;
    @Autowired
    private MockMvc mockMvc;
    String color = "red";
    int cottonPart;
    Integer quantity = 100;
    JSONObject socksJSON ;

    @BeforeEach
    void before() {
    color = "red";
    cottonPart = 90;
    quantity = 45;
    socksJSON = new JSONObject();
    socksJSON.put("color",color);
    socksJSON.put("cottonPart",cottonPart);
    socksJSON.put("quantity",quantity);
    }
    @AfterEach
    void after() {
    color = null;
    cottonPart = 0;
    quantity = 0;
    socksJSON = null;
    }


    @Test
    void getSocksTest() throws Exception {
        String url = "/api/socks";

        when(socksService.getSocks(color, "moreThan", 30))
                .thenReturn(quantity);

        mockMvc.perform(get(url)
                        .param("color", color)
                        .param("operation", "moreThan")
                        .param("cottonPart", String.valueOf(30))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(quantity))
                .andExpect(status().isOk());

    }
    @Test
    void addSocksTest() throws Exception {
        String url = "/api/socks/income";
        mockMvc.perform(multipart(
                url, HttpMethod.POST)
                .content(socksJSON.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void updateSocksTest() throws Exception {
        String url = "/api/socks/income";
        mockMvc.perform(multipart(
                                url, HttpMethod.POST)
                        .content(socksJSON.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(0));
    }
}
