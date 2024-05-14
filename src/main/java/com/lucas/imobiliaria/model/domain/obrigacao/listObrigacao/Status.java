package com.lucas.imobiliaria.model.domain.obrigacao.listObrigacao;

public enum Status {

    NAO_ENTREGUE("nao_entregue"),
    ENTREGUE("entregue"),
    DISPENSADO("dispensado");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
