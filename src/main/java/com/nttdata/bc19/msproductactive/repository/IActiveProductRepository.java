package com.nttdata.bc19.msproductactive.repository;

import com.nttdata.bc19.msproductactive.model.ActiveProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActiveProductRepository extends ReactiveMongoRepository<ActiveProduct, String> {
}
