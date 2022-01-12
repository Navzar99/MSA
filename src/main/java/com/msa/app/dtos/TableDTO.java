package com.msa.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.app.entities.Table;

public class TableDTO {
    public @JsonProperty Integer number;

    public Table mapToTable() {
        return new Table(number);
    }
}
