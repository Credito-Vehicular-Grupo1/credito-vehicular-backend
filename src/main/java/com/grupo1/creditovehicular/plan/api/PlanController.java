package com.grupo1.creditovehicular.plan.api;

import com.grupo1.creditovehicular.plan.domain.model.entity.Plan;
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
@RequestMapping(value = "api/v1/users/{userId}/plans")
public class PlanController {
    private final PlanService planService;
    private final PlanMapper mapper;

    public PlanController(PlanService planService, PlanMapper mapper) {
        this.planService = planService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<PlanResource> getAllPlansByUserId(@PathVariable Long userId, Pageable pageable) {
        return mapper.modelListPage(planService.getAllPlansByUserId(userId), pageable);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResource> getPlanById(@PathVariable Long userId, @PathVariable Long planId) {
        return planService.getPlanByUserIdAndPlanId(userId, planId)
                .map(plan -> ResponseEntity.ok(mapper.toResource(plan)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualiza la ruta y el método para crear un plan para un usuario específico
    @PostMapping
    public PlanResource createPlan(@PathVariable Long userId, @RequestBody CreatePlanResource resource) {
        // Asegúrate de que el userId del path coincide con el del cuerpo de la petición si es necesario
        Plan plan = mapper.toModel(resource);
        plan = planService.createForUser(userId, plan);
        return mapper.toResource(plan);
    }

    // Actualiza la ruta y el método para actualizar un plan de un usuario específico
    @PutMapping("/{planId}")
    public PlanResource updatePlan(@PathVariable Long userId, @PathVariable Long planId, @RequestBody UpdatePlanResource resource) {
        Plan plan = mapper.toModel(resource);
        return mapper.toResource(planService.updateForUser(userId, planId, plan));
    }

    // Actualiza la ruta y el método para eliminar un plan de un usuario específico
    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long userId, @PathVariable Long planId) {
        return planService.deleteForUser(userId, planId);
    }
}
