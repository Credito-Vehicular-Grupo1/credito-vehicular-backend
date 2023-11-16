package com.grupo1.creditovehicular.plan.mapping;

import com.grupo1.creditovehicular.plan.domain.model.entity.Plan;
import com.grupo1.creditovehicular.plan.resource.plan.CreatePlanResource;
import com.grupo1.creditovehicular.plan.resource.plan.PlanResource;
import com.grupo1.creditovehicular.plan.resource.plan.UpdatePlanResource;
import com.grupo1.creditovehicular.shared.exception.ResourceNotFoundException;
import com.grupo1.creditovehicular.shared.mapping.EnhancedModelMapper;
import com.grupo1.creditovehicular.user.domain.model.entity.User;
import com.grupo1.creditovehicular.user.domain.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PlanMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PlanResource toResource(Plan model) {
        return mapper.map(model, PlanResource.class);
    }

    public Page<PlanResource> modelListPage(List<Plan> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PlanResource.class), pageable, modelList.size());
    }

    public Plan toModel(CreatePlanResource resource) {
        // Simplemente mapea los campos de CreatePlanResource a Plan.
        // La asociación con User se manejará en el servicio.
        return mapper.map(resource, Plan.class);
    }

    public Plan toModel(UpdatePlanResource resource) {
        // De igual manera, simplemente mapea los campos de UpdatePlanResource a Plan.
        return mapper.map(resource, Plan.class);
    }

    // Si necesitas convertir una entidad Plan a un PlanResource y quieres incluir el userId,
    // puedes dejar el método toResourceWithUser. Si no, puedes quitarlo.
    public PlanResource toResourceWithUser(Plan model) {
        PlanResource resource = toResource(model);
        if (model.getUser() != null) {
            resource.setUserId(model.getUser().getId());
        }
        return resource;
    }
}
