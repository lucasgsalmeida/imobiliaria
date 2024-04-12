package com.lucas.imobiliaria.infra;

import com.lucas.imobiliaria.model.domain.cliente.Clientes;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClienteStateCache {

    private final Map<Long, Clientes> userStateMap = new ConcurrentHashMap<>();

    public void saveClienteState(Long id, Clientes clientes) {
        userStateMap.put(id, clientes);
    }

    public Clientes getClienteState(Long id) {
        return userStateMap.get(id);
    }

    public void removeClienteState(Long id) {
        userStateMap.remove(id);
    }
}
