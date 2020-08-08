package com.br.callpriceapi.controller.impl;

import com.br.callpriceapi.controller.PlanController;
import com.br.callpriceapi.model.dto.CallPriceDTO;
import com.br.callpriceapi.model.entity.Call;
import com.br.callpriceapi.model.entity.Plan;
import com.br.callpriceapi.service.PlanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plan")
@CrossOrigin(origins = "*")
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
