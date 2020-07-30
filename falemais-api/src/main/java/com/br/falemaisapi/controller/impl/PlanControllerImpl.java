package com.br.falemaisapi.controller.impl;

import com.br.falemaisapi.controller.PlanController;
import com.br.falemaisapi.model.dto.CallPriceDTO;
import com.br.falemaisapi.model.entity.Call;
import com.br.falemaisapi.model.entity.Plan;
import com.br.falemaisapi.service.PlanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plan")
@Api(tags = "PlanController")
public class PlanControllerImpl implements PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping
    public ResponseEntity<List<Plan>> findAll() {
        return ResponseEntity.ok(this.planService.findAll());
    }

    @PostMapping("/calculate")
    public ResponseEntity<CallPriceDTO> calculate(@RequestBody Call call) {
        return ResponseEntity.ok(this.planService.calculate(call));
    }
}
