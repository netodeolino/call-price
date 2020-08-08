package com.br.callpriceapi.controller;

import com.br.callpriceapi.model.dto.CallPriceDTO;
import com.br.callpriceapi.model.entity.Call;
import com.br.callpriceapi.model.entity.Plan;
import com.br.callpriceapi.model.entity.TaxRate;
import com.br.callpriceapi.service.PlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.br.callpriceapi.util.JsonUtil.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlanController.class)
class PlanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanService planService;

    private Call callPlan30;
    private Call callPlan60;
    private Call callPlan120;
    private List<Plan> plans;

    @BeforeEach
    void setUp() {
        TaxRate taxRate = new TaxRate(1L, 1.1);

        Plan plan30 = new Plan(1L, 30, "CallPrice 30", taxRate);
        Plan plan60 = new Plan(2L, 60, "CallPrice 60", taxRate);
        Plan plan120 = new Plan(3L, 120, "CallPrice 120", taxRate);

        this.plans = Arrays.asList(plan30, plan60, plan120);
        this.callPlan30 = new Call(1L, 11, 16, 20, plan30);
        this.callPlan60 = new Call(2L, 11, 17, 80, plan60);
        this.callPlan120 = new Call(3L, 18, 11, 200, plan120);
    }

    @Test
    void findAll() throws Exception {
        when(planService.findAll()).thenReturn(plans);

        mockMvc.perform(get("/plan").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void calculatePlan30() throws Exception {
        when(planService.calculate(callPlan30))
                .thenReturn(new CallPriceDTO(00.00, 38.00));

        mockMvc.perform(post("/plan/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(callPlan30)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceWithPlan", is(00.00)))
                .andExpect(jsonPath("$.priceWithoutPlan", is(38.00)));
    }

    @Test
    void calculatePlan60() throws Exception {
        when(planService.calculate(callPlan60))
                .thenReturn(new CallPriceDTO(37.40, 136.00));

        mockMvc.perform(post("/plan/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(callPlan60)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceWithPlan", is(37.40)))
                .andExpect(jsonPath("$.priceWithoutPlan", is(136.00)));
    }

    @Test
    void calculatePlan120() throws Exception {
        when(planService.calculate(callPlan120))
                .thenReturn(new CallPriceDTO(167.20, 380.00));

        mockMvc.perform(post("/plan/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(callPlan120)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceWithPlan", is(167.20)))
                .andExpect(jsonPath("$.priceWithoutPlan", is(380.00)));
    }
}