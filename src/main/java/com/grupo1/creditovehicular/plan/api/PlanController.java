package com.grupo1.creditovehicular.plan.api;

import com.grupo1.creditovehicular.plan.domain.service.PlanService;
import com.grupo1.creditovehicular.plan.mapping.PlanMapper;
import com.grupo1.creditovehicular.plan.resource.plan.CreatePlanResource;
import com.grupo1.creditovehicular.plan.resource.plan.PlanResource;
import com.grupo1.creditovehicular.plan.resource.plan.UpdatePlanResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Plans", description = "Create, read, update and delete plans")
@RestController
@RequestMapping(value = "api/v1/plans")
public class PlanController {
    private final PlanService planService;
    private final PlanMapper mapper;

    public PlanController(PlanService planService, PlanMapper mapper) {
        this.planService = planService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<PlanResource> getAllPlans(Pageable pageable) {
        return mapper.modelListPage(planService.getAll(), pageable);
    }

    @PostMapping
    public PlanResource createPlan(@RequestBody CreatePlanResource resource) {
        return mapper.toResource(planService.create(mapper.toModel(resource)));
    }

    @PutMapping("/{planId}")
    public PlanResource updatePlan(@PathVariable Long planId, @RequestBody UpdatePlanResource resource) {
        return mapper.toResource(planService.update(planId, mapper.toModel(resource)));
    }

    @DeleteMapping("{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long planId) {
        return planService.delete(planId);
    }
}
