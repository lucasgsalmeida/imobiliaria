package com.lucas.imobiliaria.model.domain.obrigacoes;

import java.util.Date;

public enum Recorrencia {

    FALSE("false"),
    MENSAL("mensal"),
    TRIMESTRAL("trimestral"),
    SEMESTRAL("trimestral"),
    ANUAL("anual");

    private String recorrencia;

    Recorrencia(String recorrencia) {
        this.recorrencia = recorrencia;
    }

    public String getRecorrencia() {
        return this.recorrencia;
    }

}
