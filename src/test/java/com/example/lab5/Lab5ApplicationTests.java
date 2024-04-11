package com.example.testingweb;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.lab5.*;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;


@ContextConfiguration(classes = Lab5Application.class)
@WebMvcTest(TvController.class)
class TestingWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TvService tvService;


    private List<TvEntity> getTv(){
        TvEntity tvEntity = new TvEntity(0,"Panasonic","LCD","TH-55HX750M",55,70500,5);
        TvEntity tvEntity1 = new TvEntity(1,"Sony","LCD","TH-55HX750MW",552,705002,51);
        return List.of(tvEntity, tvEntity1);
    }


    @Test
    void findAllShouldReturnAllItems() throws Exception {
        Mockito.when(this.tvService.getAllTv()).thenReturn(getTv());
        mockMvc.perform(get("/tv"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Panasonic")))
                .andExpect(content().string(containsString("LCD")))
                .andExpect(content().string(containsString("TH-55HX750M")))
                .andExpect(content().string(containsString("5")));
    }

    @Test
    void findAllNotExist() throws Exception{
        Mockito.when(this.tvService.getAllTv()).thenReturn(getTv());
        mockMvc.perform(get("/tv"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sony")));
    }
    @Test void checkDeleteStatus() throws Exception{
        mockMvc.perform(get("/delete"))
                .andDo(print())
                .andExpect(status().isOk());
    }




}