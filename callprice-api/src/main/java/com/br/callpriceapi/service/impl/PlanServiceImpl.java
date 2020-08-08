package com.br.callpriceapi.service.impl;

import com.br.callpriceapi.exception.BadRequestException;
import com.br.callpriceapi.model.dto.CallPriceDTO;
import com.br.callpriceapi.model.entity.Call;
import com.br.callpriceapi.model.entity.Plan;
import com.br.callpriceapi.model.entity.Price;
import com.br.callpriceapi.repository.PlanRepository;
import com.br.callpriceapi.repository.PriceRepository;
import com.br.callpriceapi.service.PlanService;
import com.br.callpriceapi.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Plan> findAll() {
        return this.planRepository.findAll();
    }

    @Override
    public CallPriceDTO calculate(Call call) {
        log.info("Calculate call: {}", call.toString());

        try {
            Plan plan = this.planRepository
                    .findById(call.getPlan().getId())
                    .orElseThrow(() -> new BadRequestException(Constants.BAD_REQUEST));

            Price price = this.priceRepository
                    .findByDddOriginAndDddDestiny(call.getDddOrigin(), call.getDddDestiny())
                    .orElseThrow(() -> new BadRequestException(Constants.BAD_REQUEST));

            Double priceWithPlan = this.calculateWithPlan(plan, call, price);
            Double priceWithoutPlan = this.calculateWithoutPlan(call, price);

            return new CallPriceDTO(priceWithPlan, priceWithoutPlan);
        } catch (Exception e) {
            log.error("ERROR in CallPriceDTO.calculate(): ", e.getMessage());
            throw e;
        }
    }

    private Double calculateWithPlan(Plan plan, Call call, Price price) {
        if (call.getDuration() <= plan.getDuration()) {
            return 0.0;
        }

        Integer minutes = call.getDuration() - plan.getDuration();

        return minutes * price.getMinutePrice() * plan.getTaxRate().getValue();
    }

    private Double calculateWithoutPlan(Call call, Price price) {
        return call.getDuration() * price.getMinutePrice();
    }
}
