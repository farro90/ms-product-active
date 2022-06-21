package com.nttdata.bc19.msproductactive.api;

import com.nttdata.bc19.msproductactive.model.ActiveProduct;
import com.nttdata.bc19.msproductactive.service.IActiveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product/active")
public class ActiveProductApi {
    @Autowired
    private IActiveProductService activeProductService;
    /*
    @PostMapping
    public Mono<ActiveProduct> create(@RequestBody ActiveProduct activeProduct){ return activeProductService.create(activeProduct); }

    @PutMapping
    public Mono<ActiveProduct> update(@RequestBody ActiveProduct activeProduct){ return activeProductService.update(activeProduct); }
     */
    @GetMapping
    public Flux<ActiveProduct> findAll(){
        return activeProductService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ActiveProduct> findById(@PathVariable String id){ return activeProductService.findById(id); }
    /*
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return activeProductService.deleteById(id);
    }
     */
}
