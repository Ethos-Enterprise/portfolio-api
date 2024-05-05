package com.ethos.portfolioapi.api.prestadoraDto;

import java.util.UUID;

public class PrestadoraBuilder {
    private UUID id;

    PrestadoraBuilder() {
    }

    public PrestadoraBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public PrestadoraDto build() {
        return new PrestadoraDto(id);
    }

    public String toString() {
        return "PrestadoraBuilder(id=" + this.id + ")";
    }
}
