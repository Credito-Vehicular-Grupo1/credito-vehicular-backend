package com.grupo1.creditovehicular.plan.domain.service;

import com.grupo1.creditovehicular.plan.domain.model.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanService {
    List<Plan> getAll();
    Page<Plan> getAll(Pageable pageable);
    Plan create(Plan plan);
    Plan update(Long planId, Plan plan);
    ResponseEntity<?> delete(Long planId);
}
