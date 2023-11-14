package com.grupo1.creditovehicular.plan.domain.persistence;

import com.grupo1.creditovehicular.plan.domain.model.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
