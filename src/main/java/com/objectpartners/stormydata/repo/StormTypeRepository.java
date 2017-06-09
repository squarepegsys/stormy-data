package com.objectpartners.stormydata.repo;

import com.objectpartners.stormydata.entity.StormType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by mikeh on 6/6/17.
 */
@RepositoryRestResource
public interface StormTypeRepository extends JpaRepository<StormType,Long> {

}
