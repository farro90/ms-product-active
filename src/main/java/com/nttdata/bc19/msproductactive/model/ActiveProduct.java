package com.nttdata.bc19.msproductactive.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveProduct extends BaseModel {
    private String name;
    private double interestRateMonth;
    private Boolean allowBusinessClient;
    private Boolean allowPersonClient;
}
