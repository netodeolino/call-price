package com.br.callpriceapi.service;

import com.br.callpriceapi.model.dto.CallPriceDTO;
import com.br.callpriceapi.model.entity.Call;
import com.br.callpriceapi.model.entity.Plan;

import java.util.List;

public interface PlanService {
    public List<Plan> findAll();

    public CallPriceDTO calculate(Call call);
}
