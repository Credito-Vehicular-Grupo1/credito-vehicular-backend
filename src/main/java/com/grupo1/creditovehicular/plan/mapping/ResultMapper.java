package com.grupo1.creditovehicular.plan.mapping;

import com.grupo1.creditovehicular.plan.domain.model.entity.Result;
import com.grupo1.creditovehicular.plan.resource.result.CreateResultResource;
import com.grupo1.creditovehicular.plan.resource.result.ResultResource;
import com.grupo1.creditovehicular.plan.resource.result.UpdateResultResource;
import com.grupo1.creditovehicular.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ResultMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public ResultResource toResource(Result model) {
        return mapper.map(model, ResultResource.class);
    }

    public Result toModel(CreateResultResource resource) {
        return mapper.map(resource, Result.class);
    }

    public Result toModel(UpdateResultResource resource) {
        return mapper.map(resource, Result.class);
    }

    public Page<ResultResource> modelListPage(List<Result> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ResultResource.class), pageable, modelList.size());
    }
}
