package com.warehouse.controller;

import com.warehouse.Controller.SocksController;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(SocksController.class)
public class SocksControllerTest {
    @Autowired
    private MockMvc mockMvc;
    String color;
    int cottonPart;
    int quantity;
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


//    @Test
//    void getSocksTest() throws Exception {
//        String url = "/api/socks/red/moreThan/30";
//
//        mockMvc.perform(get(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//    }
//    @Test
//    void addSocksTest() throws Exception {
//        String url = "/api/socks/income";
//        mockMvc.perform(MockMvcRequestBuilders.patch(
//                url)
//                .content(socksJSON.toString())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//    @Test
//    void updateSocksTest() throws Exception {
//        String url = "/api/socks/income";
//        mockMvc.perform(MockMvcRequestBuilders.patch(
//                                url)
//                        .content(socksJSON.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.quantity").value(0));
//    }
}
