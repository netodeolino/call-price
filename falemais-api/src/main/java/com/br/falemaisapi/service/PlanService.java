package com.br.falemaisapi.service;

import com.br.falemaisapi.model.dto.CallPriceDTO;
import com.br.falemaisapi.model.entity.Call;
import com.br.falemaisapi.model.entity.Plan;

import java.util.List;

public interface PlanService {
    public List<Plan> findAll();

    public CallPriceDTO calculate(Call call);
}
