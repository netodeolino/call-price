package com.br.callpriceapi.controller;

import com.br.callpriceapi.model.dto.CallPriceDTO;
import com.br.callpriceapi.model.entity.Call;
import com.br.callpriceapi.model.entity.Plan;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanController {
    @ApiOperation(
            value = "List all plans",
            response = Plan.class,
            responseContainer = "List")
    public ResponseEntity<List<Plan>> findAll();

    @ApiOperation(
            value = "Calculate the call value",
            response = CallPriceDTO.class)
    public ResponseEntity<CallPriceDTO> calculate(Call call);
}
