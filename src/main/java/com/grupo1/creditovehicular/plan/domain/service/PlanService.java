package com.grupo1.creditovehicular.plan.domain.service;

import com.grupo1.creditovehicular.plan.domain.model.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanService {
    List<Plan> getAll();
    Page<Plan> getAll(Pageable pageable);
    Plan createForUser(Long userId, Plan plan);
    Plan updateForUser(Long userId, Long planId, Plan plan);
    ResponseEntity<?> deleteForUser(Long userId, Long planId);

    List<Plan> getAllPlansByUserId(Long userId);
}
