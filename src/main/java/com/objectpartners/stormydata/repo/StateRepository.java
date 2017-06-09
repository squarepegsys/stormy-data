package com.objectpartners.stormydata.repo;

import com.objectpartners.stormydata.entity.State;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by mikeh on 6/6/17.
 */
@RepositoryRestResource
public interface StateRepository  extends PagingAndSortingRepository<State,Long> {
}
