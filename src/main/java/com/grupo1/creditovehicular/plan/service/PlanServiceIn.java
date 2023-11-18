package com.grupo1.creditovehicular.plan.service;

import com.grupo1.creditovehicular.plan.domain.model.entity.Plan;
import com.grupo1.creditovehicular.plan.domain.persistence.PlanRepository;
import com.grupo1.creditovehicular.plan.domain.service.PlanService;
import com.grupo1.creditovehicular.shared.exception.ResourceNotFoundException;
import com.grupo1.creditovehicular.shared.exception.ResourceValidationException;
import com.grupo1.creditovehicular.user.domain.model.entity.User;
import com.grupo1.creditovehicular.user.domain.persistence.UserRepository;
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
    private final UserRepository userRepository;
    private final Validator validator;

    public PlanServiceIn(PlanRepository planRepository, UserRepository userRepository, Validator validator) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
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
    public List<Plan> getAllPlansByUserId(Long userId) {
        return planRepository.findByUserId(userId);
    }

    @Override
    public Plan createForUser(Long userId, Plan plan) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
        plan.setUser(user);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return planRepository.save(plan);
    }

    @Override
    public Plan updateForUser(Long userId, Long planId, Plan request) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        return planRepository.findByIdAndUserId(planId, userId).map(plan ->
                planRepository.save(plan.withPeriodoGracia(request.getPeriodoGracia())
                        .withTipoTasa(request.getTipoTasa())
                        .withTipoMoneda(request.getTipoMoneda())
                        .withCostoTotal(request.getCostoTotal())
                        .withCuotaInicial(request.getCuotaInicial())
                        .withNumeroPeriodoGracia(request.getNumeroPeriodoGracia())
                        .withTasa(request.getTasa())
                        .withCok(request.getCok())
                        .withPeriodo(request.getPeriodo())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }

    @Override
    public ResponseEntity<?> deleteForUser(Long userId, Long planId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        return planRepository.findByIdAndUserId(planId, userId).map(
                plan -> {
                    planRepository.delete(plan);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }
}
