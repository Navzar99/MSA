package com.msa.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.app.entities.CustomerTable;

public class CustomerTableDTO {
    public @JsonProperty Integer number;
    public @JsonProperty Boolean doesRequestWaiter;

    public CustomerTable mapToTable() {
        return new CustomerTable(number, doesRequestWaiter);
    }
}
