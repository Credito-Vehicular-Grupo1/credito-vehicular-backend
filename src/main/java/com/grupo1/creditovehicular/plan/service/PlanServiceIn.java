package com.grupo1.creditovehicular.plan.service;

import com.grupo1.creditovehicular.plan.domain.model.entity.Plan;
import com.grupo1.creditovehicular.plan.domain.persistence.PlanRepository;
import com.grupo1.creditovehicular.plan.domain.service.PlanService;
import com.grupo1.creditovehicular.shared.exception.ResourceNotFoundException;
import com.grupo1.creditovehicular.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PlanServiceIn implements PlanService {
    private static final String ENTITY = "Plan";

    private final PlanRepository planRepository;
    private final Validator validator;

    public PlanServiceIn(PlanRepository planRepository, Validator validator) {
        this.planRepository = planRepository;
        this.validator = validator;
    }

    @Override
    public List<Plan> getAll() {
        return planRepository.findAll();
    }

    @Override
    public Page<Plan> getAll(Pageable pageable) {
        return planRepository.findAll(pageable);
    }

    @Override
    public Plan create(Plan plan) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return planRepository.save(plan);
    }

    @Override
    public Plan update(Long planId, Plan request) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return planRepository.findById(planId).map(plan ->
                planRepository.save(plan.withPeriodoGracia(request.getPeriodoGracia())
                        .withTipoTasa(request.getTipoTasa())
                        .withTipoMoneda(request.getTipoMoneda())
                        .withCostoTotal(request.getCostoTotal())
                        .withCuotaInicial(request.getCuotaInicial())
                        .withTasa(request.getTasa())
                        .withNCuotas(request.getNCuotas())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }

    @Override
    public ResponseEntity<?> delete(Long planId) {
        return planRepository.findById(planId).map(
                plan -> {
                    planRepository.delete(plan);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }
}
