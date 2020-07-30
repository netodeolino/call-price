package com.br.falemaisapi.model.dto;

import com.br.falemaisapi.model.entity.Call;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CallPriceDTO {
    private Double priceWithPlan;
    private Double priceWithoutPlan;
}
