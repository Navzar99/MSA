package com.msa.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.app.entities.MenuProduct;

public class MenuProductDTO {
    public @JsonProperty String name;
    public @JsonProperty Float price;
    public @JsonProperty Boolean isInStock;
    public @JsonProperty String description;

    public MenuProduct mapToMenuProduct() {
        return new MenuProduct(name, price, description, isInStock);
    }
}
