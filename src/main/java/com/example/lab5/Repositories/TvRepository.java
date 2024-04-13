package com.example.lab5.Repositories;

import com.example.lab5.Repositories.Entities.TvEntity;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.repository.CrudRepository;

@NonNullApi
public interface TvRepository extends CrudRepository<TvEntity, Integer> {
}
