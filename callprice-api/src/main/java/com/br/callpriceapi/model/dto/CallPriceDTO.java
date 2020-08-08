package com.br.callpriceapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallPriceDTO {
    private Double priceWithPlan;
    private Double priceWithoutPlan;
}
