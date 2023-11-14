package com.grupo1.creditovehicular.plan.api;

import com.grupo1.creditovehicular.plan.domain.service.ResultService;
import com.grupo1.creditovehicular.plan.mapping.ResultMapper;
import com.grupo1.creditovehicular.plan.resource.result.CreateResultResource;
import com.grupo1.creditovehicular.plan.resource.result.ResultResource;
import com.grupo1.creditovehicular.plan.resource.result.UpdateResultResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Results", description = "Create, read, update and delete results")
@RestController
@RequestMapping(value = "api/v1/results")
public class ResultController {
    private final ResultService resultService;
    private final ResultMapper mapper;

    public ResultController(ResultService resultService, ResultMapper mapper) {
        this.resultService = resultService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ResultResource> getAllResults(Pageable pageable) {
        return mapper.modelListPage(resultService.getAll(), pageable);
    }

    @PostMapping
    public ResultResource createResult(@RequestBody CreateResultResource resource) {
        return mapper.toResource(resultService.create(mapper.toModel(resource)));
    }

    @PutMapping("/{resultId}")
    public ResultResource updateResult(@PathVariable Long resultId, @RequestBody UpdateResultResource resource) {
        return mapper.toResource(resultService.update(resultId, mapper.toModel(resource)));
    }

    @DeleteMapping("{resultId}")
    public ResponseEntity<?> deleteResult(@PathVariable Long resultId) {
        return resultService.delete(resultId);
    }
}
