package com.nttdata.bc19.msproductactive.service.impl;

import com.nttdata.bc19.msproductactive.exception.ModelNotFoundException;
import com.nttdata.bc19.msproductactive.model.ActiveProduct;
import com.nttdata.bc19.msproductactive.repository.IActiveProductRepository;
import com.nttdata.bc19.msproductactive.service.IActiveProductService;
import com.nttdata.bc19.msproductactive.util.LogMessage;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Service
public class ActiveProductServiceImpl implements IActiveProductService {

    private final Logger LOGGER = LoggerFactory.getLogger("ActiveProductLog");
    private final String SAVESUCCESS = "SAVESUCCESS";
    private final String UPDATESUCCESS = "UPDATESUCCESS";
    private final String DELETESUCCESS = "DELETESUCCESS";
    @Autowired
    IActiveProductRepository activeProductRepository;

    @Override
    public Mono<ActiveProduct> create(ActiveProduct activeProduct) {
        activeProduct.setId(new ObjectId().toString());
        activeProduct.setCreatedAt(LocalDateTime.now());
        return activeProductRepository.save(activeProduct).doOnSuccess(this.doOnSucess(SAVESUCCESS));
    }

    @Override
    public Mono<ActiveProduct> update(ActiveProduct activeProduct) {
        activeProduct.setUpdatedAt(LocalDateTime.now());
        return activeProductRepository
                .findById(activeProduct.getId())
                .switchIfEmpty(Mono.error(new ModelNotFoundException(LogMessage.idNotFound)))
                .flatMap(personClientFind -> {
                    return activeProductRepository.save(activeProduct).doOnSuccess(this.doOnSucess(UPDATESUCCESS));
                });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return activeProductRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new ModelNotFoundException(LogMessage.idNotFound)))
                .flatMap(personClient -> {
                    return activeProductRepository.deleteById(id).doOnSuccess(this.doOnSucessDelete(DELETESUCCESS));
                });
    }

    @Override
    public Mono<ActiveProduct> findById(String id) { return activeProductRepository.findById(id).switchIfEmpty(Mono.error(new ModelNotFoundException(LogMessage.idNotFound))); }

    @Override
    public Flux<ActiveProduct> findAll() {
        return activeProductRepository.findAll();
    }

    private Consumer<ActiveProduct> doOnSucess(String idLogMessage){
        return new Consumer<ActiveProduct>() {
            @Override
            public void accept(ActiveProduct personClient) {
                LOGGER.info(LogMessage.logMessage.get(idLogMessage));
            }
        };
    }

    private Consumer<Void> doOnSucessDelete(String idLogMessage){
        return new Consumer<Void>() {
            @Override
            public void accept(Void unused) {
                LOGGER.info(LogMessage.logMessage.get(idLogMessage));
            }
        };
    }
}
