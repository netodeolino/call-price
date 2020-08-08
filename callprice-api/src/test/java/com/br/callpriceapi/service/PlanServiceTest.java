package com.br.callpriceapi.service;

import com.br.callpriceapi.model.dto.CallPriceDTO;
import com.br.callpriceapi.model.entity.Call;
import com.br.callpriceapi.model.entity.Plan;
import com.br.callpriceapi.model.entity.Price;
import com.br.callpriceapi.model.entity.TaxRate;
import com.br.callpriceapi.repository.PlanRepository;
import com.br.callpriceapi.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlanServiceTest {
    @MockBean
    private PlanRepository planRepository;

    @MockBean
    private PriceRepository priceRepository;

    @Autowired
    private PlanService planService;

    private Plan plan30;
    private Plan plan60;
    private Plan plan120;

    @BeforeEach
    void setUp() {
        TaxRate taxRate = new TaxRate(1L, 1.1);

        this.plan30 = new Plan(1L, 30, "CallPrice 30", taxRate);
        this.plan60 = new Plan(2L, 60, "CallPrice 60", taxRate);
        this.plan120 = new Plan(3L, 120, "CallPrice 120", taxRate);
    }

    @Test
    void findAll() {
        when(planRepository.findAll()).thenReturn(Arrays.asList(plan30, plan60, plan120));

        List<Plan> plans = planRepository.findAll();

        assertEquals(3, plans.size());
        assertEquals(plan30.getName(), plans.get(0).getName());
    }

    @Test
    void calculatePlan30_Duration31() {
        Price price = new Price(1L, 11, 16, 1.90);

        when(planRepository.findById(anyLong())).thenReturn(Optional.of(plan30));
        when(priceRepository.findByDddOriginAndDddDestiny(anyInt(), anyInt())).thenReturn(Optional.of(price));

        Call call = new Call(1L, 11, 16, 31, plan30);

        CallPriceDTO callPriceDTO = planService.calculate(call);

        assertEquals("2,09", String.format("%.2f", callPriceDTO.getPriceWithPlan()));
    }

    @Test
    void calculatePlan30_Duration20() {
        Price price = new Price(1L, 11, 16, 1.90);

        when(planRepository.findById(anyLong())).thenReturn(Optional.of(plan30));
        when(priceRepository.findByDddOriginAndDddDestiny(anyInt(), anyInt())).thenReturn(Optional.of(price));

        Call call = new Call(1L, 11, 16, 20, plan30);

        CallPriceDTO callPriceDTO = planService.calculate(call);

        assertEquals("0,00", String.format("%.2f", callPriceDTO.getPriceWithPlan()));
    }

    @Test
    void calculatePlan60_Duration80() {
        Price price = new Price(1L, 11, 17, 1.70);

        when(planRepository.findById(anyLong())).thenReturn(Optional.of(plan60));
        when(priceRepository.findByDddOriginAndDddDestiny(anyInt(), anyInt())).thenReturn(Optional.of(price));

        Call call = new Call(1L, 11, 17, 80, plan60);

        CallPriceDTO callPriceDTO = planService.calculate(call);

        assertEquals("37,40", String.format("%.2f", callPriceDTO.getPriceWithPlan()));
    }

    @Test
    void calculatePlan60_Duration60() {
        Price price = new Price(1L, 11, 17, 1.70);

        when(planRepository.findById(anyLong())).thenReturn(Optional.of(plan60));
        when(priceRepository.findByDddOriginAndDddDestiny(anyInt(), anyInt())).thenReturn(Optional.of(price));

        Call call = new Call(1L, 11, 17, 60, plan60);

        CallPriceDTO callPriceDTO = planService.calculate(call);

        assertEquals("0,00", String.format("%.2f", callPriceDTO.getPriceWithPlan()));
    }

    @Test
    void calculatePlan120_Duration200() {
        Price price = new Price(1L, 18, 11, 1.90);

        when(planRepository.findById(anyLong())).thenReturn(Optional.of(plan120));
        when(priceRepository.findByDddOriginAndDddDestiny(anyInt(), anyInt())).thenReturn(Optional.of(price));

        Call call = new Call(1L, 18, 11, 200, plan120);

        CallPriceDTO callPriceDTO = planService.calculate(call);

        assertEquals("167,20", String.format("%.2f", callPriceDTO.getPriceWithPlan()));
    }

    @Test
    void calculatePlan120_Duration60() {
        Price price = new Price(1L, 18, 11, 1.90);

        when(planRepository.findById(anyLong())).thenReturn(Optional.of(plan120));
        when(priceRepository.findByDddOriginAndDddDestiny(anyInt(), anyInt())).thenReturn(Optional.of(price));

        Call call = new Call(1L, 18, 11, 60, plan120);

        CallPriceDTO callPriceDTO = planService.calculate(call);

        assertEquals("0,00", String.format("%.2f", callPriceDTO.getPriceWithPlan()));
    }
}